package motiur_bdresult.bd.com.bdresult;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.onesignal.OneSignal;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private InterstitialAd mInterstitialAd;

    Button primaryResult, seconderyResult, nuReslt, publicResult, govtResult;

//    private static final int PLUS_ONE_REQUEST_CODE = 0;
//    private static final String URL = "https://play.google.com/store/apps/details?id=motiur_bdjobs.bd.com.allbdjobs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        primaryResult = (Button) findViewById(R.id.primaryResult);
        seconderyResult = (Button) findViewById(R.id.seconderyResult);
        nuReslt = (Button) findViewById(R.id.nuReslt);
        publicResult = (Button) findViewById(R.id.publicResult);
        govtResult = (Button) findViewById(R.id.govtResult);


        // One Signal

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        // MobileAds.initialize(this, "ca-app-pub-1090282204928094~4758004250");


        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//
//        // Interestitial Ad
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                // Load the next interstitial.
//                mInterstitialAd.loadAd(new AdRequest.Builder().build());
//            }
//
//        });
//
//        //End Interestitial Ad
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //For internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //End internet connection


    // For Menu button

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);
        // Fetch and store ShareActionProvider
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        if (mShareActionProvider != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=motiur_bdresult.bd.com.bdresult");
            shareIntent.setType("text/plain");
            mShareActionProvider.setShareIntent(shareIntent);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_ratings) {

            if (isNetworkConnected()) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=motiur_bdresult.bd.com.bdresult"));
                startActivity(i);
                //  overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }


        if (id == R.id.action_apps) {

            if (isNetworkConnected()) {
                Intent devAccount = new Intent(Intent.ACTION_VIEW);
                devAccount.setData(Uri.parse("http://play.google.com/store/apps/dev?id=6031616565948906744"));
                startActivity(devAccount);
                // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bcsExamSystem) {
//            if (mInterstitialAd.isLoaded()) {
//                mInterstitialAd.show();
//            } else {
//                Log.d("TAG", "The interstitial wasn't loaded yet.");
//            }
            // Handle the camera action

            String webURL = "http://www.bpsc.gov.bd/site/page/4bc95017-18d6-412b-8c4f-76d3e1599d8e/%E0%A6%AC%E0%A6%BF%E0%A6%B8%E0%A6%BF%E0%A6%8F%E0%A6%B8-%E0%A6%AA%E0%A6%B0%E0%A7%80%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE";


            //  bdresult.loadUrl(webURL);

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else if (id == R.id.nonCaderExamSystem) {
//            if (mInterstitialAd.isLoaded()) {
//                mInterstitialAd.show();
//            } else {
//                Log.d("TAG", "The interstitial wasn't loaded yet.");
//            }
            // Handle the camera action

            String URL = "http://www.bpsc.gov.bd/site/page/71290977-b0b2-414c-a3e4-e3c65677b9a6/%E0%A6%A8%E0%A6%A8-%E0%A6%95%E0%A7%8D%E0%A6%AF%E0%A6%BE%E0%A6%A1%E0%A6%BE%E0%A6%B0-%E0%A6%AA%E0%A6%B0%E0%A7%80%E0%A6%95%E0%A7%8D%E0%A6%B7%E0%A6%BE";


            // bdresult.loadUrl(URL);

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", URL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);

            // change the type of data you need to share,
            // for image use "image/*"
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=motiur_bdresult.bd.com.bdresult");
            startActivity(Intent.createChooser(intent, "Share"));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //End Menu Button


    //Button Click

    public void primaryResult(View view) {


        String webURL = "http://dperesult.teletalk.com.bd/dpe.php";

        Intent intent = new Intent(this, BdResultActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);


    }

    public void seconderyResult(View view) {

        String webURL = "http://www.educationboardresults.gov.bd/";

        Intent intent = new Intent(this, BdResultActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
    }


    public void nuResult(View view) {


        String webURL = "http://www.nu.edu.bd/results/";

        Intent intent = new Intent(this, BdResultActivity.class);
        intent.putExtra("URL", webURL);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }

    public void publicResult(View view) {

    }

    public void govtResult(View view) {


        Intent intent = new Intent(this, govtResult.class);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }
}