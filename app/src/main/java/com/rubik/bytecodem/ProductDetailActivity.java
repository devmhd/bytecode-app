package com.rubik.bytecodem;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;


public class ProductDetailActivity extends ActionBarActivity {


    Button  btnAddToCart , btnUpdateQuantity , btnRemoveFromCart;
    TextView tvTitle, tvDesc, tvPrice;
    ImageView ivImage;


    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        product = Global.allProducts.get( getIntent().getExtras().getInt("position"));

        initGUI();
    }


    void initGUI() {


        btnAddToCart = (Button) findViewById(R.id.btnAddToCart);
        btnUpdateQuantity = (Button) findViewById(R.id.btnUpdateQuantity);
        btnRemoveFromCart = (Button) findViewById(R.id.btnRemoveFromCart);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDesc = (TextView) findViewById(R.id.tvDescription);
        tvPrice = (TextView) findViewById(R.id.tvPrice);

        ivImage = (ImageView) findViewById(R.id.ivImage);


        tvTitle.setText(product.title);
        tvDesc.setText(product.description);
        tvPrice.setText("Tk. " + product.price);


        String imageUrl1 = product.thumbUrl;
        VolleySingleton.getInstance(getApplicationContext()).getImageLoader().get(imageUrl1,
                ImageLoader.getImageListener(ivImage,
                        R.drawable.ic_wait, R.drawable.ic_launcher));

    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
