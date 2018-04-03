package com.example.quoctuan.lazadaapp.view.login.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quoctuan.lazadaapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

/**
 * Created by quoctuan on 16/03/2018.
 */

public class SignInFragment extends Fragment implements View.OnClickListener{
    Button btn_facebook,btngoogle;
    CallbackManager callbackManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_fragment,container,false);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("test", "success");
            }

            @Override
            public void onCancel() {
                Log.d("test", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("test", "error");
            }
        });
        btn_facebook = view.findViewById(R.id.btn_facebook);
        btn_facebook.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        LoginManager.getInstance().logInWithPublishPermissions(this,
                Arrays.asList("email"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
