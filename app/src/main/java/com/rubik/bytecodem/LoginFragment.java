package com.rubik.bytecodem;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

        if(!isValidEmail(email)){
            Crouton.makeText(getActivity(), "Email not valid", Style.ALERT).show();
            return false;
        }

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

                 //TODO go ahead
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