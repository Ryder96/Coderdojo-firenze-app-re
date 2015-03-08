package loaiza.andres.it.coderdojo_firenze.Activities.Event.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import loaiza.andres.it.coderdojo_firenze.Activities.Event.TabPagerAdapterEvents;
import loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite.BriteListItem;
import loaiza.andres.it.coderdojo_firenze.Helpers.GsonHelper;
import loaiza.andres.it.coderdojo_firenze.R;


public class FragmentDescriptionEvent extends DialogFragment {


    private BriteListItem currentItem;
    private TextView textViewDescription;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View myView = inflater.inflate(R.layout.fragment_description_event, null);
        builder.setView(myView);
        this.currentItem = (BriteListItem) GsonHelper.get(getArguments().getString(TabPagerAdapterEvents.BUNDLE_CURRENT_ITEM), BriteListItem.class);
        this.initLayout(myView);
        return builder.create();
    }

    private void initLayout(View myView) {
        this.textViewDescription = (TextView) myView.findViewById(R.id.dialogDescription);
        if (this.currentItem != null)
            populateView(currentItem);
    }

    private void populateView(BriteListItem currentItem) {
        //this.textViewDescription.setText(currentItem.getDescription());
        this.textViewDescription.setText("\n\n" + this.textViewDescription.getText() + "\n\n");

    }


}
