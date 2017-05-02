package com.example.mido.sqlitetutorials;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 5/1/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "transactionManager";

    // Contacts table name
    private static final String TABLE_NAME = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_PRODUCT="product";
    private static final String KEY_DATE="date";
    private static final String KEY_BUY_PRICE="buy_price";
    private static final String KEY_SELL_PRICE="sell_price";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT,"+ KEY_PRODUCT + " TEXT,"+ KEY_BUY_PRICE + " TEXT," + KEY_SELL_PRICE + " TEXT," + KEY_DATE + " TEXT"  + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    //All CRUD Operations (Create, Read, Update and Delete)

    // Adding new contact
    public void addTransaction(Transaction transaction)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, transaction.getName()); // Contact Name
        values.put(KEY_PH_NO, transaction.getPhone()); // Contact Phone Number
        values.put(KEY_PRODUCT, transaction.getProduct()); // Contact Name
        values.put(KEY_BUY_PRICE, transaction.getBuy_price()); // Contact Phone Number
        values.put(KEY_SELL_PRICE, transaction.getSell_price());
        values.put(KEY_DATE, transaction.getDate()); // Contact Name
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Transaction
    public Transaction getTransaction(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO ,KEY_PRODUCT,KEY_BUY_PRICE,KEY_SELL_PRICE,KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Transaction transaction = new Transaction(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        // return contact
        return transaction;
    }

    // Getting All Contacts
    public List<Transaction> getAllContacts() {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setId(Integer.parseInt(cursor.getString(0)));
                transaction.setName(cursor.getString(1));
                transaction.setPhone(cursor.getString(2));
                transaction.setProduct(cursor.getString(3));
                transaction.setBuy_price(cursor.getString(4));
                transaction.setSell_price(cursor.getString(5));
                transaction.setDate(cursor.getString(6));
                // Adding contact to list
                transactionList.add(transaction);
            } while (cursor.moveToNext());
        }

        // return contact list
        return transactionList;
    }

    // Getting contacts Count
 //   public int getContactsCount() {}
    // Updating single contact
  //  public int updateContact(Transaction contact) {}

    // Deleting single contact
    public void deleteTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(transaction.getId()) });
        db.close();
    }

      public double getTotalBuyPrice() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM("+KEY_BUY_PRICE+") FROM " + TABLE_NAME, null);
        c.moveToFirst();
        double i = c.getDouble(0);
        c.close();
        return i;
    }
    public double getTotalSellPrice() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM("+KEY_SELL_PRICE+") FROM " + TABLE_NAME, null);
        c.moveToFirst();
        double i = c.getDouble(0);
        c.close();
        return i;
    }
    public void deleteAll()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }
}
