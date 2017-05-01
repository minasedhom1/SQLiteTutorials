package com.example.mido.sqlitetutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText name_et,phone_et,product_et,price_et;
    Button add;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         db = new DBHandler(this);add= (Button) findViewById(R.id.add_trans_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Reading: ", "Reading all contacts..");
                List<Transaction> contacts = db.getAllContacts();

                for (Transaction cn : contacts) {
                    String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }
            }
        });
/*

 name_et= (EditText) findViewById(R.id.name);
        phone_et= (EditText) findViewById(R.id.phone);
        price_et= (EditText) findViewById(R.id.price);
        product_et= (EditText) findViewById(R.id.product);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            */
/*    Transaction transaction=new Transaction();
                transaction.setName(name_et.getText().toString());
                transaction.setPhone(phone_et.getText().toString());
                transaction.setProduct(product_et.getText().toString());
                transaction.setPrice(price_et.getText().toString());
                transaction.setDate("D");
                db.addTransaction(transaction);*//*


                */
/**
                 * CRUD Operations
                 * *//*

                // Inserting Contacts
               */
/* Log.d("Insert: ", "Inserting ..");
                db.addTransaction(new Transaction("Ravi", "9100000000","mozel","10","today"));
                db.addTransaction(new Transaction("Ravi", "9100000000","mozel","10","today"));


                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Transaction> contacts = db.getAllContacts();

                for (Transaction cn : contacts) {
                    String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }*//*

            }
        });

*/


        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addTransaction(new Transaction("Ravi", "9100000000","mozel","10","today"));
        db.addTransaction(new Transaction("Ravi", "9100000000","mozel","10","today"));


        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Transaction> contacts = db.getAllContacts();

        for (Transaction cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
    }

