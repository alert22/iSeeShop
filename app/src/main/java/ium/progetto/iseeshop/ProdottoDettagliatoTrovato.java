package ium.progetto.iseeshop;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alina on 28/05/2016.
 */
public class ProdottoDettagliatoTrovato extends Activity implements customToolBarInterface {

    private static String TAG = "Prodotto Trovato";
    private static String registname = "registrazione";
    public static final String FILE = "prodottiaAggiunti";

    ListView listViewProdotto;
    CustomAdapterProdottoTrovato customAdapter;
    CustomAdapterCarrello customAdapterCarrello;
    Prodotto prodotto;
    private AudioManager am;
    private MediaPlayer mp;
    ImageView imgProdotto;
    SharedPreferences sp;
    int contatoreProdottiAggiunti = 0;
    TextView nomeActivity;
    Button piu, meno;
    EditText quantita;
    int quant = 0;
    ImageButton frecciaGiu;
    ImageButton play, home, addCarrello;
    boolean iconaPlay = true;
    LinearLayout quantitaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodotto_trovato_dettagliato_layout);

        quantitaLayout = (LinearLayout) findViewById(R.id.quantitaLayout);
        imgProdotto = (ImageView) findViewById(R.id.imgProdotto);
        nomeActivity = (TextView) findViewById(R.id.nomeActivity);
        nomeActivity.setText("Dettaglio Prodotto");
        play = (ImageButton) findViewById(R.id.play);
        quantita = (EditText) findViewById(R.id.quantita2);

        frecciaGiu = (ImageButton) findViewById(R.id.frecciagiu);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        ActivityManager.TaskDescription taskDesc =
                new ActivityManager.TaskDescription("iSeeShop", bm, getResources().getColor(R.color.coloreStatusBar));
        this.setTaskDescription(taskDesc);


        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        mp = MediaPlayer.create(this, getResources().getIdentifier(registname, "raw", getPackageName()));

        //Set colore barra di stato

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.coloreStatusBar));

        listViewProdotto = (ListView) findViewById(R.id.listview);
        customAdapter = new CustomAdapterProdottoTrovato(this, R.layout.list_element_prodotto_trovato, new ArrayList<String>());
        listViewProdotto.setAdapter(customAdapter);
        play = (ImageButton) findViewById(R.id.play);
        addCarrello = (ImageButton) findViewById(R.id.carrello);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        //Creazione Prodotto

        prodotto = new Prodotto(
                sp.getString("nome", "Latte Parmalat"),
                sp.getFloat("prezzo", 1.00f),
                sp.getString("marca", "Parmalat"),
                sp.getString("scadenza", "28/06/16"),
                sp.getString("produzione", "28/05/2016"),
                sp.getInt("quantita", 1),
                sp.getInt("idImmagine", R.drawable.lattenoback));
        Log.d("prova shared ", sp.getString("funziono", "non va :("));

        quantita.setText("" + prodotto.getQuantita());
        imgProdotto.setImageDrawable(getDrawable(prodotto.getIdImmagine()));

        //aggiungo al list view
        customAdapter.add(prodotto.getNome());
        customAdapter.add("" + prodotto.getPrezzo() + "€");
        customAdapter.add("Elemento 2/5");
        customAdapter.notifyDataSetChanged();

        //riproduzione vocale play/pausa
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iconaPlay) {
                    play.setBackground(getDrawable(R.drawable.pausa));
                    Log.d(TAG, "buttonPlay pressed");
                    if (mp == null) {
                        Toast.makeText(getApplicationContext(), "Nessun brano selezionato", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mp.start();
                    iconaPlay = false;
                } else {
                    play.setBackground(getDrawable(R.drawable.play));
                    Log.d(TAG, "buttonPause pressed");
                    if (mp != null) {
                        mp.pause();
                    }
                    iconaPlay = true;
                }
            }
        });

        //Freccia su

        frecciaGiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putInt("quantita",Integer.parseInt(quantita.getText().toString())).commit();
                Intent prodottoTrovato = new Intent(getApplication(), ProdottoTrovato.class);
                startActivity(prodottoTrovato);
                finish();
            }
        });

        //aggiunta al carrello

        if (sp.getBoolean("prodottoDaCarrello", true)) {
            addCarrello.setBackground(getDrawable(R.drawable.cestino));
            quantitaLayout.setVisibility(View.INVISIBLE);
            addCarrello.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle(getString(R.string.textAlertDelete)) //
                            .setMessage(getString(R.string.deleteCarrello)) //
                            .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Carrello.carrello.rimuoviProdotto(sp.getInt("posizione",0));
                                    goBack();
                                    dialog.dismiss();

                                }
                            }) //
                            .setNegativeButton(getString(R.string.ignoreDeleteProduct), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();

                                }
                            });
                    builder.show();
                }
            });
        } else {
            addCarrello.setBackground(getDrawable(R.drawable.carrello));
            addCarrello.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(getApplication(), "Il prodotto è stato aggiunto al carrello.", Toast.LENGTH_LONG).show();

                    finish();
                    Intent carrello = new Intent(getApplication(), MainActivity.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("nome", prodotto.getNome());
                    editor.putFloat("prezzo", prodotto.getPrezzo());
                    editor.putString("marca", prodotto.getProduttore());
                    editor.putString("scadenza", prodotto.getScadenza());
                    editor.putString("produzione", prodotto.getDataProduzione());
                    editor.putInt("quantita", Integer.parseInt(quantita.getText().toString()));
                    editor.putInt("idImmagine", prodotto.getIdImmagine());
                    editor.putBoolean("prodottoDaCarrello", true);
                    editor.putString("funziono", "funziono");
                    editor.putBoolean("scansione", false).commit();
                    editor.commit();
                    goHome(v);
                    //startActivity(carrello);


                }
            });
        }
    }

    public void piu(View v) {
        quant = Integer.parseInt(quantita.getText().toString());
        quant = quant + 1;
        quantita.setText("" + quant);
    }

    public void meno(View v) {
        quant = Integer.parseInt(quantita.getText().toString());
        quant = quant - 1;
        if (quant < 1) {
            quantita.setText("1");
        } else {
            quantita.setText("" + quant);
        }
    }

    public void goBack() {
        this.finish();
    }

    @Override
    public void goHome(View v) {
        this.finish();
    }

    @Override
    public void showPopup(View v) {
        CustomToolBar.show(this, v);
    }

}

