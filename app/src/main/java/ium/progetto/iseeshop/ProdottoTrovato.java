package ium.progetto.iseeshop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alina on 28/05/2016.
 */
public class ProdottoTrovato extends Activity {

    ListView listViewProdotto;
    CustomAdapterProdottoTrovato customAdapter;
    Prodotto prodotto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodotto_trovato_layout);
        listViewProdotto = (ListView) findViewById(R.id.listview);
        customAdapter = new CustomAdapterProdottoTrovato(this,R.layout.list_element_prodotto_trovato, new ArrayList<String>());
        listViewProdotto.setAdapter(customAdapter);

        //Creazione Prodotto
        prodotto = new Prodotto("Latte Parmalat", 1.00f, "Parmalat", "28/06/16", "28/05/2016");


        customAdapter.add(prodotto.getNome());
        customAdapter.add(""+prodotto.getPrezzo()+"€");
        customAdapter.add("Elemento 2/5");
        customAdapter.notifyDataSetChanged();
    }


}
