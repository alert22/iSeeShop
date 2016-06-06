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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Alina on 28/05/2016.
 */
public class ProdottoTrovato extends Activity implements customToolBarInterface {

    private static String TAG = "Prodotto Trovato";
    private static String registname = "registrazione";
    ListView listViewProdotto;
    CustomAdapterProdottoTrovato customAdapter;
    CustomAdapterCarrello customAdapterCarrello;
    Prodotto prodotto;
    private AudioManager am;
    private MediaPlayer mp;
    SharedPreferences sp;
    int contatoreProdottiAggiunti =0;
    TextView nomeActivity;

    ImageButton play, home, addCarrello;
    boolean iconaPlay = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodotto_trovato_layout);


        nomeActivity = (TextView)findViewById(R.id.nomeActivity);
        nomeActivity.setText("Dettaglio Prodotto");
        play = (ImageButton) findViewById(R.id.play);


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
        prodotto = new Prodotto("Latte Parmalat", 1.00f, "Parmalat", "28/06/16", "28/05/2016");

        //aggiungo al list view
        customAdapter.add(prodotto.getNome());
        customAdapter.add(""+prodotto.getPrezzo()+"€");
        customAdapter.add("Elemento 2/5");
        customAdapter.notifyDataSetChanged();
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

        addCarrello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utilizzo sharePreferences per aggiungere i prodotti al carrello
                contatoreProdottiAggiunti++;
                editor.putString(""+contatoreProdottiAggiunti, prodotto.getNome());
                editor.putFloat(prodotto.getNome(), prodotto.getPrezzo());
                editor.apply();
                Toast.makeText(getApplication(),"Il prodotto è stato aggiunto al carrello.", Toast.LENGTH_LONG).show();

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
