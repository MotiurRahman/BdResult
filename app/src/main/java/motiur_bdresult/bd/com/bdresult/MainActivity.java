package motiur_bdresult.bd.com.bdresult;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;


import com.google.android.gms.ads.MobileAds;
import com.onesignal.OneSignal;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private InterstitialAd mInterstitialAd;

    // Button primaryResult1, primaryResult2,seconderyResult1,seconderyResult2, nuReslt,nuAdmission_result, publicResult, govtResult;

//    private static final int PLUS_ONE_REQUEST_CODE = 0;
//    private static final String URL = "https://play.google.com/store/apps/details?id=motiur_bdjobs.bd.com.allbdjobs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-4951262838901192~5542320854");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#00796B"));
        }


//        primaryResult1 = (Button) findViewById(R.id.primaryResult1);
//        primaryResult2 = (Button) findViewById(R.id.primaryResult2);
//        seconderyResult1 = (Button) findViewById(R.id.seconderyResult1);
//        seconderyResult2 = (Button) findViewById(R.id.seconderyResult2);
//        nuAdmission_result = (Button) findViewById(R.id.nuAdmission_result);
//        nuReslt = (Button) findViewById(R.id.nuReslt);
//        publicResult = (Button) findViewById(R.id.publicResult);
//        govtResult = (Button) findViewById(R.id.govtResult);


        // One Signal

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        //Floting action button

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                if (isNetworkConnected()) {

                    Intent i = new Intent(Intent.ACTION_VIEW);

                    i.setData(Uri.parse("market://details?id=motiur_bdresult.bd.com.bdresult"));
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                } else {
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Floting action button


        // Load an ad into the AdMob banner view.
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);



        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Interestitial Ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        //End Interestitial Ad
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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


        if (id == R.id.action_update) {

            if (isNetworkConnected()) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setData(Uri.parse("market://details?id=motiur_bdresult.bd.com.bdresult"));
                startActivity(i);
                //  overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        }

        if (id == R.id.action_close) {

            finish();
            System.exit(0);

        }


        if (id == R.id.action_more) {

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

        if (id == R.id.sms_result) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            //Handle the camera action

            String webURL = "file:///android_asset/smsSystem.html";


            //  bdresult.loadUrl(webURL);

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else if (id == R.id.review_result) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            // Handle the camera action

            String webURL = "file:///android_asset/reviewResult.html";


            //  bdresult.loadUrl(webURL);

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else if (id == R.id.examRoutineSSC) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            // Handle the camera action

            String URL = "https://drive.google.com/file/d/1FNsVSHhH7P2tRyG7Buoss-fF9rwxEudz/view";


            if (isNetworkConnected()) {

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }


        } else if (id == R.id.examRoutineHSC) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            // Handle the camera action

            String URL = "https://dhakaeducationboard.gov.bd/data/20181122102109185706.pdf";


            if (isNetworkConnected()) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(URL));
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.buet) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            String URL = "http://www.buet.ac.bd/Home/Admission";
            if (isNetworkConnected()) {

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.medical) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            String URL = "http://result.dghs.gov.bd/";
            if (isNetworkConnected()) {

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.du) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            String URL = "http://admission.eis.du.ac.bd/";
            if (isNetworkConnected()) {

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nuAdmission) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            String URL = "http://app1.nu.edu.bd/";
            if (isNetworkConnected()) {

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //End Menu Button


    //Button Click

    public void primaryResult1(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://202.51.191.190:5839/";


        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }


    }


    public void primaryResult2(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://dperesult.teletalk.com.bd/dpe.php";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }

    public void primaryResult3(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "https://dpe.portal.gov.bd/site/page/f5584576-5e2f-465f-b4a6-c2e992184d3f";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void seconderyResult1(View view) {


        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        //Handle the camera action


        String webURL = "https://eboardresults.com/app/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }
    }

    public void seconderyResult2(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        String webURL = "http://www.educationboardresults.gov.bd/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }
    }


    public void nuResult(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://www.nu.ac.bd/results/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void nu_all_AdmissionResult(View view) {


        Intent intent = new Intent(this, nu_admission.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);


    }


    public void bteb_result(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "https://sites.google.com/site/resultbteb/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void bou_final(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://www.bou.ac.bd/result.php";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void bou_details(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://exam.bou.edu.bd/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void polytechnic_admission(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://www.btebadmission.gov.bd/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void dhakaPolytechnic_result(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://dpi.gov.bd/academics/result/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void ntrca(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://ntrca.teletalk.com.bd/result/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }

    public void ntrca2(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://ngi.teletalk.com.bd/ntrca/app/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }



    public void bcps(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        String webURL = "http://bcpsbd.org/result/";

        if (isNetworkConnected()) {

            Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
            intent.putExtra("URL", webURL);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }


    public void publicResult(View view) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Comming Soon")
                .setTitle("Admission Result");

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    public void govtResult(View view) {


        Intent intent = new Intent(this, govtResult.class);
        startActivity(intent);
        // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }
}
