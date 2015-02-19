package com.videum.javier.materialdesignnavigationdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;
import com.videum.javier.materialdesignnavigationdrawer.Utils.CircleTransformWhite;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    Button buttonRedLight, buttonRedDark, buttonIndigoLight, buttonIndigoDark;
    ToggleButton toggleButtonDrawer;
    LinearLayout linearLayoutDrawerAccount, linearLayoutDrawerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Navigation Drawer App");

        setupNavigationDrawer();

        setupButtons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupTheme() {
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        switch (sharedPreferences.getString("THEME", "REDLIGHT")) {
            case "REDLIGHT":
                setTheme(R.style.AppThemeRedLight);
                break;
            case "REDDARK":
                setTheme(R.style.AppThemeRedDark);
                break;
            case "INDIGOLIGHT":
                setTheme(R.style.AppThemeIndigoLight);
                break;
            case "INDIGODARK":
                setTheme(R.style.AppThemeIndigoDark);
                break;
        }
    }

    public void setupNavigationDrawer() {

        // Setup Navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Setup Drawer Icon
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        String urlPicture, urlCover;
        urlPicture = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/v/t1.0-1/p200x200/1424388_10151792392315878_146499977_n.jpg?oh=1201b30a151e242bc39e67d6aba32b86&oe=554BD3DE&__gda__=1431136939_25c3b0c44d23dc6c5d9153757f08d3f2";
        urlCover = "https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xpa1/v/t35.0-12/p180x540/1473382_10151795016155878_455139729_o.jpg?oh=d5b18d06dddbf883cae6eac7796f1716&oe=54E79A6C&__gda__=1424516730_6c8a8de7f91a2ea1b54948011a1145a7";

        ImageView imageViewPicture, imageViewCover;
        imageViewPicture = (ImageView) findViewById(R.id.imageViewPicture);
        imageViewPicture.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        imageViewCover = (ImageView) findViewById(R.id.imageViewCover);

        Picasso.with(getApplicationContext()).load(urlPicture).transform(new CircleTransformWhite()).into(imageViewPicture);
        Picasso.with(getApplicationContext()).load(urlCover).into(imageViewCover);

        TypedValue typedValue = new TypedValue();
        MainActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        final int color = typedValue.data;
        drawerLayout.setStatusBarBackgroundColor(color);

        toggleButtonDrawer = (ToggleButton) findViewById(R.id.toggleButtonDrawer);
        toggleButtonDrawer.setOnClickListener(this);
    }

    public void setupButtons() {
        buttonRedLight = (Button) findViewById(R.id.buttonRedLight);
        buttonRedLight.setOnClickListener(this);
        buttonRedDark = (Button) findViewById(R.id.buttonRedDark);
        buttonRedDark.setOnClickListener(this);
        buttonIndigoLight = (Button) findViewById(R.id.buttonIndigoLight);
        buttonIndigoLight.setOnClickListener(this);
        buttonIndigoDark = (Button) findViewById(R.id.buttonIndigoDark);
        buttonIndigoDark.setOnClickListener(this);
    }

    // All onClick for all views
    @Override
    public void onClick(View v) {
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);

        switch (v.getId()) {
            case R.id.buttonRedLight:
                sharedPreferences.edit().putString("THEME", "REDLIGHT").apply();
                startActivity(intent);
                break;
            case R.id.buttonRedDark:
                sharedPreferences.edit().putString("THEME", "REDDARK").apply();
                startActivity(intent);
                break;
            case R.id.buttonIndigoLight:
                sharedPreferences.edit().putString("THEME", "INDIGOLIGHT").apply();
                startActivity(intent);
                break;
            case R.id.buttonIndigoDark:
                sharedPreferences.edit().putString("THEME", "INDIGODARK").apply();
                startActivity(intent);
                break;
            case R.id.toggleButtonDrawer:
                linearLayoutDrawerAccount = (LinearLayout) findViewById(R.id.linearLayoutDrawerAccounts);
                linearLayoutDrawerMain = (LinearLayout) findViewById(R.id.linearLayoutDrawerMain);
                if (linearLayoutDrawerAccount.getVisibility() == View.VISIBLE){
                    linearLayoutDrawerAccount.setVisibility(View.GONE);
                    linearLayoutDrawerMain.setVisibility(View.VISIBLE);
                }else {
                    linearLayoutDrawerAccount.setVisibility(View.VISIBLE);
                    linearLayoutDrawerMain.setVisibility(View.GONE);
                }
                break;
        }
    }
}
