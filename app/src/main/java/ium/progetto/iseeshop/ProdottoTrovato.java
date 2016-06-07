package ium.progetto.iseeshop;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
public class ProdottoTrovato extends Activity implements customToolBarInterface {

    private static String TAG = "Prodotto Trovato";
    private static String registname = "registrazione";
    public static final String FILE = "prodottiaAggiunti";

    ListView listViewProdotto;
    CustomAdapterProdottoTrovato customAdapter;
    CustomAdapterCarrello customAdapterCarrello;
    Prodotto prodotto;
    private AudioManager am;
    private MediaPlayer mp;
    SharedPreferences sp;
    int contatoreProdottiAggiunti =0;
    TextView nomeActivity;
    Button piu, meno;
    EditText quantita;
    int quant=0;

    ImageButton play, home, addCarrello;
    boolean iconaPlay = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodotto_trovato_layout);


        nomeActivity = (TextView)findViewById(R.id.nomeActivity);
        nomeActivity.setText("Dettaglio Prodotto");
        play = (ImageButton) findViewById(R.id.play);
        piu = (Button)findViewById(R.id.piu);
        meno = (Button) findViewById(R.id.meno);
        quantita = (EditText) findViewById(R.id.quantita);

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
        customAdapter = new CustomAdapterProdottoTrovato(this,R.layout.list_element_prodotto_trovato, new ArrayList<String>());
        listViewProdotto.setAdapter(customAdapter);
        play = (ImageButton) findViewById(R.id.play);
        addCarrello =(ImageButton) findViewById(R.id.carrello);

        sp = getSharedPreferences("Prodotti", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();


        //Creazione Prodotto

        prodotto = new Prodotto("Latte Parmalat", 1.00f, "Parmalat", "28/06/16", "6/06/2016", 1);

        //aggiungo al list view
        customAdapter.add(prodotto.getNome());
        customAdapter.add(""+prodotto.getPrezzo()+"€");
        customAdapter.add("Elemento 2/5");
        customAdapter.notifyDataSetChanged();

        //riproduzione vocale play/pausa
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iconaPlay) {
                    play.setBackground(getDrawable(R.drawable.pausa));
                    Log.d(TAG,"buttonPlay pressed");
                    if (mp == null) {
                        Toast.makeText(getApplicationContext(), "Nessun brano selezionato", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mp.start();
                    iconaPlay=false;
                }else{
                    play.setBackground(getDrawable(R.drawable.play));
                    Log.d(TAG,"buttonPause pressed");
                    if (mp != null) {
                        mp.pause();
                    }
                    iconaPlay=true;
                }
            }
        });

        //aggiunta al carrello
        addCarrello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileOutputStream fos = null;

                Log.d("DEBUG", "Salvo sul file il prodotto");
                try {
                    fos = openFileOutput(FILE, Context.MODE_PRIVATE);
                    //prendo la quantita inserita dall'utente e lo setto nel prodotto
                    quant=Integer.parseInt(quantita.getText().toString());
                    prodotto.setQuantita(quant);

                    //salvo l'intero prodotto sul file per leggerlo nel carrello successivamente
                        String str = prodotto.getNome()+"\n" +prodotto.getPrezzo()+"\n"+ prodotto.getProduttore()+"\n"+prodotto.getScadenza()+"\n"+prodotto.getDataProduzione()+"\n"+prodotto.getQuantita()+"\n";
                        fos.write(str.getBytes());


                    fos.close();

                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplication(),"Il prodotto è stato aggiunto al carrello.", Toast.LENGTH_LONG).show();

                finish();
                Intent carrello= new Intent(getApplication(), Carrello.class);
                startActivity(carrello);


            }
        });

        piu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quant=Integer.parseInt(quantita.getText().toString());
                quant = quant+1;
                quantita.setText(""+quant);
            }
        });
        meno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quant=Integer.parseInt(quantita.getText().toString());
                quant = quant-1;
                if(quant<1){
                    quantita.setText("1");
                }else {
                    quantita.setText("" + quant);
                }
            }
        });

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
