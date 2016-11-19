package cl.telematica.android.certamen3.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.telematica.android.certamen3.R;
import cl.telematica.android.certamen3.activities.DataBaseActivity;
import cl.telematica.android.certamen3.models.Feed;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by franciscocabezas on 11/18/16.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Feed> mDataset;
    private Context mContext;
    public Realm realm;
    String a, b, c, d, e, f;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public Button mAddBtn;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textTitle);
            mImageView = (ImageView) v.findViewById(R.id.imgBackground);
            mAddBtn = (Button) v.findViewById(R.id.add_btn);

        }
    }

    public DataAdapter(Context mContext, List<Feed> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    /*private void add_data(String a, String b, String c, String d, String e, String f){
        realm.beginTransaction();
        Feed feeds = realm.createObject(Feed.class); // Create a new object
        feeds.setTitle(a);
        feeds.setLink(b);
        feeds.setAuthor(c);
        feeds.setPublishedDate(d);
        feeds.setContent(e);
        feeds.setImage(f);
        realm.commitTransaction();
    }*/

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Feed feed = mDataset.get(position);

        holder.mTextView.setText(feed.getTitle());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = feed.getLink();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(browserIntent);
            }
        });

        Glide.with(mContext).load(feed.getImage()).into(holder.mImageView);

        if(feed.isFavorite()) {
            holder.mAddBtn.setText(mContext.getString(R.string.added));
        } else {
            holder.mAddBtn.setText(mContext.getString(R.string.like));
        }
        holder.mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * In this section, you have to manage the add behavior on local database
                 */
                feed.setFavorite(!feed.isFavorite());
                if(feed.isFavorite()) {
                    holder.mAddBtn.setText(mContext.getString(R.string.added));
                    System.out.println(feed.getTitle().toString());

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.copyToRealm(feed);
                    realm.commitTransaction();
                    //add_data(feed.getTitle().toString(),feed.getLink().toString(),feed.getAuthor().toString(),
                      //      feed.getPublishedDate().toString(), feed.getContent().toString(), feed.getImage().toString());
                } else {
                    holder.mAddBtn.setText(mContext.getString(R.string.like));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
