package com.rubik.bytecodem;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mehedee Zaman on 1/29/2015.
 */
public class ProductListAdapter extends ArrayAdapter<Product> {


    ArrayList<Product> products;
    Context context;


    public ProductListAdapter(Context context, ArrayList<Product> products) {


        super(context, R.layout.row_product, products);
        this.context = context;
        this.products = products;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_product, parent, false);

        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tvPrice = (TextView) rowView.findViewById(R.id.tvPrice);
        ImageView ivThumb = (ImageView) rowView.findViewById(R.id.ivThumb);

        tvTitle.setText(products.get(position).title);
        tvPrice.setText("Tk. " + products.get(position).price);

        // TODO ivThumb

        String imageUrl1 =products.get(position).thumbUrl;
        VolleySingleton.getInstance(context).getImageLoader().get(imageUrl1,
                ImageLoader.getImageListener(ivThumb,
                        R.drawable.ic_wait, R.drawable.ic_launcher));


         rowView.setTag(position);

        return rowView;

    }
}