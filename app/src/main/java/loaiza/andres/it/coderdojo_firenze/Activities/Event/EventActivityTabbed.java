package loaiza.andres.it.coderdojo_firenze.Activities.Event;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import loaiza.andres.it.coderdojo_firenze.Activities.Main.TabPagerAdapterMain;
import loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite.BriteEventsAdapter;
import loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite.BriteListItem;
import loaiza.andres.it.coderdojo_firenze.Gui.DojoActivityHelper;
import loaiza.andres.it.coderdojo_firenze.Helpers.GsonHelper;
import loaiza.andres.it.coderdojo_firenze.R;


public class EventActivityTabbed extends ActionBarActivity implements MaterialTabListener {
    MaterialTabHost tabHost;
    ViewPager pager;
    TabPagerAdapterMain pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DojoActivityHelper.setupLayout(this);
        super.setContentView(R.layout.activity_main_event);
        BriteListItem currentItem = (BriteListItem) GsonHelper.get(getIntent().getStringExtra(BriteEventsAdapter.INTENT_ITEM), BriteListItem.class);
        this.initLayout();
        if (currentItem != null)
            this.setupTabs(currentItem);
    }

    private void initLayout() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.eventToolbar);
        this.setSupportActionBar(toolbar);

        tabHost = (MaterialTabHost) this.findViewById(R.id.eventTabHost);
        pager = (ViewPager) this.findViewById(R.id.eventViewPager);

        pagerAdapter = new TabPagerAdapterMain(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        tabHost.addTab(tabHost.newTab().setTabListener(this).setText("Informazioni Evento"));
        tabHost.addTab(tabHost.newTab().setTabListener(this).setText("Map"));

    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {

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
