package ium.progetto.iseeshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alina on 28/05/2016.
 */
public class ProdottoTrovato extends Activity {

    ListView listViewProdotto;
    CustomAdapterProdottoTrovato customAdapter;
    Prodotto prodotto;
    ImageButton play;
    boolean iconaPlay = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodotto_trovato_layout);
        listViewProdotto = (ListView) findViewById(R.id.listview);
        customAdapter = new CustomAdapterProdottoTrovato(this,R.layout.list_element_prodotto_trovato, new ArrayList<String>());
        listViewProdotto.setAdapter(customAdapter);
        play = (ImageButton) findViewById(R.id.play);

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

    }

    public void showPopup(View v) {
        CustomPopup.show(this, v);
    }

}
