package com.rubik.bytecodem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mehedee Zaman on 1/29/2015.
 */
public class CartListAdapter extends ArrayAdapter<CartEntry> {


    ArrayList<CartEntry> cart;
    Context context;


    public CartListAdapter(Context context, ArrayList<CartEntry> cart) {


        super(context, R.layout.row_product, cart);
        this.context = context;
        this.cart = cart;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_cart_entry, parent, false);

        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tvQuantity = (TextView) rowView.findViewById(R.id.tvQuantity);
        ImageButton btnRemove = (ImageButton) rowView.findViewById(R.id.btnDelete);

        tvTitle.setText(cart.get(position).product.title);
        tvQuantity.setText("" + cart.get(position).quantity + " in cart");


        btnRemove.setTag(cart.get(position).product.id);


        // TODO btn listener



        return rowView;

    }
}