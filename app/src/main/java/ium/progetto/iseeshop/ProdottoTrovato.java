package ium.progetto.iseeshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Alina on 28/05/2016.
 */
public class ProdottoTrovato extends Activity implements customToolBarInterface {

    ListView listViewProdotto;
    CustomAdapterProdottoTrovato customAdapter;
    CustomAdapterCarrello customAdapterCarrello;
    Prodotto prodotto;
    SharedPreferences sp;
    int contatoreProdottiAggiunti =0;

    ImageButton play, addCarrello;
    boolean iconaPlay = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodotto_trovato_layout);
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
                    iconaPlay=false;
                }else{
                    play.setBackground(getDrawable(R.drawable.play));
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
