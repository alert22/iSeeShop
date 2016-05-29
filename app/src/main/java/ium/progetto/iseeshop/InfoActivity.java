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

    String testoInfoStr = "Lorem ipsum dolor sit amet, consectetur adipisci elit," +
            " sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut" +
            " enim ad minim veniam, quis nostrum exercitationem ullam corporis" +
            " suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. " +
            "Quis aute iure reprehenderit in voluptate velit esse cillum dolore" +
            " eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non" +
            " proident, sunt in culpa qui officia deserunt mollit anim id est" +
            " laborum.";

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
        testoInfoStr += testoInfoStr;
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
