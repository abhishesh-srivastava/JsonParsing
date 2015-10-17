package com.abhishesh.projects.medicine.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.abhishesh.projects.medicine.R;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class DetailFragment extends Fragment {

    private static final String ITEM_DATA_EXTRA = "extra_item_data";
    private long mId = -1;
    public static DetailFragment newInstance(long id) {
        final DetailFragment f = new DetailFragment();

        final Bundle args = new Bundle();
        args.putLong(ITEM_DATA_EXTRA, id);
        f.setArguments(args);
        return f;
    }

    /**
     * Empty constructor as per the Fragment documentation
     */
    public DetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments() != null ? getArguments().getInt(ITEM_DATA_EXTRA) : -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment_layout, container, false);
        return v;
    }
}
