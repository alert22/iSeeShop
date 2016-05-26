package ium.progetto.iseeshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    private ListView listViewCarrello;
    CustomAdapter customAdapter;
    ArrayList<Prodotto> arrayProdotti;
    private Prodotto prodotto;
    private Prodotto prodotto1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Scansione");
        spec.setContent(R.id.Scansione);
        spec.setIndicator("Scansione");
        host.addTab(spec);


        //Tab 2
        spec = host.newTabSpec("Carrello");
        spec.setContent(R.id.Carrello);
        spec.setIndicator("Carrello");
        host.addTab(spec);

        arrayProdotti = new ArrayList<>();
        listViewCarrello = (ListView) findViewById(R.id.listaProdotti);
        customAdapter=new CustomAdapter(this, R.layout.list_element, new ArrayList<Prodotto>());
        listViewCarrello.setAdapter(customAdapter);
        prodotto = new Prodotto("Latte Parmalat", "1.00");
        prodotto1 = new Prodotto("Fagioli bb", "2.00");
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