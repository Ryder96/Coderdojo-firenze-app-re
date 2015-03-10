package coderdojo.firenze.Activities.Event.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import coderdojo.firenze.Activities.Event.TabPagerAdapterEvents;
import coderdojo.firenze.Activities.Main.MainActivity;
import coderdojo.firenze.CustomControls.EventBrite.BriteListItem;
import coderdojo.firenze.Helpers.GsonHelper;
import coderdojo.firenze.Helpers.ResourcesHelper;
import coderdojo_firenze.R;


public class FragmentEventMap extends Fragment implements View.OnClickListener {

    private GoogleMap map;
    private BriteListItem currentItem;
    private ImageView pather;
    private LinearLayout optionContainer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_event_map, container, false);
        this.currentItem = (BriteListItem) GsonHelper.get(getArguments().getString(TabPagerAdapterEvents.BUNDLE_CURRENT_ITEM), BriteListItem.class);
        if (map == null)
            this.initMap();
        initView(myView);
        return myView;
    }

    private void initView(View myView) {
        pather = (ImageView) myView.findViewById(R.id.madePath);
        optionContainer = (LinearLayout) myView.findViewById(R.id.optionContainer);

        pather.setOnClickListener(this);
        optionContainer.setBackgroundColor(MainActivity.toolbarColor);
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

        map.setPadding(100, 0, 0, 0);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (map != null && !this.getActivity().isFinishing()) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.map)).commit();
            map = null;
        }
    }


    @Override
    public void onClick(View v) {
        if (v.equals(pather)) {
            if (map.getMyLocation() == null) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(), "Attivare gps", Toast.LENGTH_SHORT);
                myToast.show();
            } else {
                Polyline line = map.addPolyline(new PolylineOptions()
                        .add(new LatLng(map.getMyLocation().getLatitude(), map.getMyLocation().getLongitude()),
                                new LatLng(currentItem.getLatLng().latitude, currentItem.getLatLng().longitude))
                        .width(25)
                        .color(MainActivity.toolbarColor)
                        .geodesic(true));

            }
        }
    }
}
