package com.rubik.bytecodem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CartActivity extends ActionBarActivity {

    ListView lvCart;
    ProgressDialog pb;
    TextView tvTotal;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initGUI();
    }




    void initGUI(){

        lvCart = (ListView) findViewById(R.id.lvCart);
        tvTotal = (TextView) findViewById(R.id.tvcartTotal);

//

        lvCart.setAdapter(new CartListAdapter(this, Global.activeCart));



        double sum = 0;
        for (int i=0; i<Global.activeCart.size(); ++i){

            sum += Global.activeCart.get(i).subTotal;
        }

        tvTotal.setText("Total Tk. " + sum);








        //after loading




    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            pd= ProgressDialog.show(this, "", "Checking out...");

            String feedUrl = "https://api.parse.com/1/functions/checkout";

            JsonObjectRequest req = new JsonObjectRequest(feedUrl, new JSONObject(),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                          //  try {

                                pd.dismiss();

                            Toast.makeText(CartActivity.this, "Checkout done", Toast.LENGTH_SHORT).show();

                                Global.activeCart = new ArrayList<CartEntry>();

                                lvCart.setAdapter(new CartListAdapter(CartActivity.this, Global.activeCart));

//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
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

            VolleySingleton.getInstance(this).getRequestQueue().add(req);



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
