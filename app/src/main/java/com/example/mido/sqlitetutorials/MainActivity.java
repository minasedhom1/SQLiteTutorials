package com.example.mido.sqlitetutorials;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText name_et, phone_et, product_et, buy_price_et,sell_price_et,date_et;
    Button add,view_trans_btn,clear_btn;
    DBHandler db;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et = (EditText) findViewById(R.id.name);
        phone_et = (EditText) findViewById(R.id.phone);
        buy_price_et = (EditText) findViewById(R.id.buy_price);
        sell_price_et=(EditText) findViewById(R.id.sell_price);
        product_et = (EditText) findViewById(R.id.product);
        date_et=(EditText) findViewById(R.id.date);
        view_trans_btn= (Button) findViewById(R.id.view_trans_btn);
        view_trans_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewTransactions.class));
            }
        });
        myCalendar = Calendar.getInstance();

         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date_et.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        db = new DBHandler(this);
        add = (Button) findViewById(R.id.add_trans_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name_et.getText().toString().matches("") || phone_et.getText().toString().matches("") ||product_et.getText().toString().matches("") || buy_price_et.getText().toString().matches("") || sell_price_et.getText().toString().matches("")||date_et.getText().toString().matches("") ) {
                    Toast.makeText(MainActivity.this, "Complete the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Transaction transaction = new Transaction();
                    transaction.setName(name_et.getText().toString());
                    transaction.setPhone(phone_et.getText().toString());
                    transaction.setProduct(product_et.getText().toString());
                    transaction.setBuy_price(buy_price_et.getText().toString());
                    transaction.setSell_price(sell_price_et.getText().toString());
                    transaction.setDate(date_et.getText().toString());
                    db.addTransaction(transaction);

                    Log.d("Reading: ", "Reading all contacts..");
                    List<Transaction> contacts = db.getAllContacts();

                    for (Transaction cn : contacts) {
                        String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone();
                        // Writing Contacts to log
                        Log.d("Name: ", log);
                    }
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        clear_btn= (Button) findViewById(R.id.clear_btn);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              db.deleteAll();
             //   clear();
            }
        });


    }
    private void updateLabel() {

            String myFormat = "dd/MM/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            date_et.setText(sdf.format(myCalendar.getTime()));
        }
    private void clear()
    {
        name_et.setText("");
        phone_et.setText("");
        product_et.setText("");
        buy_price_et.setText("");
        sell_price_et.setText("");
        date_et.setText("");
    }
}
