package com.example.complainhere;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Complain Report";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_USERS = "users";
    public static final String TABLE_COMPLAIN = "complain";
    public static final String KEY_ID_COMPLAIN = "complain_id";
    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_EMAIL = "person_email";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY = "category";
    public static final String SAVERITY = "saverity";
    public static final String COMPLAIN_IMAGE = "complain_image";


    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //byte variable
    public ByteArrayOutputStream objectbyteArrayOutputStream;
    public byte[] ImageInBytes;
    //creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";
    //creating complain table

    public static final String SQL_TABLE_COMPLAIN = " CREATE TABLE " + TABLE_COMPLAIN
            + " ( "
            + KEY_ID_COMPLAIN + " INTEGER PRIMARY KEY, "
            + PERSON_NAME + " TEXT, "
            + PERSON_EMAIL + " TEXT, "
            + DESCRIPTION + " TEXT, "
            + CATEGORY + " TEXT, "
            + SAVERITY + " TEXT, "
            + COMPLAIN_IMAGE + " BLOB NOT NULL"
            + " ) ";


    public SqliteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_COMPLAIN);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS + TABLE_COMPLAIN);
    }

    // method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }
    public long addcomplain(complain complain) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap captureimage = complain.getCaptureimage();
        objectbyteArrayOutputStream = new ByteArrayOutputStream();
        captureimage.compress(Bitmap.CompressFormat.JPEG,100,objectbyteArrayOutputStream);
        ImageInBytes = objectbyteArrayOutputStream.toByteArray();
        //get writable database
        ContentValues complaincontentvalue = new ContentValues();
        complaincontentvalue.put(PERSON_NAME, complain.getPersonName());
        complaincontentvalue.put(PERSON_EMAIL, complain.getEmail());
        complaincontentvalue.put(CATEGORY, complain.getCategory());
        complaincontentvalue.put(DESCRIPTION, complain.getDescription());
        complaincontentvalue.put(SAVERITY, complain.getSaverity());
        complaincontentvalue.put(COMPLAIN_IMAGE, ImageInBytes);

        // insert row
        long complain_id = db.insert(TABLE_COMPLAIN, null, complaincontentvalue );
        Log.d("insert id ", "id ->" + complain_id);
        return  complain_id;
    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
    //get all list of cpmplain
    public ArrayList<complain> getAllImagesData(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<complain> complainListObject = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_COMPLAIN;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() != 0){
           while (cursor.moveToNext()){
               String person= cursor.getString(1);
               String email= cursor.getString(2);
               String imagedescription= cursor.getString(3);
               String imageCategory= cursor.getString(4);
               String imageSaverity= cursor.getString(5);
               byte [] imageBytes = cursor.getBlob(6);
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                complainListObject.add(new complain(person,email,imagedescription,imageCategory,imageSaverity,objectBitmap));
           }
        }
        return complainListObject;
    }
    //get detail where id =1
    public complain getDetails(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COMPLAIN, new String[]{KEY_ID_COMPLAIN,PERSON_NAME,PERSON_EMAIL,DESCRIPTION,CATEGORY,SAVERITY,COMPLAIN_IMAGE},KEY_ID_COMPLAIN +"=?",
                new String[]{String.valueOf(id)},null,null,null);

        if (cursor !=null)
            cursor.moveToFirst();
        byte [] imageBytes = cursor.getBlob(6);
        Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

        complain complain  = new complain(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),objectBitmap);
        return complain;

    }
}

