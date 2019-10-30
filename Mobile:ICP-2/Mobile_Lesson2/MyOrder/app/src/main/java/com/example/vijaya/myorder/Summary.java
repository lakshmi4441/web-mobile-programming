package com.example.vijaya.myorder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String OrderSummaryMessage = getIntent().getStringExtra("OrderSummaryMessage");
        String order = getIntent().getStringExtra("order");
        String price = getIntent().getStringExtra("price");
        String name = getIntent().getStringExtra("name");
        String quant = getIntent().getStringExtra("quant");


        TextView ingredients = (TextView) findViewById(R.id.ingredients);
        ingredients.setText(order);

        TextView nameid = (TextView) findViewById(R.id.name);
        nameid.setText(name);

        TextView priceid = (TextView) findViewById(R.id.price);
        priceid.setText(price);

        TextView quantityid = (TextView) findViewById(R.id.quantity);
        quantityid.setText(quant);

        Log.d("order", order);
        Log.d("name", name);
        Log.d("price", price);
    }

}

