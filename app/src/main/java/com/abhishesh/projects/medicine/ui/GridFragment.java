package com.abhishesh.projects.medicine.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.abhishesh.projects.medicine.adapter.GridViewAdapter;
import com.abhishesh.projects.medicine.db.DatabaseController;
import com.abhishesh.projects.medicine.model.Item;
import com.abhishesh.projects.medicine.utils.JsonParser;
import com.abhishesh.projects.medicine.utils.LogUtils;
import com.abhishesh.projects.medicine.R;
import com.abhishesh.projects.medicine.utils.NetworkRequestHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class GridFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = LogUtils.makeLogTag(GridFragment.class);
    private GridViewAdapter mAdapter;
    private final String SHARED_PREF_NAME = "item_pref";
    private final String API_URL = "http://api.staging.pharmeasy.in/v1/reminder-medicine?brand=&page=1";

    public GridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        boolean isDataDownloaded = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean("loaded", false);
        DownloadItemDataTask task = new DownloadItemDataTask(getActivity(), isDataDownloaded);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        mAdapter = new GridViewAdapter(getActivity(), new ArrayList<Item>());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.grid_fragment_layout, container, false);
        GridView mGridView = (GridView) v.findViewById(R.id.gridView);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        final Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra(DetailActivity.EXTRA_ITEM, (int) (position));
        startActivity(i);
    }

    private class DownloadItemDataTask extends AsyncTask<Void, Integer, List<Item>> {

        private Context mContext;
        private boolean isDataAvaiable;

        DownloadItemDataTask(Context context, boolean flag) {
            mContext = context;
            isDataAvaiable = flag;
        }

        @Override
        protected List<Item> doInBackground(Void... params) {
            List<Item> itemList = null;

            if (isDataAvaiable) {
                itemList = DatabaseController.getInstance(mContext).getAllItems();
            } else {
                String response = NetworkRequestHandler.getRequest(API_URL);
                itemList = JsonParser.parse(response);
                for (Item item : itemList) {
                    DatabaseController mController = DatabaseController.getInstance(mContext);
                    long ll = mController.insertItem(item);
                }
            }
            return itemList;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            if (items != null && items.size() > 0) {
                mAdapter.setList(items);
                mAdapter.notifyDataSetChanged();
                if (!isDataAvaiable) {
                    getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit().putBoolean("loaded", true).commit();
                    getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit().putInt("count", items.size()).commit();
                }
            }
        }
    }
}
