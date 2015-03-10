package coderdojo.firenze.CustomControls.Twitter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import coderdojo_firenze.R;


public class TwitterAdapter extends RecyclerView.Adapter<TwitterAdapter.ViewHolder> {


    private List<TwitterListElement> mDataset;
    private View myView;

    public TwitterAdapter(List<TwitterListElement> items) {
        mDataset = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_twitter_item, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.view_text.setText(mDataset.get(position).getPost().getText());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView view_text;

        public ViewHolder(View itemView) {
            super(itemView);
            view_text = (TextView) itemView.findViewById(R.id.twitterPostText);
        }
    }
}
