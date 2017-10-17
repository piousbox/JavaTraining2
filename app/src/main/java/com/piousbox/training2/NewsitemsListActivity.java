package com.piousbox.training2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.annotation.NonNull;

import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import java.util.*;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.Adapter;

import com.piousbox.training2.rest.*;
import com.piousbox.training2.dummy.*;

public class NewsitemsListActivity extends AppCompatActivity {

    ThisAdapter _adapter;
    static final ArrayList<String> _items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle()); */

        /* LinearLayout item = (LinearLayout)findViewById(R.id.n_layout);
        View child = getLayoutInflater().inflate(R.layout.newsitems_list2, null);
        item.addView(child); */

        /* ListView listView = getLayoutInflater().inflate(R.layout.n_list3, parent, false);
        String[] from = { "php_key","c_key","android_key","hacking_key" };
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.newsitems_list, R.id.n_item, from);
        listView.setAdapter(arrayAdapter); */
    }

    public class ThisAdapter extends RecyclerView.Adapter<ThisAdapter.ViewHolder> {
        public ArrayList<Newsitem> mItems = new ArrayList<Newsitem>();

        public ThisAdapter(List<Newsitem> items) {
            mItems.add(new Newsitem("1", "title", "sum-desc"));
            mItems.addAll(items);

            Log.v("abba", "all these items:" + mItems.get(0));
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public Newsitem mItem;
            public final View mView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
            }

            @Override
            public String toString() {
                return super.toString() + " and this string.";
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mItems.get(position);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.newsitems_list_content, parent, false);
            return new ViewHolder(view);
        }

    }
}
