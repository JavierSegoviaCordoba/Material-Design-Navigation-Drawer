package com.videum.javier.materialdesignnavigationdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;
import com.videum.javier.materialdesignnavigationdrawer.Utils.CircleTransform;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    Button buttonRedLight, buttonRedDark, buttonIndigoLight, buttonIndigoDark;
    ToggleButton toggleButtonDrawer;
    FrameLayout statusBar, frameLayoutSetting1, frameLayoutSetting2;
    LinearLayout linearLayoutDrawerAccount, linearLayoutDrawerMain, linearLayoutScrollViewChild;
    ImageView imageViewDrawerArrowUpDown;
    ScrollView scrollViewNavigationDrawerContent;
    ViewTreeObserver viewTreeObserverNavigationDrawerScrollView;
    ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;

    float drawerHeight, scrollViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setupTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup toolbar and statusBar (really FrameLayout)
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Material Navigation Drawer");
        statusBar = (FrameLayout) findViewById(R.id.statusBar);

        // Setup navigation drawer
        setupNavigationDrawer();

        // Setup buttons to change theme app
        setupButtons();

        // Hide Settings and Feedback buttons when navigation drawer is scrolled
        hideNavigationDrawerSettingsAndFeedbackOnScroll();

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
        switch (sharedPreferences.getString("THEME", "INDIGOLIGHT")) {
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
        urlPictureMain = "https://lh4.googleusercontent.com/-LEwBpvgLyOM/AAAAAAAAAAI/AAAAAAAAHJM/CbQbbI7w1Bc/s120-c/photo.jpg";
        urlCoverMain = "https://lh6.googleusercontent.com/-2RQc20WjV-8/VRIFuAiNzbI/AAAAAAAAArk/dEZZEWJqkUs/w1474-h829-no/cover-no-words.png";
        urlPictureSecond = "https://lh3.googleusercontent.com/-fIIhUhsMF3k/AAAAAAAAAAI/AAAAAAAAAp4/RPUESUibS6U/s120-c/photo.jpg";

        ImageView imageViewPictureMain, imageViewCoverMain, imageViewPictureSecond;
        imageViewPictureMain = (ImageView) findViewById(R.id.imageViewPictureMain);
        imageViewCoverMain = (ImageView) findViewById(R.id.imageViewCover);
        imageViewPictureSecond = (ImageView) findViewById(R.id.imageViewPictureSecond);

        Picasso.with(getApplicationContext()).load(urlPictureMain).transform(new CircleTransform()).into(imageViewPictureMain);
        Picasso.with(getApplicationContext()).load(urlCoverMain).into(imageViewCoverMain);
        Picasso.with(getApplicationContext()).load(urlPictureSecond).transform(new CircleTransform()).into(imageViewPictureSecond);

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

    private void hideNavigationDrawerSettingsAndFeedbackOnScroll() {

        scrollViewNavigationDrawerContent = (ScrollView) findViewById(R.id.scrollViewNavigationDrawerContent);
        linearLayoutScrollViewChild = (LinearLayout) findViewById(R.id.linearLayoutScrollViewChild);
        frameLayoutSetting1 = (FrameLayout) findViewById(R.id.frameLayoutSettings1);
        frameLayoutSetting2 = (FrameLayout) findViewById(R.id.frameLayoutSettings2);

        viewTreeObserverNavigationDrawerScrollView = linearLayoutScrollViewChild.getViewTreeObserver();

        if (viewTreeObserverNavigationDrawerScrollView.isAlive()) {
            viewTreeObserverNavigationDrawerScrollView.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (Build.VERSION.SDK_INT > 16) {
                        linearLayoutScrollViewChild.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        linearLayoutScrollViewChild.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                    drawerHeight = linearLayoutScrollViewChild.getHeight();
                    scrollViewHeight = scrollViewNavigationDrawerContent.getHeight();

                    if (drawerHeight > scrollViewHeight) {
                        frameLayoutSetting1.setVisibility(View.VISIBLE);
                        frameLayoutSetting2.setVisibility(View.VISIBLE);
                    }

                    if (drawerHeight < scrollViewHeight) {
                        frameLayoutSetting1.setVisibility(View.GONE);
                        frameLayoutSetting2.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                scrollViewNavigationDrawerContent.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE:
                                if (scrollViewNavigationDrawerContent.getScrollY() != 0) {
                                    frameLayoutSetting1.animate().translationY(frameLayoutSetting1
                                            .getHeight()).setInterpolator(new AccelerateInterpolator(5f)).setDuration(400);
                                }
                                break;
                        }
                        return false;
                    }
                });

                if (scrollViewNavigationDrawerContent.getScrollY() == 0) {
                    frameLayoutSetting1.animate().translationY(0)
                            .setInterpolator(new DecelerateInterpolator(5f)).setDuration(600);
                }

            }
        };

        scrollViewNavigationDrawerContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        ViewTreeObserver observer;
                        observer = scrollViewNavigationDrawerContent.getViewTreeObserver();
                        observer.addOnScrollChangedListener(onScrollChangedListener);
                        break;
                }

                return false;
            }
        });

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
