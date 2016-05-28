package ium.progetto.iseeshop;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by robin on 28/05/2016.
 */
public class CustomToolBar extends Activity {


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
