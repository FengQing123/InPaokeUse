package com.fq.inpaokeuse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fq.inpaokeuse.bean.Picture;

import java.util.List;

/**
 * @author fengqing
 * @date 2018/2/1
 */
public class PictureAdapter extends ArrayAdapter<Picture> {

    public PictureAdapter(Context context, int textViewResourceId, List<Picture> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Picture picture = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
        } else {
            view = convertView;
        }
        TextView text1 = view.findViewById(android.R.id.text1);
        text1.setText(picture.getName());
        return view;
    }

}