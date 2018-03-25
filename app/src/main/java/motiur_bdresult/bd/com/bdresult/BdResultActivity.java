package motiur_bdresult.bd.com.bdresult;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class BdResultActivity extends AppCompatActivity {

    WebView bdresult;
    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd_result);



        //Bar color
        ActionBar webActivity = getSupportActionBar();
        webActivity.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        if (webActivity != null) {
            webActivity.setDisplayHomeAsUpEnabled(true);
            webActivity.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        //


        //WebView
        bdresult = (WebView)findViewById(R.id.web1);
        WebSettings webSettings = bdresult.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Improve wevView performance

        bdresult.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        bdresult.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        bdresult.getSettings().setAppCacheEnabled(true);
        bdresult.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        bdresult.setInitialScale(1);
        bdresult.getSettings().setDisplayZoomControls(false);
        bdresult.getSettings().setBuiltInZoomControls(true);
        // chakrirkhobor.setVerticalScrollBarEnabled(false);
        bdresult.setHorizontalScrollBarEnabled(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);

        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);


        // Get URL

        String URL = getIntent().getStringExtra("URL");
        bdresult.loadUrl(URL);

        bdresult.setWebViewClient(new mywebClient());

        proBar = (ProgressBar)findViewById(R.id.progressBar1);



        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        // MobileAds.initialize(this, "ca-app-pub-1090282204928094~4758004250");

    }

    //For webview progress bar loading

    public class mywebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            proBar.setVisibility(View.GONE);
            setTitle(view.getTitle());


        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            proBar.setVisibility(View.VISIBLE);
            setTitle("Loading.....");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    //End webview progress bar

    //WebView back button

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (bdresult.canGoBack()) {
                        bdresult.goBack();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    //end WebView back button

    // For Menu button

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bdresultmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            finish();

            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
          //  overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }


        return super.onOptionsItemSelected(item);
    }

}