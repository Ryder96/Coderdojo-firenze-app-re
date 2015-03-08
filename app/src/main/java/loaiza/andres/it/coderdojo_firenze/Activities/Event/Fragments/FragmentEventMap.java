package loaiza.andres.it.coderdojo_firenze.Activities.Event.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import loaiza.andres.it.coderdojo_firenze.Activities.Event.TabPagerAdapterEvents;
import loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite.BriteListItem;
import loaiza.andres.it.coderdojo_firenze.Helpers.GsonHelper;
import loaiza.andres.it.coderdojo_firenze.Helpers.ResourcesHelper;
import loaiza.andres.it.coderdojo_firenze.R;


public class FragmentEventMap extends Fragment {

    private GoogleMap map;
    private BriteListItem currentItem;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_event_map, container, false);
        this.currentItem = (BriteListItem) GsonHelper.get(getArguments().getString(TabPagerAdapterEvents.BUNDLE_CURRENT_ITEM), BriteListItem.class);
        if (map == null)
            this.initMap();
        return myView;
    }

    private void initMap() {

        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentItem.getLatLng(), 14));
        map.setMyLocationEnabled(true);

        if (map.getMyLocation() != null)
            map.addMarker(new MarkerOptions()
                            .position(currentItem.getLatLng())
                            .title("La tua posizione")
            );

        map.addMarker(new MarkerOptions()
                        .position(currentItem.getLatLng())
                        .title("Coder Dojo")
                        .icon(ResourcesHelper.getBitmapFormRes(this.getActivity().getApplicationContext(), R.drawable.ic_launcher))
        );

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (map != null && !this.getActivity().isFinishing()) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.map)).commit();
            map = null;
        }
    }


}
