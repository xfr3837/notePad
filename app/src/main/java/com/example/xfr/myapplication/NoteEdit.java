package com.example.xfr.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 笔记编辑界面
 * Created by xfr on 2017/12/23.
 */

public class NoteEdit extends AppCompatActivity {

    private List<Note> noteList = new ArrayList<>();
    private EditText edit;
    private NoteDatabase noteDatabase;
    private static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        edit = (EditText) findViewById(R.id.edit);
        noteDatabase = new NoteDatabase(this, "Note.db", null, 1);
        Intent intent = getIntent();
        id = intent.getIntExtra("noteId", -1);


        if (id == -1)
            // 新打开的笔记
            edit.setHint("请输入...");
        else{
            // 修改笔记
            SQLiteDatabase db = noteDatabase.getWritableDatabase();
            Cursor cursor = db.query("Note", null, null, null, null, null, null);
            // 清除首页并重新加载
            noteList.clear();
            if (cursor.moveToFirst()) {
                do {
                    int queryId = cursor.getInt(cursor.getColumnIndex("id"));
                    if (queryId == id) {
                        String content = cursor.getString(cursor.getColumnIndex("content"));
                        edit.setText(content);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    @Override
    protected void onDestroy() {
        if (id == -1)
            saveData();
        else
            updateData();
        super.onDestroy();
    }

    /**
     * 更新数据
     */
    private void updateData() {
        String inputText = edit.getText().toString();
        String time = getDateToString();

        if (inputText == null || inputText.isEmpty()) {
            Toast.makeText(this, "未更新！", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = noteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("time", time);
        values.put("content", inputText);
        db.update("Note", values, "id = ?", new String[] {String.valueOf(id)});

        Toast.makeText(this, "保存成功！", Toast.LENGTH_LONG).show();
    }


    /**
     * 保存数据
     */
    private void saveData() {

        String inputText = edit.getText().toString();
        String time = getDateToString();

        if (inputText == null || inputText.isEmpty()) {
            Toast.makeText(this, "未输入！", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = noteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("time", time);
        values.put("content", inputText);
        db.insert("Note", null, values);

        Toast.makeText(this, "保存成功！", Toast.LENGTH_LONG).show();
    }

    /**
     * 获取时间戳并转换成字符串
     * @return dateString
     */
    private String getDateToString() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat();
        String dateString = formatter.format(date);
        return dateString;
    }
}
