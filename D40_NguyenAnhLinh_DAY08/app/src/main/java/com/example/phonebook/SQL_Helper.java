package com.example.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQL_Helper extends SQLiteOpenHelper {

    private static final String TAG = "SQL_Helper";

    static final String DB_NAME = "PHoneBook.db";
    static final String DB_TABLE = "ConTact";
    static final int DB_VERSION = 1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public SQL_Helper( Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "Create Table ConTact(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,"+
                "phoneNumber INTEGER,"+
                "image INTEGER)";

        db.execSQL(queryCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != newVersion)
        {
            db.execSQL(" DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }


    public  void insertPhone( Contact contact)
    {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("name",contact.getName());
        contentValues.put("phoneNumber",contact.getPhoneNumber());
        contentValues.put("image",contact.getImg());
        sqLiteDatabase.insert(DB_TABLE, null, contentValues);

    }

    public boolean  deletePhone(String name)
    {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_TABLE,"name=?", new String[]{String.valueOf(name)}) >=0;

    }

    public boolean updatePhone(String name, Contact contact)
    {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("name",contact.getName());
        contentValues.put("phoneNumber",contact.getPhoneNumber());
        contentValues.put("image",contact.getImg());

       return sqLiteDatabase.update(DB_TABLE,contentValues,"name=?",
                new String[]{String.valueOf(name)}) >=0;
    }
    public List<Contact> GetallPhoneNumber()
    {
        List<Contact> contacts = new ArrayList<>();
        sqLiteDatabase = getWritableDatabase();
        Contact contact;
        Cursor cursor = sqLiteDatabase.query(false, DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String  phone =(cursor.getString(cursor.getColumnIndex("phoneNumber")));
            int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex("image")));
       contacts.add( new Contact(name,phone,image));
        }
        return contacts;
    }
}
