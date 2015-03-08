package loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import loaiza.andres.it.coderdojo_firenze.Activities.Event.EventActivityTabbed;
import loaiza.andres.it.coderdojo_firenze.Helpers.GsonHelper;
import loaiza.andres.it.coderdojo_firenze.R;


public class BriteEventsAdapter extends RecyclerView.Adapter<BriteEventsAdapter.ViewHolder> {

    public static final String INTENT_ITEM = "LIST_ITEM";

    List<BriteListItem> mDataset;
    Context context;

    private View myView;

    public BriteEventsAdapter(List<BriteListItem> items, Context context) {
        mDataset = items;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {

        myView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_event_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(myView);


        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.view_icon.setImageResource(mDataset.get(i).getIcon());
        viewHolder.view_title.setText(mDataset.get(i).getTitle());
        viewHolder.view_location.setText(mDataset.get(i).getLocation());
        viewHolder.view_places.setText(mDataset.get(i).getEventDate());

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventActivityTabbed.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra(INTENT_ITEM, GsonHelper.convert(mDataset.get(i)));
                context.startActivity(intent);
            }

        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView view_icon;
        TextView view_title;
        TextView view_location;
        TextView view_places;

        public ViewHolder(View itemView) {
            super(itemView);
            view_title = (TextView) itemView.findViewById(R.id.eventListTitle);
            view_icon = (ImageView) itemView.findViewById(R.id.eventListIcon);
            view_location = (TextView) itemView.findViewById(R.id.eventListLocation);
            view_places = (TextView) itemView.findViewById(R.id.eventListPlaces);


        }


    }
}
