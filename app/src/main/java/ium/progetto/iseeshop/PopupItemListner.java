package ium.progetto.iseeshop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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

            //BOTTONE IMPOSTAZIONI

            case R.id.action_settings:
                Toast.makeText(c, "Impostazioni", Toast.LENGTH_SHORT).show();
                return true;

            //BOTTONE CONTATTACI

            case R.id.action_contact:
                String uriText =
                        "mailto:iSeeShopSupport@gmail.com" +
                                "?subject=" + Uri.encode("Inserire Oggetto Mail") +
                                "&body=" + Uri.encode("Inserire Testo");

                Uri uri = Uri.parse(uriText);

                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                c.startActivity(Intent.createChooser(sendIntent, "Send email"));
                Toast.makeText(c, "Contattaci", Toast.LENGTH_SHORT).show();
                return true;

            //BOTTONE HELP

            case R.id.action_help:
                Toast.makeText(c, "Aiuto", Toast.LENGTH_SHORT).show();
                return true;

            //BOTTONE INFO

            case R.id.action_info:

                if (c.getClass().getName().toString().equals(InfoActivity.class.getName()))  {
                    Toast.makeText(c, "classe uguale", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Intent info = new Intent(c, InfoActivity.class);
                    c.startActivity(info);
                    Toast.makeText(c, "Info", Toast.LENGTH_SHORT).show();
                    return true;
                }
        }
        return false;
    }
}
