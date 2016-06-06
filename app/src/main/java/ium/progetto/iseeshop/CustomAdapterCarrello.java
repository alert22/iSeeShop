package ium.progetto.iseeshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alina on 20/04/2016.
 */
public class CustomAdapterCarrello extends ArrayAdapter<Prodotto> {
    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterCarrello(Context context, int resourceId, List<Prodotto> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);//Crea un'istanza di un file XML di layout nella corrispondente View
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.list_element, parent, false);
        }

        Prodotto sq = getItem(position);

        TextView nomeTextView;
        TextView prezzoTextView;
        TextView quantita;

        quantita = (TextView) v.findViewById(R.id.pezzi);
        nomeTextView = (TextView) v.findViewById(R.id.nome);
        prezzoTextView = (TextView) v.findViewById(R.id.prezzo);
        quantita.setText(sq.getQuantita()+"p. ");
        nomeTextView.setText(sq.getNome());
        prezzoTextView.setText(""+sq.getPrezzo());


        return v;
    }
}

