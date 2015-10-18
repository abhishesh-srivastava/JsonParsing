package com.abhishesh.projects.medicine.ui;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishesh.projects.medicine.R;
import com.abhishesh.projects.medicine.db.DatabaseController;
import com.abhishesh.projects.medicine.model.Item;
import com.abhishesh.projects.medicine.utils.Utils;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class DetailFragment extends Fragment {

    private static final String ITEM_DATA_EXTRA = "extra_item_data";
    private int mId = -1;
    private TextView label;
    private TextView brand;
    private TextView type;
    private TextView drugsPackSize;
    private TextView manufacturer;
    private TextView mrp;
    private TextView packForm;
    private TextView pForm;
    private ImageView icon;
    public static DetailFragment newInstance(int id) {

        final DetailFragment f = new DetailFragment();
        final Bundle args = new Bundle();
        args.putInt(ITEM_DATA_EXTRA, id);
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
        icon = (ImageView) v.findViewById(R.id.pager_icon);
        icon.setImageResource(Utils.icons[mId % 5]);
        label = (TextView) v.findViewById(R.id.detail_label);
        brand = (TextView) v.findViewById(R.id.detail_brand);
        type = (TextView) v.findViewById(R.id.detail_type);
        drugsPackSize = (TextView) v.findViewById(R.id.detail_size);
        mrp = (TextView) v.findViewById(R.id.detail_mrp);
        packForm = (TextView) v.findViewById(R.id.detail_pack_form);
        pForm = (TextView) v.findViewById(R.id.detail_p_form);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //items are stored from number 1, not 0 in db
        Item item = DatabaseController.getInstance(getActivity()).getItem(mId+1);
        if(item != null) {
            label.setText("LABEL : " + item.getLabel());
            brand.setText("BRAND : " + item.getBrand());
            type.setText("TYPE : " + item.getType());
            drugsPackSize.setText("SIZE : " + item.getDrugsPackSize());
            mrp.setText("MRP Rs" + item.getMrp());
            packForm.setText("PACKFORM : " + item.getPackForm());
            pForm.setText("PFORM : " + item.getpForm());
        }
    }
}
