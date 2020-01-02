package com.bchilakalapudi.dailynews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

public class FeedAdapter extends ArrayAdapter<OneIndia_Feed> {

    List<OneIndia_Feed> modelList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public FeedAdapter(Context context, List<OneIndia_Feed> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.modelList = objects;
    }

    @Override
    public OneIndia_Feed getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView","Success");
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.topic_layout, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        OneIndia_Feed item = getItem(position);
       // Log.d("title",""+item.getTitle());
            vh.title.setText(item.getTitle());
          /*  vh.a_option.setText(item.getA_option());
        vh.b_option.setText(item.getB_option());
        vh.c_option.setText(item.getC_option());
        vh.d_option.setText(item.getD_option());
        vh.e_option.setText(item.getE_option());
        vh.f_option.setText(item.getF_option());
        vh.g_option.setText(item.getG_option());
        vh.h_option.setText(item.getH_option());
        vh.answer.setText(item.getAnswer());
        */
       // Log.d("f_option",""+vh.f_option.getText());


       // vh.textViewName.setText(item.getName());
       // vh.textViewCountry.setText(item.getCountry());

        return vh.rootView;
    }

    /**
     * ViewHolder class for layout.<br />
     * <br />
     * Auto-created on 2016-01-05 00:50:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView title;

        private ViewHolder(RelativeLayout rootView,
                           TextView title) {
            this.rootView = rootView;
            this.title = title;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView title = (TextView) rootView.findViewById(R.id.topic_title);

            return new ViewHolder(rootView, title

                    );
        }
    }

}