package loaiza.andres.it.coderdojo_firenze.Activities.Event.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import loaiza.andres.it.coderdojo_firenze.Activities.Event.TabPagerAdapterEvents;
import loaiza.andres.it.coderdojo_firenze.Activities.Main.MainActivity;
import loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite.BriteListItem;
import loaiza.andres.it.coderdojo_firenze.Helpers.GsonHelper;
import loaiza.andres.it.coderdojo_firenze.R;


public class FragmentEventInfo extends Fragment implements View.OnClickListener {

    public static final String BUNDLE_CURRENT_ITEM = "BUNDLE_ITEM";

    private BriteListItem currentItem;
    private TextView textViewTitle;
    private TextView textViewDate;
    private TextView textViewLocation;
    private TextView textViewDescription;
    private ImageView imageViewSite;

    private View myView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_event_info, container, false);
        this.currentItem = (BriteListItem) GsonHelper.get(getArguments().getString(TabPagerAdapterEvents.BUNDLE_CURRENT_ITEM), BriteListItem.class);
        this.initLayout(myView);
        return myView;

    }


    private void initLayout(View view) {
        this.textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        this.textViewTitle = (TextView) view.findViewById(R.id.textviewTitle);
        this.textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        this.textViewDescription = (TextView) view.findViewById(R.id.textViewEventDescription);
        this.imageViewSite = (ImageView) view.findViewById(R.id.imageViewLocation);
        if (this.currentItem != null)
            populateView(currentItem);
    }

    private void populateView(BriteListItem item) {
        this.textViewTitle.setText(item.getTitle());
        this.textViewDate.setText(item.getEventDate());
        this.textViewDescription.setText(item.getDescription());
        this.textViewLocation.setText(item.getLocation());
        this.currentItem = item;
        this.textViewDescription.setOnClickListener(this);
        this.imageViewSite.setOnClickListener(this);
        this.textViewDescription.setText(textViewDescription.getText().toString() + "...");
        this.imageViewSite.setColorFilter(MainActivity.toolbarColor);

    }

    private void openDescriptionDialog() {
        FragmentDescriptionEvent dialog = new FragmentDescriptionEvent();
        Bundle bundle = new Bundle();
        bundle.putString(this.BUNDLE_CURRENT_ITEM, GsonHelper.convert(this.currentItem));
        dialog.setArguments(bundle);
        dialog.show(getActivity().getSupportFragmentManager(), "Description");
    }


    @Override
    public void onClick(View v) {
        if (v.equals(textViewDescription))
            openDescriptionDialog();
        if (v.equals(imageViewSite))
            openSite();
    }

    private void openSite() {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.getURL()));
        startActivity(myIntent);
    }


}