package com.rubik.bytecodem;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class ProductDetailActivity extends ActionBarActivity {


    Button  btnAddToCart , btnUpdateQuantity , btnRemoveFromCart;
    TextView tvTitle, tvDesc, tvPrice;
    ImageView ivImage;
    ProgressDialog pd;


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






        int quantity = 0;
        for(CartEntry ce : Global.activeCart){

            if(ce.product.id.equals(product.id)){
                quantity = ce.quantity;
                break;

            }


        }

        if(quantity == 0){

            btnRemoveFromCart.setVisibility(View.GONE);
            btnUpdateQuantity.setVisibility(View.GONE);

        }


        btnUpdateQuantity.setText("Update quantity in cart (" + quantity + ")");



        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd = ProgressDialog.show(ProductDetailActivity.this, "", "Adding to cart...");



                String feedUrl = "https://api.parse.com/1/classes/Order";
                HashMap<String, Object> params = new HashMap<String, Object>();


                HashMap cartmap = new HashMap<String, String>();


                cartmap.put("__type", "Pointer" );
                cartmap.put("className", "Cart" );
                cartmap.put("objectId", PreferenceStorage.getCurrentCardId() );

                JSONObject cartJson = new JSONObject(cartmap);

                HashMap proMap = new HashMap<String, String>();


                proMap.put("__type", "Pointer" );
                proMap.put("className", "Product" );
                proMap.put("objectId", product.id );

                JSONObject productJson = new JSONObject(proMap);



                params.put("cart", cartJson);
                params.put("product",productJson);
                params.put("quantity",1);

                JsonObjectRequest req = new JsonObjectRequest(feedUrl, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                pd.dismiss();
                                try {

                                    Toast.makeText(getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();

                                    boolean found = false;

                                    for(CartEntry ce : Global.activeCart){
                                        if(ce.product.id == product.id){
                                            found = true;
                                            ce.quantity++;
                                            break;
                                        }
                                    }

                                    if(!found){
                                        Global.activeCart.add(new CartEntry(
                                                        product,
                                                        1,
                                                        product.price,

                                                        response.getString("objectId")


                                                )
                                        );
                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("X-Parse-Application-Id", "R6kBbmhFNsPv44ekZbLlC6hq7JZ7b4fWT5G3H3GN");
                        headers.put("Content-Type", "application/json");
                        headers.put("X-Parse-REST-API-Key", "QHh6SwA97ioIo8ZkmEczrpFr8jZB5G5rYybrlbpO");
                        headers.put("X-Parse-Session-Token", PreferenceStorage.getSessionToken());
                        return headers;
                    }
                };

                VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().add(req);



            }
        });







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
