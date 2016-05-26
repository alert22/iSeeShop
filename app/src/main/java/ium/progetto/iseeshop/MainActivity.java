package ium.progetto.iseeshop;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent scansione = new Intent(this, Scansione.class);
        Intent carrello = new Intent(this, Carrello.class);


        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup(this.getLocalActivityManager());

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Scansione")
                .setContent(scansione)
                .setIndicator("Scansione");
        host.addTab(spec);


        //Tab 2
        spec = host.newTabSpec("Carrello")
                .setContent(carrello)
                .setIndicator("Carrello");
        host.addTab(spec);
    }

}