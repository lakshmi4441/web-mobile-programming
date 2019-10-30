package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int COFFEE_PRICE = 6;
    final int Jalapinoes = 1;
    final int Onions = 1;
    final int Olives = 1;
    final int Tomatoes = 1;
    final int Spinach = 1;
    final int Lettuce = 1;
    final int Bellpeppers = 1;
    String name = "";
    String order = "";
    String price1 = "";
    String quant = "";
    String totalmessage ="";

    int quantity = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        CheckBox jalapenoes = (CheckBox) findViewById(R.id.jalapenoes);
        boolean hasjalapenoes = jalapenoes.isChecked();

        CheckBox onion = (CheckBox) findViewById(R.id.onion);
        boolean hasonion = onion.isChecked();

        CheckBox tomatoes = (CheckBox) findViewById(R.id.tomatoes);
        boolean hastomatoes = tomatoes.isChecked();

        CheckBox bellpeppers = (CheckBox) findViewById(R.id.bellpeppers);
        boolean hasbellpeppers = bellpeppers.isChecked();

        CheckBox spinach = (CheckBox) findViewById(R.id.spinach);
        boolean hasspinach = spinach.isChecked();

        CheckBox olives = (CheckBox) findViewById(R.id.olives);
        boolean hasolives = olives.isChecked();

        CheckBox lettuce = (CheckBox) findViewById(R.id.lettuce);
        boolean haslettuce = lettuce.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasbellpeppers,hasjalapenoes, hastomatoes, hasonion, hasspinach, haslettuce);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasbellpeppers,hasjalapenoes, hastomatoes, hasonion, hasspinach, haslettuce, totalPrice);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        Intent redirect = new Intent(MainActivity.this, Summary.class);
        redirect.putExtra("OrderSummaryMessage", orderSummaryMessage);
        redirect.putExtra("order", order);
        redirect.putExtra("price", price1);
        redirect.putExtra("quant", quant);
        redirect.putExtra("name", name);

        startActivity(redirect);

    }

    protected void sendEmail() {
        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        CheckBox jalapenoes = (CheckBox) findViewById(R.id.jalapenoes);
        boolean hasjalapenoes = jalapenoes.isChecked();

        CheckBox onion = (CheckBox) findViewById(R.id.onion);
        boolean hasonion = onion.isChecked();

        CheckBox tomatoes = (CheckBox) findViewById(R.id.tomatoes);
        boolean hastomatoes = tomatoes.isChecked();

        CheckBox bellpeppers = (CheckBox) findViewById(R.id.bellpeppers);
        boolean hasbellpeppers = bellpeppers.isChecked();

        CheckBox spinach = (CheckBox) findViewById(R.id.spinach);
        boolean hasspinach = spinach.isChecked();

        CheckBox olives = (CheckBox) findViewById(R.id.olives);
        boolean hasolives = olives.isChecked();

        CheckBox lettuce = (CheckBox) findViewById(R.id.lettuce);
        boolean haslettuce = lettuce.isChecked();

        float totalPrice = calculatePrice(hasbellpeppers,hasjalapenoes, hastomatoes, hasonion, hasspinach, haslettuce);

        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_Bellpeppers, boolToString(hasbellpeppers)) + "\n" +
                getString(R.string.order_summary_Jalapenoes, boolToString(hasjalapenoes)) + "\n" +
                getString(R.string.order_summary_Lettuce, boolToString(haslettuce)) + "\n" +
                getString(R.string.order_summary_Onions, boolToString(hasonion)) + "\n" +
                getString(R.string.order_summary_Spinach, boolToString(hasspinach)) + "\n" +
                getString(R.string.order_summary_Tomatoes, boolToString(hastomatoes)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, totalPrice) + "\n" +
                getString(R.string.thank_you);

        TextView email = (TextView) findViewById(R.id.email);

        Log.i("Send email", "");
        String[] TO = {email.getText().toString()};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is your pizza Order");
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName,boolean hasbellpeppers, boolean hasjalapenoes, boolean hastomatoes, boolean hasonion,boolean hasspinach,boolean haslettuce, float price) {
        name = userInputName;
        order= getString(R.string.order_summary_Bellpeppers, boolToString(hasbellpeppers)) + "\n" +
                getString(R.string.order_summary_Jalapenoes, boolToString(hasjalapenoes)) + "\n" +
                getString(R.string.order_summary_Lettuce, boolToString(haslettuce)) + "\n" +
                getString(R.string.order_summary_Onions, boolToString(hasonion)) + "\n" +
                getString(R.string.order_summary_Spinach, boolToString(hasspinach)) + "\n" +
                getString(R.string.order_summary_Tomatoes, boolToString(hastomatoes)) + "\n" ;
        quant= getString(R.string.order_summary_quantity, quantity);
        price1 = getString(R.string.order_summary_total_price, price);

        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_Bellpeppers, boolToString(hasbellpeppers)) + "\n" +
                getString(R.string.order_summary_Jalapenoes, boolToString(hasjalapenoes)) + "\n" +
                getString(R.string.order_summary_Lettuce, boolToString(haslettuce)) + "\n" +
                getString(R.string.order_summary_Onions, boolToString(hasonion)) + "\n" +
                getString(R.string.order_summary_Spinach, boolToString(hasspinach)) + "\n" +
                getString(R.string.order_summary_Tomatoes, boolToString(hastomatoes)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);

        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasbellpeppers, boolean hasjalapenoes,boolean hastomatoes, boolean hasonion,boolean hasspinach,boolean haslettuce) {
        int basePrice = COFFEE_PRICE;
        TextView text_quan = (TextView) findViewById(R.id.quantity_text_view);
        quantity = Integer.parseInt(text_quan.getText().toString());
        if (hasbellpeppers) {
            basePrice += Lettuce;
        }
        if (hasjalapenoes) {
            basePrice += Jalapinoes;
        }
        if (hastomatoes) {
            basePrice += Tomatoes;
        }
        if (hasonion) {
            basePrice += Onions;
        }
        if (hasspinach) {
            basePrice += Spinach;
        }
        if (haslettuce) {
            basePrice += Bellpeppers;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}


