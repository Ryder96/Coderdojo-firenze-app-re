package coderdojo.firenze.Activities.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import coderdojo.firenze.Activities.Main.Fragments.FragmentEventList;
import coderdojo.firenze.Activities.Main.Fragments.FragmentInfos;
import coderdojo.firenze.Activities.Main.Fragments.FragmentNews;

public class TabPagerAdapterMain extends FragmentStatePagerAdapter {


    private FragmentNews _fragmentNews = null;
    private FragmentEventList _fragmentEventList = null;
    private FragmentInfos _fragmentInfos = null;

    public TabPagerAdapterMain(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return getFragmentEventList();
            case 1:
                return getFragmentInfos();
            case 2:
                return getFragmentNews();
        }
        return null;
    }

    private FragmentNews getFragmentNews() {
        if (_fragmentNews == null)
            _fragmentNews = new FragmentNews();
        return _fragmentNews;
    }

    private FragmentEventList getFragmentEventList() {
        if (_fragmentEventList == null)
            _fragmentEventList = new FragmentEventList();
        return _fragmentEventList;
    }

    private FragmentInfos getFragmentInfos() {
        if (_fragmentInfos == null)
            _fragmentInfos = new FragmentInfos();
        return _fragmentInfos;
    }

    @Override
    public int getCount() {
        return 3; //N. of tabs
    }
}