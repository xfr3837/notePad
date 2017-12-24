package com.example.xfr.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 笔记数据库
 * Created by xfr on 2017/12/23.
 */

public class NoteDatabase extends SQLiteOpenHelper {
    public static final String CREATE_NOTE = "create table Note ("
            + "id integer primary key autoincrement,"
            + "time text,"
            + "content text)";

    private Context mContext;

    public NoteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTE);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
