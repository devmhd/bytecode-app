package com.rubik.bytecodem;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class LoginFragment extends Fragment {

    Button btnNewAccount, btnSignin;
    EditText etEmail, etPass;
    ProgressDialog progressDialog;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initGUI();




    }


    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    boolean validateInputs(){

        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();



        if(email.isEmpty()){
            Crouton.makeText(getActivity(), "Email needed", Style.ALERT).show();
            return false;
        }

        if(pass.isEmpty()){
            Crouton.makeText(getActivity(), "Need a password", Style.ALERT).show();
            return false;
        }

//        if(!isValidEmail(email)){
//            Crouton.makeText(getActivity(), "Email not valid", Style.ALERT).show();
//            return false;
//        }

        return true;


    }

    void initGUI(){



        btnNewAccount = (Button) getView().findViewById(R.id.btnNewAccount);
        btnSignin = (Button) getView().findViewById(R.id.btnSignIn);

        etEmail = (EditText) getView().findViewById(R.id.etEmail);
        etPass = (EditText) getView().findViewById(R.id.etPass);


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs()){



                    String feedUrl = "https://api.parse.com/1/login?username=" + etEmail.getText().toString() + "&password=" + etPass.getText().toString();

                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, feedUrl, "",
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {


                                        PreferenceStorage.setFirstLaunch();

                                        PreferenceStorage.setSessionToken(response.getString("sessionToken"));
                                        PreferenceStorage.setLoggerName(response.getString("username"));


                                        PreferenceStorage.setLoggedIn(true);

                                        startActivity(new Intent(getActivity(), ProductListActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));





                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                            },

                            new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

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



                    VolleySingleton.getInstance(getActivity()).getRequestQueue().add(jsonRequest);
                }

            }
        });


        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                ft.replace(R.id.content_frame, new SignupFragment())
                        .commit();
            }
        });

    }


}