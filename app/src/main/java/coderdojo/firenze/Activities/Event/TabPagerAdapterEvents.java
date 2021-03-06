package coderdojo.firenze.Activities.Event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import coderdojo.firenze.Activities.Event.Fragments.FragmentEventInfo;
import coderdojo.firenze.Activities.Event.Fragments.FragmentEventMap;
import coderdojo.firenze.CustomControls.EventBrite.BriteListItem;
import coderdojo.firenze.Helpers.GsonHelper;

public class TabPagerAdapterEvents extends FragmentStatePagerAdapter {

    public static final String BUNDLE_CURRENT_ITEM = "BUNDLE_ITEM";
    private BriteListItem currentItem;
    private FragmentEventInfo _fragmentEventInfo = null;
    private FragmentEventMap _fragmentEventMap = null;
    private Bundle _bundle = null;

    public TabPagerAdapterEvents(FragmentManager manager, BriteListItem currentItem) {
        super(manager);
        this.currentItem = currentItem;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return getInfoFragment();
            case 1:
                return getMapFragment();
        }
        return null;
    }

    private FragmentEventInfo getInfoFragment() {
        if (_fragmentEventInfo == null) {
            _fragmentEventInfo = new FragmentEventInfo();
            _fragmentEventInfo.setArguments(getArguments());
        }
        return _fragmentEventInfo;
    }

    private FragmentEventMap getMapFragment() {
        if (_fragmentEventMap == null) {
            _fragmentEventMap = new FragmentEventMap();
            _fragmentEventMap.setArguments(getArguments());
        }
        return _fragmentEventMap;
    }

    private Bundle getArguments() {
        if (_bundle == null) {
            _bundle = new Bundle();
            _bundle.putString(BUNDLE_CURRENT_ITEM, GsonHelper.convert(this.currentItem));
        }
        return _bundle;
    }


    @Override
    public int getCount() {
        return 2; //N. of tabs
    }
}