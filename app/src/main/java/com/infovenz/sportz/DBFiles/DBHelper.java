package com.infovenz.sportz.DBFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase shopDB) {
        try{
            shopDB.execSQL("create Table Usersignup(name TEXT primary key, email TEXT, password TEXT, mobileNumber TEXT)");
            shopDB.execSQL("create Table CartPage(productID NUMBER primary key, productName TEXT, productRate TEXT, productCount NUMBER,productImage NUMBER)");
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase shopDB, int oldVersion, int newVersion) {
        try{
            shopDB.execSQL("drop Table if exists Usersignup");
            shopDB.execSQL("drop Table if exists CartPage");
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
        }
    }

    public Boolean DeleteCartData(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete("CartPage", null, null);
            if(result ==-1)
                return false;
            else
                return true;
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
            return false;
        }
    }

    public Boolean insertUserData(String name, String email, String password, String mobile){

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("email", email);
            contentValues.put("password", password);
            contentValues.put("mobileNumber", mobile);

            long result = db.insert("Usersignup", null, contentValues);
            if(result ==-1)
                return false;
            else
                return true;
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
            return false;
        }
    }

    public Boolean insertProductDetails(Integer p_ID, String p_Name, String p_Rate, Integer p_count, Integer p_Image){

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("productID", p_ID);
            contentValues.put("productName", p_Name);
            contentValues.put("productRate", p_Rate);
            contentValues.put("productCount", p_count);
            contentValues.put("productImage", p_Image);

            long result = db.insert("CartPage", null, contentValues);
            if(result ==-1)
                return false;
            else
                return true;
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
            return false;
        }
    }

    public Cursor getUserData(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from Usersignup", null);
            return cursor;
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
            return null;
        }
    }

    public Cursor getUserData(String email){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String sql="select * from Usersignup where email =?";
            Cursor cursor=db.rawQuery(sql,new String[]{email});
            return cursor;
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
            return null;
        }
    }

    public Cursor getCartData(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from CartPage", null);
            return cursor;
        }
        catch (Exception e){
            Log.i("Excepetion",e.getMessage().toString());
            return null;
        }
    }
}
