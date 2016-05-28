package ium.progetto.iseeshop;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;


public class Scansione extends FragmentActivity {
    ImageView immagineNfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scansione_layout);
        immagineNfc = (ImageView) findViewById(R.id.nfc);
        immagineNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(getString(R.string.titleAlert)) //
                        .setMessage(getString(R.string.textAlert)) //
                        .setPositiveButton(getString(R.string.showProduct), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO
                                dialog.dismiss();
                            }
                        }) //
                        .setNegativeButton(getString(R.string.ignoreProduct), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO
                                dialog.dismiss();
                            }
                        });
                builder.show();

            }
        });
    }
}

