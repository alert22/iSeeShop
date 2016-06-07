package ium.progetto.iseeshop;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by robin on 28/05/2016.
 */

public class InfoActivity extends Activity implements customToolBarInterface{

    TextView testoInfo;
    TextView nomeActivity;

    String testoInfoStr = "L’app iSeeShop è stata realizzata presso l’Università degli Studi di Salerno," +
            " dal team composto dagli studenti:\n\nAlina Korniychuk,\nPietro Russo,\nRoberto Tortora, \nRaffaele" +
            " Sibilia.\n\nNell’ambito di un progetto universitario del corso di “Interazione Uomo-Macchina”.\n" +
            "\n" +
            "Versione 1.0\n" +
            "\n" +
            "© 2015-2016";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);

        nomeActivity = (TextView)findViewById(R.id.nomeActivity);
        nomeActivity.setText("Info");

        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        ActivityManager.TaskDescription taskDesc =
                new ActivityManager.TaskDescription("iSeeShop", bm, getResources().getColor(R.color.coloreStatusBar));
        this.setTaskDescription(taskDesc);

        //Set colore barra di stato

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.coloreStatusBar));

        testoInfo = (TextView) findViewById(R.id.textInfo);
        testoInfo.setText(testoInfoStr);
    }

    @Override
    public void goHome(View v) {
            this.finish();
    }

    public void showPopup(View v) {
        CustomToolBar.show(this, v);
    }
}
