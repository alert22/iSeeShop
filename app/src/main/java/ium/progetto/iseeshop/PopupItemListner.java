package ium.progetto.iseeshop;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by robin on 28/05/2016.
 */

public class PopupItemListner implements PopupMenu.OnMenuItemClickListener {

    Context c;
    public void setContext(Context c) {
        this.c = c;
    } 

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(c, "Impostazioni", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_contact:
                Toast.makeText(c, "Contattaci", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_help:
                Toast.makeText(c, "Aiuto", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_info:
                Toast.makeText(c, "Info", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
