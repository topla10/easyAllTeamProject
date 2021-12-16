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

public class ProductByCode extends AppCompatActivity {

    private EditText productCode;
    private Button submit;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private TextView namec;
    private TextView pricec;
    private TextView quantityc;
    private TextView categoryc;
    private TextView brandc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_code);
        Toolbar toolbar = findViewById(R.id.toolbar);

        productCode = (EditText)findViewById(R.id.code);
        submit = (Button) findViewById(R.id.productCode);

        namec = (TextView) findViewById(R.id.namec);;
        pricec = (TextView) findViewById(R.id.pricec);;
        quantityc = (TextView) findViewById(R.id.quantityc);;
        categoryc = (TextView) findViewById(R.id.categoryc);;
        brandc = (TextView) findViewById(R.id.brandc);;

        setSupportActionBar(toolbar);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Clicked");

                sendAndRequestResponse(productCode.getText().toString());
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
                    namec.setText(productName);

                    String productPrice = "Product Price: " + obj1.getString("price");
                    pricec.setText(productPrice);

                    String productquantity = "Product Name: " + obj1.getString("quantity");
                    quantityc.setText(productquantity);

                    String productCategory = "Product Name: " + obj1.getString("category");
                    categoryc.setText(productCategory);

                    String productBrand = "Product Name: " + obj1.getString("brand");
                    brandc.setText(productBrand);


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