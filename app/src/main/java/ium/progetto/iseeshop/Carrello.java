package ium.progetto.iseeshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by robin on 26/05/2016.
 */
public class Carrello extends FragmentActivity {

    public static final String FILE = "prodottiaAggiunti";
    FileInputStream fis = null;

    private ListView listViewCarrello;
    private TextView textSomma;
    CustomAdapterCarrello customAdapterCarrello;
    ArrayList<Prodotto> arrayProdotti;
    SharedPreferences sp;
    ImageButton cestino;
    private Prodotto prodotto;
    private Prodotto prodotto1;
    private Prodotto prodotto2;
    private float somma;
    public static Carrello carrello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello_layout);

        carrello = this;
        arrayProdotti = new ArrayList<Prodotto>();
        cestino = (ImageButton) findViewById(R.id.cestino);
        listViewCarrello = (ListView) findViewById(R.id.listaProdotti);
        textSomma = (TextView) findViewById(R.id.somma);
        customAdapterCarrello = new CustomAdapterCarrello(this, R.layout.list_element, new ArrayList<Prodotto>());
        listViewCarrello.setAdapter(customAdapterCarrello);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sp.edit();

        //Creazione Prodotti
        prodotto = new Prodotto("Pasta Barilla", 1.20f, "Barilla",  "28/05/2018","29/06/16", 1, R.drawable.pipe_rigate);
        prodotto1 = new Prodotto("Fagioli Mersì", 2.00f, "Azienda Martea", "29/06/17", "28/05/2016", 2, R.drawable.fagioli);
        prodotto2 = new Prodotto("Acqua Vita Snella", 0.90f, "Vita Snella",  "28/05/2019","28/06/16", 4, R.drawable.acqua_vitasnella);



        arrayProdotti.add(prodotto);
        arrayProdotti.add(prodotto1);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto2);

        //Creazione Prodotto
        aggiungiProdotti();


        //OnClick listener per eliminazione item

        listViewCarrello.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Prodotto p = arrayProdotti.get(position);
                        editor.putString("nome",p.getNome());
                        editor.putFloat("prezzo", p.getPrezzo());
                        editor.putString("marca",p.getProduttore());
                        editor.putString("scadenza",p.getScadenza());
                        editor.putString("produzione",p.getDataProduzione());
                        editor.putInt("quantita",p.getQuantita());
                        editor.putBoolean("prodottoDaCarrello", true);
                        editor.putString("funziono","funziono");
                        editor.putInt("posizione", position);
                        editor.putInt("idImmagine", p.getIdImmagine());
                        editor.commit();
                        Intent prodottoTrovato = new Intent(getApplication(), ProdottoTrovato.class);
                        startActivity(prodottoTrovato);
                   }
               });

                listViewCarrello.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle(getString(R.string.titleAlertDelete)) //
                                .setMessage(getString(R.string.textAlertDelete)) //
                                .setPositiveButton(getString(R.string.deleteProduct), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //elimino il file dei prodotti aggiunti non mi serve piu
                                        File file = new File(FILE);
                                        boolean deleted = file.delete();
                                        arrayProdotti.remove(position);
                                        aggiungiProdotti();
                                        dialog.dismiss();

                                    }
                                }) //
                                .setNegativeButton(getString(R.string.ignoreDeleteProduct), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();

                                    }
                                });
                        builder.show();
                        return true;

                    }
                });

        cestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(getString(R.string.titleAlertSvuota)) //
                        .setMessage(getString(R.string.deleteCarrello)) //
                        .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                arrayProdotti.clear();
                                customAdapterCarrello.clear();
                                textSomma.setText("0");
                                editor.commit();
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
        customAdapterCarrello.clear();
        for(int i=0; i<arrayProdotti.size(); i++){
            somma = somma+arrayProdotti.get(i).getPrezzo()*arrayProdotti.get(i).getQuantita();
            customAdapterCarrello.add(arrayProdotti.get(i));
        }
        customAdapterCarrello.notifyDataSetChanged();
        float f = (float) (Math.round( somma * Math.pow( 10, 2 ) )/Math.pow( 10, 2 ));
        textSomma.setText(""+f);
    }

    public void rimuoviProdotto(int position) {
        arrayProdotti.remove(position);
        aggiungiProdotti();
    }

    public void aggiungiProdotto() {
        prodotto = new Prodotto(
                sp.getString("nome","Latte Parmalat"),
                sp.getFloat("prezzo", 1.00f),
                sp.getString("marca","Parmalat"),
                sp.getString("scadenza","28/06/16"),
                sp.getString("produzione","28/05/2016"),
                sp.getInt("quantita",1),
                sp.getInt("idImmagine", R.drawable.lattenoback));
        Log.d("prova shared ", sp.getString("funziono","non va :("));
        arrayProdotti.add(prodotto);
        aggiungiProdotti();
    }
}
