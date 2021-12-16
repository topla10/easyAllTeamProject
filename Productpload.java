package com.example.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Productpload extends AppCompatActivity {

    private EditText category;
    private EditText productName;
    private EditText productCode;
    private EditText brand;
    private EditText price;
    private EditText expirationDate;
    private EditText quantity;
    private Button submit;
    private Button productCodeButton;
    private Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpload);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        category = (EditText)findViewById(R.id.category);
        productName = (EditText)findViewById(R.id.product_name);
        productCode = (EditText)findViewById(R.id.code);
        brand = (EditText)findViewById(R.id.brand);
        price = (EditText)findViewById(R.id.price);
        expirationDate = (EditText)findViewById(R.id.editTextDate);
        quantity = (EditText)findViewById(R.id.quantity);
        submit = (Button) findViewById(R.id.submitId);
        productCodeButton = (Button) findViewById(R.id.getProductCode);
        addToCart = (Button) findViewById(R.id.addToCartProd);

        System.out.println("Called");

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Clicked");

                postDataUsingVolley();
            }
        });

        productCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Productpload.this,ProductByCode.class); // redirecting to LoginActivity.
                startActivity(intent);
            }


        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Productpload.this,AddToCart.class); // redirecting to LoginActivity.
                startActivity(intent);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void postDataUsingVolley() {
        // url to post our data
        String url = "https://videocash.herokuapp.com/product";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Productpload.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                // on below line we are displaying a success toast message.
                Toast.makeText(Productpload.this, "SENT", Toast.LENGTH_SHORT).show();
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);
                    System.out.println(respObj.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Productpload.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

//                params.put("quantity", "202");
//                params.put("exp", "2013-12-5");
//                params.put("name", "Product 2");
//                params.put("code", "113");
//                params.put("brand", "Brand 1");
//                params.put("price", "500.0");
//                params.put("category", "cat 1");

//                params.put("quantity", quantity.toString());
//                params.put("exp", expirationDate.toString());
//                params.put("name", productName.toString());
//                params.put("code", productCode.toString());
//                params.put("brand", brand.toString());
//                params.put("price", price.toString());

                // on below line we are passing our key
                // and value pair to our parameters.
                System.out.println(quantity.getText().toString());
                System.out.println(expirationDate.getText().toString());
                System.out.println(productName.getText().toString());
                System.out.println(productCode.getText().toString());
                System.out.println(brand.getText().toString());
                System.out.println(price.getText().toString());
                System.out.println(category.getText().toString());

                params.put("quantity", quantity.getText().toString());
                params.put("exp", expirationDate.getText().toString());
                params.put("name", productName.getText().toString());
                params.put("code", productCode.getText().toString());
                params.put("brand", brand.getText().toString());
                params.put("price", price.getText().toString());
                params.put("category", category.getText().toString());

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