package loaiza.andres.it.coderdojo_firenze.Activities.Main.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import loaiza.andres.it.coderdojo_firenze.R;

/**
 * Created by Garu on 06/01/2015.
 */
public class FragmentInfos extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_main_info, container, false);
        return myView;
    }


}
