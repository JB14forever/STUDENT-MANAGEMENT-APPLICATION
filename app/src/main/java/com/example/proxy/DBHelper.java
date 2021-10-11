package com.example.proxy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "StudentData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table if not exists StudentDetails (StudRollNo VARCHAR primary key, StudName VARCHAR , StudBranch VARCHAR, StudPhone VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists StudentDetails");
    }

    public Boolean InsertStudentData(String StudRollNo, String StudName,  String StudBranch, String StudPhone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudRollNo" , StudRollNo);
        contentValues.put("StudName", StudName);
        contentValues.put("StudBranch", StudBranch);
        contentValues.put("StudPhone", StudPhone);
        long result = DB.insert("StudentDetails", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean UpdateStudentData( String StudRollNo, String StudName, String StudBranch, String StudPhone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudRollNo" , StudRollNo);
        contentValues.put("StudName", StudName);
        contentValues.put("StudBranch", StudBranch);
        contentValues.put("StudPhone", StudPhone);
        Cursor cursor = DB.rawQuery("Select * from StudentDetails where StudRollNo = ?", new String[]{StudRollNo});
        if (cursor.getCount() > 0) {
            long result = DB.update("StudentDetails", contentValues, "StudRollNo=?", new String[]{StudRollNo});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean DeleteStudentData (String StudRollNo)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from StudentDetails where StudRollNo = ?", new String[]{StudRollNo});
        if (cursor.getCount() > 0) {
            long result = DB.delete("StudentDetails", "StudRollNo=?", new String[]{StudRollNo});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor GetData ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from StudentDetails", null);
        return cursor;

    }
}
