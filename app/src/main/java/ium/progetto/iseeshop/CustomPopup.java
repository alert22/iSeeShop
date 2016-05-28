package ium.progetto.iseeshop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by robin on 28/05/2016.
 */
public class CustomPopup {

    public static void show(Context c, View v) {
        PopupMenu popup = new PopupMenu(c, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
        PopupItemListner pil = new PopupItemListner();
        pil.setContext(c);
        popup.setOnMenuItemClickListener(pil);


    }

}
