package ium.progetto.iseeshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by robin on 26/05/2016.
 */
public class Carrello extends FragmentActivity {

    private ListView listViewCarrello;
    private TextView textSomma;
    CustomAdapter customAdapter;
    ArrayList<Prodotto> arrayProdotti;
    private Prodotto prodotto;
    private Prodotto prodotto1;
    private Prodotto prodotto2;
    private float somma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_layout);

        arrayProdotti = new ArrayList<>();
        listViewCarrello = (ListView) findViewById(R.id.listaProdotti);
        textSomma = (TextView) findViewById(R.id.somma);
        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<Prodotto>());
        listViewCarrello.setAdapter(customAdapter);

        //Creazione Prodotti
        prodotto = new Prodotto("Latte Parmalat", 1.00f);
        prodotto1 = new Prodotto("Fagioli bb", 2.00f);
        prodotto2 = new Prodotto("Acqua Naturale Ginevra", 0.90f);
        arrayProdotti.add(prodotto);
        arrayProdotti.add(prodotto1);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        aggiungiProdotti();

        //OnClick listenr per eliminazione item

        listViewCarrello.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(getString(R.string.titleAlertDelete)) //
                        .setMessage(getString(R.string.textAlertDelete)) //
                        .setPositiveButton(getString(R.string.deleteProduct), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                arrayProdotti.remove(position);
                                aggiungiProdotti();
                                dialog.dismiss();

                            }
                        }) //
                        .setNegativeButton(getString(R.string.ignoreDeleteProduct), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO
                                dialog.dismiss();

                            }
                        });
                builder.show();
                return true;

            }
        });
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

    public void aggiungiProdotti(){
        somma=0;
        customAdapter.clear();
        for(int i=0; i<arrayProdotti.size(); i++){
            somma = somma+arrayProdotti.get(i).getPrezzo();
            textSomma.setText(""+somma);
            customAdapter.add(arrayProdotti.get(i));
            customAdapter.notifyDataSetChanged();
        }
    }
}
