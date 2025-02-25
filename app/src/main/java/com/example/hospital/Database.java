package com.example.hospital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users(username TEXT , email TEXT , password TEXT)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "CREATE TABLE cart(username TEXT , product TEXT , price FLOAT, otype TEXT)";
        sqLiteDatabase.execSQL(qry2);

        // Change address to TEXT instead of FLOAT
        String qry3 = "CREATE TABLE orderplace(username TEXT , fullname TEXT , address TEXT, contact TEXT , pincode INT , date TEXT , time TEXT , amount FLOAT , otype TEXT)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username , String email , String password){
        ContentValues values = new ContentValues() ;
        values.put("username" , username);
        values.put("email" , email);
        values.put("password" , password);
        SQLiteDatabase db = getWritableDatabase() ;
        db.insert("users" , null ,values) ;
        db.close();
    }

    public int login(String username , String password){
        int result = 0 ;
        String []str = new String[2] ;
        str[0] = username ;
        str[1] = password ;
        SQLiteDatabase db = getReadableDatabase() ;
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?" , str) ;
        if (cursor.moveToFirst()) {
            result = 1;
        }
        return result ;
    }

    public void addcart(String username , String product , float price , String otype){
        ContentValues values = new ContentValues() ;
        values.put("username" , username);
        values.put("product" , product);
        values.put("price" , price);
        values.put("otype" , otype);
        SQLiteDatabase db = getWritableDatabase() ;
        db.insert("cart" , null ,values) ;
        db.close();
    }

    public int checkcart(String username , String product){
        int result = 0 ;
        String []str = new String[2] ;
        str[0] = username ;
        str[1] = product ;
        SQLiteDatabase db = getReadableDatabase() ;
        Cursor cursor = db.rawQuery("select * from cart where username=? and product=?" , str) ;
        if (cursor.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result ;
    }

    public void removecart(String username , String otype){
        int result = 0 ;
        String []str = new String[2] ;
        str[0] = username ;
        str[1] = otype ;
        SQLiteDatabase db = getWritableDatabase() ;
        db.delete("cart" , "username=? and otype=?" , str) ;
        db.close();
    }

    public ArrayList getcartdata(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>() ;
        SQLiteDatabase db = getReadableDatabase() ;
        String []str = new String[2] ;
        str[0] = username ;
        str[1] = otype ;
        Cursor cursor = db.rawQuery("Select * from cart where username = ? and otype = ? " ,  str) ;
        if (cursor.moveToFirst()){
            do{
                String product = cursor.getString(1) ;
                String price = cursor.getString(2);
                arr.add(product +" $ " + price) ;
            }while (cursor.moveToNext()) ;
        }
        db.close();
        return arr ;
    }

    public void addorder(String username, String fullname, String address, String contact, int pincode , String date , String time , float price , String otype) {

        ContentValues values = new ContentValues() ;
        values.put("username" , username);
        values.put("fullname" , fullname);
        values.put("address" , address);
        values.put("contact" , contact);
        values.put("pincode" , pincode);
        values.put("date" , date);
        values.put("time" , time);
        values.put("amount" , price);
        values.put("otype" , otype);
        SQLiteDatabase db = getWritableDatabase() ;
        db.insert("orderplace" , null ,values) ;
        db.close();
    }

    public ArrayList getorderdata(String username){
        ArrayList<String> arr = new ArrayList<>() ;
        SQLiteDatabase db = getReadableDatabase() ;
        String []str = new String[1] ;
        str[0] = username ;
        Cursor cursor = db.rawQuery("Select * from orderplace where username = ? " ,  str) ;
        if (cursor.moveToFirst()){
            do{
                arr.add(cursor.getString(1) + "$" + cursor.getString(2) + "$" + cursor.getString(3) + "$" + cursor.getString(4) + "$" + cursor.getString(5) + "$" + cursor.getString(6) + "$" + cursor.getString(7) + "$" + cursor.getString(8)) ;
            }while (cursor.moveToNext()) ;
        }
        db.close();
        return arr ;
    }

    public int checkappointmentexist(String username, String fullname, String address, String contact, String date, String time) {
         int result = 0 ;
         String str[] = new String[6] ;
         str[0] = username ;
         str[1] = fullname;
         str[2] = address ;
         str[3] = contact ;
         str[4] = date ;
         str[5] = time ;
         SQLiteDatabase db = getReadableDatabase() ;
         Cursor cursor = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contact = ? and date = ? and time = ?" , str) ;
             if(cursor.moveToFirst()){
                 result = 1 ;
             }
             db.close();
             return result ;
    }
}
