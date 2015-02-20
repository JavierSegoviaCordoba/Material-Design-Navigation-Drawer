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
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
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
    ImageView imageViewDrawerArrowUpDown;

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

        String urlPictureMain, urlCoverMain, urlPictureSecond;
        urlPictureMain = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/v/t1.0-1/p200x200/1424388_10151792392315878_146499977_n.jpg?oh=1201b30a151e242bc39e67d6aba32b86&oe=554BD3DE&__gda__=1431136939_25c3b0c44d23dc6c5d9153757f08d3f2";
        urlCoverMain = "https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xpa1/v/t35.0-12/p180x540/1473382_10151795016155878_455139729_o.jpg?oh=d5b18d06dddbf883cae6eac7796f1716&oe=54E79A6C&__gda__=1424516730_6c8a8de7f91a2ea1b54948011a1145a7";
        urlPictureSecond = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xaf1/v/t1.0-1/p320x320/10268475_617322955025149_5688820484112978497_n.png?oh=a2a775ba30e9fbdb54adad6b6f5cb58b&oe=55841FF5&__gda__=1435757385_769c223771a667e654d89b5723275b0c";

        ImageView imageViewPictureMain, imageViewCoverMain, imageViewPictureSecond;
        imageViewPictureMain = (ImageView) findViewById(R.id.imageViewPictureMain);
        imageViewCoverMain = (ImageView) findViewById(R.id.imageViewCover);
        imageViewPictureSecond = (ImageView) findViewById(R.id.imageViewPictureSecond);

        Picasso.with(getApplicationContext()).load(urlPictureMain).transform(new CircleTransformWhite()).into(imageViewPictureMain);
        Picasso.with(getApplicationContext()).load(urlCoverMain).into(imageViewCoverMain);
        Picasso.with(getApplicationContext()).load(urlPictureSecond).transform(new CircleTransformWhite()).into(imageViewPictureSecond);

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
                imageViewDrawerArrowUpDown = (ImageView) findViewById(R.id.imageViewDrawerArrowUpDown);
                if (linearLayoutDrawerAccount.getVisibility() == View.VISIBLE) {
                    linearLayoutDrawerAccount.setVisibility(View.GONE);
                    linearLayoutDrawerMain.setVisibility(View.VISIBLE);
                    Animation animation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(500);
                    imageViewDrawerArrowUpDown.startAnimation(animation);
                    imageViewDrawerArrowUpDown.setBackgroundResource(R.drawable.ic_navigation_arrow_drop_up);
                } else {
                    linearLayoutDrawerAccount.setVisibility(View.VISIBLE);
                    linearLayoutDrawerMain.setVisibility(View.GONE);
                    Animation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(500);
                    imageViewDrawerArrowUpDown.startAnimation(animation);
                    imageViewDrawerArrowUpDown.setBackgroundResource(R.drawable.ic_navigation_arrow_drop_down);
                }
                break;
        }
    }
}
