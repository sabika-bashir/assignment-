package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =  2;
    public static final String DATABASE_NAME =  "notedbs";
    public static final String DATABASE_TABLE =  "notestables";
    //columns for database
    public static final String Note_ID =  "id";
    public static final String Note_Subject =  "subject";
    public static final String Note_body =  "body";



    database(Context context)
     {
         super(context,DATABASE_NAME,null,DATABASE_VERSION);



     }
    @Override

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + " ( "+ Note_ID +" INT PRIMARY KEY,"+
                Note_Subject + " TEXT,"+
                Note_body + " TEXT"+")";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      if(oldVersion > newVersion)
          return;
      db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
      onCreate(db);
    }
// add data in database
    public long addNote (note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
     c.put(Note_Subject,note.getSubject());
        c.put(Note_body,note.getBody());
        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("insert id ", "id ->" + ID);
        return  ID;

    }
    //get note from database where id =?
    public note getNote(String id){
        SQLiteDatabase db = this.getReadableDatabase();

            String[] query = new String[] {Note_ID,Note_Subject,Note_body};
            Cursor cursor=  db.query(DATABASE_TABLE,query,Note_Subject+"=?",new String[]{String.valueOf(id)},null,null,null,null);
            if(cursor != null)
                cursor.moveToFirst();

            return new note(
                    cursor.getString(1),
                    cursor.getString(2));

    }

    //get all notes
    public List<note> listofNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
     List<note> allNotes = new ArrayList<>();
     String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+Note_ID+" DESC";;
     Cursor cursor = db.rawQuery(query,null);


        if(cursor.moveToFirst()){
            do{
                note note = new note();
                note.setID(cursor.getLong(0));
                note.setSubject(cursor.getString(1));
                note.setBody(cursor.getString(2));
                allNotes.add(note);
            }while (cursor.moveToNext());
        }

        return allNotes;
    }
    public int editNote(note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ note.getSubject() + "\n ID -> "+note.getBody());
        c.put(Note_Subject,note.getSubject());
        c.put(Note_body,note.getBody());
        return db.update(DATABASE_TABLE,c,Note_ID+"=?",new String[]{String.valueOf(note.getID())});

    }



    void deleteNote(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,Note_Subject+"=?",new String[]{id});
        db.close();
    }

}
