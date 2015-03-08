package loaiza.andres.it.coderdojo_firenze.Activities.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import loaiza.andres.it.coderdojo_firenze.Activities.Main.Fragments.FragmentCopyright;
import loaiza.andres.it.coderdojo_firenze.Helpers.ColorPickerPKG.ColorPickerDialog;
import loaiza.andres.it.coderdojo_firenze.R;


public class MainActivity extends ActionBarActivity implements MaterialTabListener, View.OnClickListener {

    public static final String COLOR = "COLORTOOLBAR";
    MaterialTabHost tabHost;
    ViewPager pager;
    TabPagerAdapterMain pagerAdapter;
    Toolbar toolbar;
    LinearLayout links;
    int toolbarColor = Color.BLACK;
    private ImageView viewFacebook, viewTwitter, viewCopyright, viewDojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        setupTabs();
        initLayout();


    }

    private void setupTabs() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        links = (LinearLayout) findViewById(R.id.linkContainer);
        this.setSupportActionBar(toolbar);

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.viewpager);

        pagerAdapter = new TabPagerAdapterMain(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        tabHost.addTab(tabHost.newTab().setTabListener(this).setText("Eventi"));
        tabHost.addTab(tabHost.newTab().setTabListener(this).setText("Info"));
        tabHost.addTab(tabHost.newTab().setTabListener(this).setText("News"));

        pager.setCurrentItem(1);
    }


    private void initLayout() {
        this.viewFacebook = (ImageView) findViewById(R.id.infoFacebook);
        this.viewTwitter = (ImageView) findViewById(R.id.infoTwitter);
        this.viewCopyright = (ImageView) findViewById(R.id.infoCopyright);
        this.viewDojo = (ImageView) findViewById(R.id.infoSite);

        viewFacebook.setOnClickListener(this);
        viewTwitter.setOnClickListener(this);
        viewCopyright.setOnClickListener(this);
        viewDojo.setOnClickListener(this);

        toolbar.setBackgroundColor(toolbarColor);
        tabHost.setBackgroundColor(toolbarColor);
        tabHost.setPrimaryColor(toolbarColor);
        links.setBackgroundColor(toolbarColor);
    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    public void onClick(View view) {
        if (view.equals(viewFacebook))
            openUrl("https://www.facebook.com/CoderDojoFirenze?fref=ts");
        else if (view.equals(viewTwitter))
            openUrl("https://twitter.com/coderdojofi");
        else if (view.equals(viewDojo))
            openUrl("http://firenze.coderdojo.it/");
        else if (view.equals(viewCopyright)) {
            FragmentCopyright dialog = new FragmentCopyright();
            dialog.show(this.getSupportFragmentManager(), "Description");

        }

    }

    private void openUrl(String url) {
        Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(fbIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, toolbarColor, new ColorPickerDialog.OnColorSelectedListener() {


                @Override
                public void onColorSelected(int color) {
                    toolbar.setBackgroundColor(color);
                    tabHost.setBackgroundColor(color);
                    tabHost.setPrimaryColor(color);
                    links.setBackgroundColor(color);
                    saveData(color);
                }
            });
            colorPickerDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveData(int color) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(COLOR, color);
        editor.commit();
    }

    private void loadData() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int colortmp = sharedPref.getInt(COLOR, toolbarColor);
        if (colortmp != Color.BLACK) {
            toolbarColor = colortmp;
        }
    }


}
