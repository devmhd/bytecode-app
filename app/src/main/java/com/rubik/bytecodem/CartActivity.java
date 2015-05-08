package com.rubik.bytecodem;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class CartActivity extends ActionBarActivity {

    ListView lvCart;
    ProgressDialog pb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }




    void initGUI(){

        lvCart = (ListView) findViewById(R.id.lvCart);

        if(Global.activeCart == null){

            //todo fetch;
            pb = ProgressDialog.show(this, "", "Fetching cart...");





            pb.dismiss();


        } else {


            lvCart.setAdapter(new CartListAdapter(this, Global.activeCart));
        }











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

            //todo CHECKOUT
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
