package com.example.danhba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public ContactDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, contact.getName());
        // Thêm các cột khác nếu cần

        database.insert(DBHelper.TABLE_CONTACTS, null, values);
    }

    public void updateContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, contact.getName());
        // Cập nhật các cột khác nếu cần

        database.update(DBHelper.TABLE_CONTACTS, values, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact) {
        database.delete(DBHelper.TABLE_CONTACTS, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_CONTACTS;

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)));
                // Đọc các cột khác nếu cần

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return contactList;
    }
}