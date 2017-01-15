package com.example.lenovo.baymax2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by LENOVO on 17/11/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String dbname="baymax";
    private static final int ver=5;
    private static final String tb1="sickness";
    private static final String tb2="cure";
    //tb1
    private static final String primary="_id";
    private static final String title="title";
    private static final String sp="key";
    //tb2
    private static final String f="aid_id";
    private static final String steps="steps";
    private static final String pic="pic";
    private Context context;
    private ArrayList<ContentValues>tb1v;
    private ArrayList<ContentValues>tb2v;


    public DBHelper(Context context,ArrayList<ContentValues>tb1v,ArrayList<ContentValues>tb2v) {
        super(context, dbname, null, ver);
        this.context=context;
        this.tb1v=tb1v;
        this.tb2v=tb2v;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="CREATE TABLE "+tb1+" ("+primary+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                title+" VARCHAR(255),"+sp+" INTEGER UNIQUE);";
        String q2="CREATE TABLE "+tb2+" ("+primary+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                steps+" VARCHAR(255),"+f+" INTEGER,"+pic+" INTEGER);";
        try {
            db.execSQL(q);
            db.execSQL(q2);
            for (int i=0;i<=tb1v.size()-1;i++)
                db.insert(tb1,null,tb1v.get(i));
            for (int j=0;j<=tb2v.size()-1;j++)
                db.insert(tb2,null,tb2v.get(j));
            Toast.makeText(context,"Successfully created the database",Toast.LENGTH_LONG);

        }catch (Exception ex){
            Toast.makeText(context,ex.toString(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            String d="DROP TABLE "+tb1;
            String d2="DROP TABLE "+tb2;
            db.execSQL(d);
            db.execSQL(d2);
            onCreate(db);
        }catch (Exception ex){
            Toast.makeText(context,ex.toString(),Toast.LENGTH_LONG).show();
        }

    }
}
