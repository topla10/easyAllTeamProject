package com.example.homework;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddToCart extends AppCompatActivity {

    private EditText productCode;
    private EditText quantity;
    private Button addToCart;

    private TextView productDataName;
    private TextView productDataPrice;
    private TextView productDataQuantity;
    private TextView total;
    private TextView change;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private EditText amountReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        productCode = (EditText)findViewById(R.id.code);
        quantity = (EditText)findViewById(R.id.quantity);
        addToCart = (Button) findViewById(R.id.addToCart);

        productDataName = (TextView) findViewById(R.id.productDataName);
        productDataPrice = (TextView) findViewById(R.id.productDataPrice);
        productDataQuantity = (TextView) findViewById(R.id.productDataQuantity);
        change = (TextView) findViewById(R.id.change);
        total = (TextView) findViewById(R.id.total);

        amountReceived = (EditText)findViewById(R.id.amountReceived);


        addToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Clicked");

                sendAndRequestResponse(productCode.getText().toString());
            }
        });


    }


    private void sendAndRequestResponse(String productCode) {
        System.out.println("Product code "+productCode);

        String url = "https://videocash.herokuapp.com/product/"+productCode;

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response ------------------> "+response.toString());

                try {
                    JSONObject obj = new JSONObject(response);
                    System.out.println("Object : "+obj);

                    System.out.println("Object 1 : "+obj.get("data"));
                    JSONObject obj1 =  obj.getJSONObject("data");

                    String productName = "Product Name: " + obj1.getString("name");
                    productDataName.setText(productName);

                    String productPrice = "Product Price: " + obj1.getString("price");
                    productDataPrice.setText(productPrice);

                    String productQuantity = "Product Quantity: " + quantity.getText().toString();
                    productDataQuantity.setText(productQuantity);

                    Double totalAmount = Double.parseDouble(quantity.getText().toString()) * Double.parseDouble(obj1.getString("price"));
                    String totalString = "Total: " + totalAmount.toString();
                    total.setText(totalString);

                    Double changeAmount = Double.parseDouble(amountReceived.getText().toString()) - totalAmount;

                    String changeString = "Change: " + changeAmount.toString();
                    change.setText(changeString);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("error : "+error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

}