package ium.progetto.iseeshop;

import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;


public class MainActivity extends ActivityGroup implements customToolBarInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //crezione barra custom per il task manager
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        ActivityManager.TaskDescription taskDesc =
                new ActivityManager.TaskDescription("iSeeShop", bm, getResources().getColor(R.color.coloreStatusBar));
        this.setTaskDescription(taskDesc);



        //Set colore barra di stato

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.coloreStatusBar)); //ffb74d 075e55

        Intent scansione = new Intent(this, Scansione.class);
        Intent carrello = new Intent(this, Carrello.class);


        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup(this.getLocalActivityManager());

        final IndicatorView indicatore1 = new IndicatorView(getApplicationContext());
        indicatore1.setText("Scansione");

        final IndicatorView indicatore2 = new IndicatorView(getApplicationContext());
        indicatore2.setText("Carrello");
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Scansione")
                .setContent(scansione)
                .setIndicator(indicatore1);

        indicatore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicatore1.setSelected(true);
                indicatore2.setSelected(false);
            }
        });



        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Carrello")
                .setContent(carrello)
                .setIndicator(indicatore2);


        indicatore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicatore2.setSelected(true);
                indicatore1.setSelected(false);
            }
        });
        host.addTab(spec);

        if (sp.getBoolean("scansione",true)) {
            host.setCurrentTab(0);
        } else {
            host.setCurrentTab(1);
        }
    }


    @Override
    public void goHome(View v) {

    }

    @Override
    public void showPopup(View v) {
        CustomToolBar.show(this, v);
    }

}