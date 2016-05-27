package ium.progetto.iseeshop;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;




public class MainActivity extends ActivityGroup {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#075e55"));

        Intent scansione = new Intent(this, Scansione.class);
        Intent carrello = new Intent(this, Carrello.class);



        TabHost host = (TabHost)findViewById(R.id.tabHost);
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



    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.show();
    }

}