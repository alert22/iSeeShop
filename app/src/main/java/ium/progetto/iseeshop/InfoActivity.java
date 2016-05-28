package ium.progetto.iseeshop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by robin on 28/05/2016.
 */

public class InfoActivity extends Activity {

    TextView testoInfo;
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
        testoInfo = (TextView) findViewById(R.id.textInfo);
        testoInfoStr += testoInfoStr;
        testoInfo.setText(testoInfoStr);
    }

    public void showPopup(View v) {
        CustomPopup.show(this, v);
    }
}
