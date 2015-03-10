package coderdojo.firenze.Activities.Main.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import coderdojo.firenze.CustomControls.EventBrite.BriteEventsAdapter;
import coderdojo.firenze.CustomControls.EventBrite.BriteListItem;
import coderdojo.firenze.Helpers.EventBriteHelper;
import coderdojo.firenze.Networking.EventBrite.AsyncBriteEventsRequest;
import coderdojo.firenze.Networking.EventBrite.EventBriteRequestManager;
import coderdojo.firenze.Networking.EventBrite.Response.EventBriteEvents;
import coderdojo.firenze.Networking.EventBrite.RetrofitInterfaces.EventBriteListener;
import coderdojo_firenze.R;


public class FragmentEventList extends Fragment implements EventBriteListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String DEBUG_TAG = "DOJO-DEBUG";
    private RecyclerView eventListView;
    private ProgressBar progressYing;
    private TextView textViewNoEvents;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;


    private EventBriteRequestManager requestManager = new EventBriteRequestManager();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_main_events, container, false);
        this.initLayout(myView);
        this.requestEvents(false);
        return myView;
    }

    private void requestEvents(boolean forceUpdate) {
        if (requestManager.getLastEvents() == null || forceUpdate) {
            onRequestStarted();
            new AsyncBriteEventsRequest(this, requestManager).execute(EventBriteRequestManager.CODERDOJO_ID); // TODO change with DOJO_ID
        } else {
            onEventListRecived(requestManager.getLastEvents());
        }
    }

    private void onRequestStarted() {
        this.progressYing.setVisibility(View.VISIBLE);
        this.eventListView.setVisibility(View.GONE);
        this.textViewNoEvents.setVisibility(View.GONE);
    }

    private void onRequestCompleted() {
        this.progressYing.setVisibility(View.GONE);
        this.eventListView.setVisibility(View.VISIBLE);
        this.textViewNoEvents.setVisibility(View.GONE);
    }


    private void initLayout(View view) {

        this.eventListView = (RecyclerView) view.findViewById(R.id.eventRecyclerView);
        this.progressYing = (ProgressBar) view.findViewById(R.id.progressYing);
        this.textViewNoEvents = (TextView) view.findViewById(R.id.textViewNoEvents);
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

        this.swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        this.swipeRefreshLayout.setOnRefreshListener(this);
        this.eventListView.setVisibility(View.GONE);
        this.textViewNoEvents.setVisibility(View.GONE);


        eventListView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        eventListView.setLayoutManager(mLayoutManager);


    }

    private void loadList(RecyclerView listView, List<BriteListItem> events) {

        if (events.size() == 0)
            setupNoEventsLayout();
        else if (this.getActivity() != null) {
            BriteEventsAdapter adapter = new BriteEventsAdapter(events, getActivity().getApplicationContext());
            listView.setAdapter(adapter);
            onRequestCompleted();
        }
    }

    private void setupNoEventsLayout() {
        this.textViewNoEvents.setVisibility(View.VISIBLE);
        this.eventListView.setVisibility(View.GONE);
        this.progressYing.setVisibility(View.GONE);
    }

    @Override
    public void onEventListRecived(EventBriteEvents response) {

        if (response != null) {
            loadList(eventListView, EventBriteHelper.getListItems(response));
        } else {
            setupNoEventsLayout(); // TODO  Setup error layout
        }

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        requestEvents(true);
    }


}
