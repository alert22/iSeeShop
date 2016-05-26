package ium.progetto.iseeshop;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by robin on 26/05/2016.
 */
public class Carrello extends FragmentActivity {

    private ListView listViewCarrello;
    CustomAdapter customAdapter;
    ArrayList<Prodotto> arrayProdotti;
    private Prodotto prodotto;
    private Prodotto prodotto1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_layout);

        arrayProdotti = new ArrayList<>();
        listViewCarrello = (ListView) findViewById(R.id.listaProdotti);
        customAdapter=new CustomAdapter(this, R.layout.list_element, new ArrayList<Prodotto>());
        listViewCarrello.setAdapter(customAdapter);
        prodotto = new Prodotto("Latte Parmalat", 1.00f);
        prodotto1 = new Prodotto("Fagioli bb", 2.00f);
        arrayProdotti.add(prodotto);
        customAdapter.add(prodotto);
        customAdapter.add(prodotto1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
