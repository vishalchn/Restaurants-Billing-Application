 package com.vishal.practice;

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

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int kquantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if(quantity==100){
            Toast.makeText(this,"You cannot have more than 10 Samosa",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);

    }

    public void decrement(View view) {

        if(quantity==1){
            Toast.makeText(this,"You cannot have Less than 1 Samosa",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }
// FOR KACHORI
    public void kincrement(View view) {
        if (kquantity == 100) {
            Toast.makeText(this, "You cannot have more than 10 Kachori", Toast.LENGTH_SHORT).show();
            return;
        }
        kquantity = kquantity + 1;
        kdisplay(kquantity);
    }
    public void kdecrement(View view) {

        if(kquantity==1){
            Toast.makeText(this,"You cannot have Less than 1 Kachori",Toast.LENGTH_SHORT).show();
            return;
        }
        kquantity = kquantity - 1;
        kdisplay(kquantity);
    }
    private int calculatePrice(boolean addChatniChecked,boolean addShewChecked,boolean addDhaniChecked){
        int price = 5;
        if(addChatniChecked){
            price=price+2;

        }
        if(addShewChecked){
            price=price+1;

        }
        if(addDhaniChecked){
            price=price+2;
        }
        int kprice=kquantity*5;
        int totalPrice=kprice+quantity*price;
        return totalPrice;
    }

    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.edit_text);
        String name=nameField.getText().toString();

        CheckBox Chatni = (CheckBox) findViewById(R.id.chatni);
        boolean hasChatniChecked = Chatni.isChecked();

        CheckBox Shew = findViewById(R.id.shew);
        boolean hasShewChecked = Shew.isChecked();

        CheckBox Dhani = (CheckBox) findViewById(R.id.dahi_view);
       Boolean hasDhaniChecked=Dhani.isChecked();

        int price=calculatePrice(hasChatniChecked,hasShewChecked,hasDhaniChecked);
        String priceMessage=createOrderSummary(name,price,hasChatniChecked,hasShewChecked,hasDhaniChecked);
//        displayMessage(priceMessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"ODER DETAIL");
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

    }
    private String createOrderSummary(String name,int totalprice,boolean chatni,boolean shew,boolean dhani){
        //String summary = "Name: "+name;
        String summary = getString(R.string.name, name);
        //summary += "\n Is chatni Order? "+ chatni;
        summary += "\n" + getString(R.string.chatni_checked, chatni);
        //summary += "\n Is Shew Order? "+shew;
        summary += "\n" + getString(R.string.shew_checked, shew);
        //summary += "\n Is Dhani Order? "+dhani;
        summary += "\n" + getString(R.string.dhani_checked, dhani);
        //summary = summary +"\nSamosa Quantity: "+quantity;
        summary = summary + "\n" + getString(R.string.samosa_quantity, quantity);
        //summary = summary +"\nKachori Quantity: "+kquantity;
        summary = summary + "\n" + getString(R.string.kachori_quantity, kquantity);
        //summary = summary +"\nTotal: $"+totalprice;
        summary = summary + "\n" + getString(R.string.total_price, NumberFormat.getCurrencyInstance().format(totalprice));
        summary = summary + "\n" + getString(R.string.thank_you);
        return summary;
    }

    //integer display method
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    //kachori display method
    public void kdisplay(int number){
        TextView textView = (TextView) findViewById(R.id.kachori_quantity);
        textView.setText(""+number);
    }

    //String display method
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}