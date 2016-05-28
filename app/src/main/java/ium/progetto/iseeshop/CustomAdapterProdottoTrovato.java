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
public class CustomAdapterProdottoTrovato extends ArrayAdapter<String> {
    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterProdottoTrovato(Context context, int resourceId, List<String> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);//Crea un'istanza di un file XML di layout nella corrispondente View
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.list_element_prodotto_trovato, parent, false);
        }

        String string = getItem(position);

        TextView nomeTextView;


        nomeTextView = (TextView) v.findViewById(R.id.nome);

        nomeTextView.setText(string);



        return v;
    }
}

