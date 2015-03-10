package coderdojo.firenze.Activities.Main.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import coderdojo.firenze.CustomControls.Twitter.TwitterAdapter;
import coderdojo.firenze.CustomControls.Twitter.TwitterListElement;
import coderdojo.firenze.Helpers.TwitterApiHelper;
import coderdojo.firenze.Networking.Twitter.AsyncTwitterRequest;
import coderdojo.firenze.Networking.Twitter.TwitterApi;
import coderdojo.firenze.Networking.Twitter.TwitterRequestListener;
import coderdojo_firenze.R;
import twitter4j.ResponseList;
import twitter4j.Status;


public class FragmentNews extends Fragment implements TwitterRequestListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView twitterListView;
    private ProgressBar progressBar;

    private TwitterApi twitterApi = new TwitterApi();
    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_main_news, container, false);
        this.initLayout(myView);
        this.twitterApiRequest(false);
        return myView;
    }

    private void twitterApiRequest(boolean forceUpdate) {
        if (twitterApi.getLastRequest() == null || forceUpdate) {
            new AsyncTwitterRequest(this, twitterApi).execute(); //TODO Change TwitterApi Constr. (see the class)
            onRequestStarted();
        } else
            onRequestDone(twitterApi.getLastRequest());
    }

    private void onRequestDone() {
        this.twitterListView.setVisibility(View.VISIBLE);
        this.progressBar.setVisibility(View.GONE);
    }

    public void onRequestStarted() {
        this.twitterListView.setVisibility(View.GONE);
        this.progressBar.setVisibility(View.VISIBLE);
    }

    private void initLayout(View view) {
        this.twitterListView = (RecyclerView) view.findViewById(R.id.twitterListView);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressYing);
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

        this.swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        this.swipeRefreshLayout.setOnRefreshListener(this);

        twitterListView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        // twitterListView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL));

        twitterListView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onRequestDone(ResponseList<Status> array) {
        if (array != null) {
            ArrayList<TwitterListElement> itemArray = TwitterApiHelper.getItemArray(array);
            this.populateList(twitterListView, itemArray);
            onRequestDone();
        } else
            Log.d("CoderDojo", "Error during request..."); // TODO add error layout or retry
        this.swipeRefreshLayout.setRefreshing(false);
    }

    private void populateList(RecyclerView listView, ArrayList<TwitterListElement> array) {
        if (this.getActivity() != null) {
            TwitterAdapter adapter = new TwitterAdapter(array);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onRefresh() {
        this.twitterApiRequest(true);
    }
}
