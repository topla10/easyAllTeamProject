package com.example.homework;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    private EditText username;
    private EditText password;
    private Button login;
    private TextView message;

    private int counter = 3;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://videocash.herokuapp.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText)findViewById(R.id.usernameId);
        password = (EditText)findViewById(R.id.passwordId);
        login = (Button) findViewById(R.id.loginId);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());

            }
        });

    }
    public void validate(String userName,String userPassword) {
        if ((userName.equals("abc")) && (userPassword.equals("1234"))) {

            Log.d("HI", "Yes");
            Intent intent=new Intent(MainActivity.this,Productpload.class); // redirecting to LoginActivity.
            startActivity(intent);
        }

    }

    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response ------------------> "+response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               System.out.println("error : "+error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }



    private void postDataUsingVolley() {
        // url to post our data
        String url = "https://videocash.herokuapp.com/product";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                // on below line we are displaying a success toast message.
                Toast.makeText(MainActivity.this, "SENT", Toast.LENGTH_SHORT).show();
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);
                    System.out.println("Response: "+response.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                params.put("quantity", "202");
                params.put("exp", "2013-12-5");
                params.put("name", "Product 2");
                params.put("code", "113");
                params.put("brand", "Brand 1");
                params.put("price", "500.0");
                params.put("category", "cat 1");

                // on below line we are passing our key
                // and value pair to our parameters.
//                params.put("quantity", quantity.toString());
//                params.put("exp", expirationDate.toString());
//                params.put("name", productName.toString());
//                params.put("code", productCode.toString());
//                params.put("brand", brand.toString());
//                params.put("price", price.toString());

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

}
