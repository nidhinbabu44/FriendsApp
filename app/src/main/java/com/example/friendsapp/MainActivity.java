package com.example.friendsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    AppCompatButton b1;
    String getname, getfname,getfnickname,getdesc;
    String apiurl="https://dummyapifriends.herokuapp.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.ffname);
        e3=(EditText) findViewById(R.id.fnickname);
        e4=(EditText) findViewById(R.id.fdesc);
        b1=(AppCompatButton) findViewById(R.id.submitbut);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getname=e1.getText().toString();
                getfname=e2.getText().toString();
                getfnickname=e3.getText().toString();
                getdesc=e4.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }

                        })


                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String,String> params=new HashMap<>();
                        params.put("name",getname);
                        params.put("friendName",getfname);
                        params.put("friendNickName",getfnickname);
                        params.put("DescribeYourFriend",getdesc);

                        return params;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);
            }
        });
    }
}