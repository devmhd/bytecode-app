package com.rubik.bytecodem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProductListActivity extends ActionBarActivity {


    ListView lvProducts;
    EditText etSearch;
    ImageButton btnSearch;
    ProgressDialog pb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        initGUI();
    }




    void initGUI(){

        lvProducts = (ListView) findViewById(R.id.lvProducts);
        etSearch = (EditText) findViewById(R.id.etSearch);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);


        pb = ProgressDialog.show(this, "", "Fetching products...");


        String feedUrl = "https://api.parse.com/1/classes/Product?skip=0&limit=20";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, feedUrl, "",
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray products = response.getJSONArray("results");


                            Global.allProducts = new ArrayList<Product>();

                            for(int i=0; i<products.length(); ++i){

                                Global.allProducts.add(new Product(
                                        products.getJSONObject(i).getString("name"),
                                        products.getJSONObject(i).getString("description"),
                                        products.getJSONObject(i).getString("objectId"),
                                        products.getJSONObject(i).getDouble("price"),
                                        products.getJSONObject(i).getString("thumbURL")

                                ));

                            }


                            //after loading
                            pb.dismiss();

                      lvProducts.setAdapter(new ProductListAdapter(getApplicationContext(),Global.allProducts));

                            lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    int p = (int) view.getTag();

                                    Intent i = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                                    i.putExtra("position", p);
                                    startActivity(i);

                                }
                            });



                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Parse-Application-Id", "R6kBbmhFNsPv44ekZbLlC6hq7JZ7b4fWT5G3H3GN");
                headers.put("Content-Type", "application/json");
                headers.put("X-Parse-REST-API-Key", "QHh6SwA97ioIo8ZkmEczrpFr8jZB5G5rYybrlbpO");
                return headers;
            }
        };



        VolleySingleton.getInstance(this).getRequestQueue().add(jsonRequest);








    };

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_product_list, menu);
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
