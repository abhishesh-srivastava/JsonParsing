package com.abhishesh.projects.medicine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.abhishesh.projects.medicine.adapter.GridViewAdapter;
import com.abhishesh.projects.medicine.model.Item;
import com.abhishesh.projects.medicine.utils.JsonParser;
import com.abhishesh.projects.medicine.utils.LogUtils;
import com.abhishesh.projects.medicine.R;
import com.abhishesh.projects.medicine.utils.NetworkRequestHandler;

import java.util.List;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class GridFragment extends Fragment implements AdapterView.OnItemClickListener{

    public static final String TAG = LogUtils.makeLogTag(GridFragment.class);

    private int mGridSize;
    private int mGridSpacing;
    private GridViewAdapter mAdapter;

    public GridFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mGridSize = getResources().getDimensionPixelSize(R.dimen.grid_item_size);
        mGridSpacing = getResources().getDimensionPixelSize(R.dimen.grid_item_margin);
//        try {
        StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder().permitAll();
        StrictMode.setThreadPolicy(builder.build());
            String response = NetworkRequestHandler.getRequest("http://api.staging.pharmeasy.in/v1/reminder-medicine?brand=&page=1");
            /* InputStream inputStream = getActivity().getAssets().open("reminder-medicine.xml");
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            Log.d("ABHISHESH","input: " + total.toString()); */
            List<Item> itemList = JsonParser.parse(response);
            mAdapter = new GridViewAdapter(getActivity(),itemList);
            Log.d("ABHISHESH","item: " + itemList);
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Nullable
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
        i.putExtra(DetailActivity.EXTRA_ITEM, (int) id);
        startActivity(i);
    }
}
