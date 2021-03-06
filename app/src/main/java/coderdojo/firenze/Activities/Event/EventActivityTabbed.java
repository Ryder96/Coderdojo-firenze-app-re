package coderdojo.firenze.Activities.Event;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import coderdojo.firenze.Activities.Main.MainActivity;
import coderdojo.firenze.CustomControls.EventBrite.BriteEventsAdapter;
import coderdojo.firenze.CustomControls.EventBrite.BriteListItem;
import coderdojo.firenze.Gui.DojoActivityHelper;
import coderdojo.firenze.Helpers.GsonHelper;
import coderdojo_firenze.R;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class EventActivityTabbed extends ActionBarActivity implements MaterialTabListener {
    MaterialTabHost tabHost;
    ViewPager pager;
    TabPagerAdapterEvents pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DojoActivityHelper.setupLayout(this);
        super.setContentView(R.layout.activity_main_event);
        BriteListItem currentItem = (BriteListItem) GsonHelper.get(getIntent().getStringExtra(BriteEventsAdapter.INTENT_ITEM), BriteListItem.class);
        this.initLayout(currentItem);
        if (currentItem != null)
            this.setupTabs(currentItem);
    }

    private void initLayout(BriteListItem currentItem) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.eventToolbar);
        this.setSupportActionBar(toolbar);

        tabHost = (MaterialTabHost) this.findViewById(R.id.eventTabHost);
        pager = (ViewPager) this.findViewById(R.id.eventViewPager);

        pagerAdapter = new TabPagerAdapterEvents(getSupportFragmentManager(), currentItem);
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        tabHost.addTab(tabHost.newTab().setTabListener(this).setText("Informazioni Evento"));
        tabHost.addTab(tabHost.newTab().setTabListener(this).setText("Map"));


        tabHost.setPrimaryColor(MainActivity.toolbarColor);
        toolbar.setBackgroundColor(MainActivity.toolbarColor);
        tabHost.setBackgroundColor(MainActivity.toolbarColor);
        tabHost.setPrimaryColor(MainActivity.toolbarColor);
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

    public void setupTabs(BriteListItem upTabs) {


    }
}
