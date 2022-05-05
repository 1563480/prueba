package com.example.nwaster.ui.Lists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.nwaster.R;

import java.util.ArrayList;

class ListViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;
    FragmentManager fm;

    // The ListViewAdapter Constructor
    // @param context: the Context from the MainActivity
    // @param items: The list of items in our Grocery List
    public ListViewAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.list_row, items);
        this.context = context;
        list = items;
    }

    // The method we override to provide our own layout for each View (row) in the ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row, null);
            TextView name = convertView.findViewById(R.id.name);
            TextView number = convertView.findViewById(R.id.number);

            number.setText(position + 1 + ".");
            name.setText(list.get(position));
        }
        return convertView;
    }

}
