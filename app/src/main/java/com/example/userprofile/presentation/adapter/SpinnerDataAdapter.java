package com.example.userprofile.presentation.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.userprofile.R;
import com.example.userprofile.data.model.BaseModel;
import com.example.userprofile.data.model.Gender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mihanik on 20.04.2017.
 */

public class SpinnerDataAdapter<T extends BaseModel> extends ArrayAdapter<T> {

    private List<T> data = null;
    private Gender tempGender = null;
    private LayoutInflater mInflater = null;
    private int resource;
    public SpinnerDataAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> data) {
        super(context, resource, data);
        this.data = data;
        this.resource = resource;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public int getCount() {
        if(data != null) {
            return data.size();
        }

        return 0;
    }
    @Override
    public void addAll(@NonNull Collection<? extends T> collection) {
        if(data == null) data = new ArrayList<T>();
        data.addAll(collection);
    }

    @Override
    public void clear() {
        if(data != null) {
            data.clear();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = mInflater.inflate(this.resource, parent, false);
        TextView itemNameLabel  = (TextView)row.findViewById(R.id.itemName);
        itemNameLabel.setText(data.get(position).getName());
        return row;
    }
}
