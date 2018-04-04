package motiur_bdresult.bd.com.bdresult;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class govtResult extends AppCompatActivity {

    //Button bcsResult, nonCaderResult, devisionResult, seniorScale;

   // TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_result);

        //Bar color
        ActionBar webActivity = getSupportActionBar();
        webActivity.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        if (webActivity != null) {
            webActivity.setDisplayHomeAsUpEnabled(true);
            webActivity.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        //

//        bcsResult = (Button)findViewById(R.id.bcsResult);
//        nonCaderResult = (Button)findViewById(R.id.nonCaderResult);
//        devisionResult = (Button)findViewById(R.id.devisionResult);
//        seniorScale = (Button)findViewById(R.id.seniorScale);
       // textView = (TextView) findViewById(R.id.textView);

        // Load an ad into the AdMob banner view.
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        // MobileAds.initialize(this, "ca-app-pub-1090282204928094~4758004250");



    }

    public void bcs_Result(View view){

        String webURL = "http://www.bpsc.gov.bd/site/view/psc_exam/BCS%20Examination/%E0%A6%AC%E0%A6%BF%E0%A6%B8%E0%A6%BF%E0%A6%8F%E0%A6%B8-%E0%A6%AA%E0%A6%B0%E0%A7%80%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE";

        Intent intent = new Intent(this, BdResultActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }
    public void nonCaderResult(View view){

        String webURL = "http://www.bpsc.gov.bd/site/view/psc_exam/Non-Cadre%20Examination/%E0%A6%A8%E0%A6%A8-%E0%A6%95%E0%A7%8D%E0%A6%AF%E0%A6%BE%E0%A6%A1%E0%A6%BE%E0%A6%B0-%E0%A6%AA%E0%A6%B0%E0%A7%80%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE";

        Intent intent = new Intent(this, BdResultActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }
    public void devisionResult(View view){
        String webURL = "http://www.bpsc.gov.bd/site/view/psc_exam/Departmental%20Examination/%E0%A6%AC%E0%A6%BF%E0%A6%AD%E0%A6%BE%E0%A6%97%E0%A7%80%E0%A7%9F-%E0%A6%AA%E0%A6%B0%E0%A7%80%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE";

        Intent intent = new Intent(this, BdResultActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }

    public void seniorScale(View view){
        String webURL = "http://www.bpsc.gov.bd/site/view/psc_exam/Senior%20Scale%20Examination/%E0%A6%B8%E0%A6%BF%E0%A6%A8%E0%A6%BF%E0%A7%9F%E0%A6%B0-%E0%A6%B8%E0%A7%8D%E0%A6%95%E0%A7%87%E0%A6%B2-%E0%A6%AA%E0%A6%B0%E0%A7%80%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE";

        Intent intent = new Intent(this, BdResultActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.govtresult, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_close) {
            finish();

            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            //  overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }


        return super.onOptionsItemSelected(item);
    }


}
