package com.example.xfr.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 首页
 * Created by xfr on 2017/12/23.
 */

public class MainActivity extends AppCompatActivity {

    public static NoteDatabase dbHelper;

    private NoteDatabase noteDatabase;
    private List<Note> noteList = new ArrayList<>();
    private final static int ID = -1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 此处有 bug 待修复，返回 MainActivity 时并不会刷新数据
     * 需要进入其他 activity 再次返回后才能刷新
     */
    @Override
    protected void onResume() {
        initNote();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        NoteAdapter adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new NoteDatabase(this, "Note.db", null, 1);
    }


    /**
     * 菜单
     * @param item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent editIntent = new Intent(MainActivity.this, NoteEdit.class);
                editIntent.putExtra("noteId", ID);
                startActivity(editIntent);
                break;
            case R.id.about_item:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
        }
        return true;
    }

    /**
     * 初始化数据
     */
    private void initNote() {
        noteDatabase = new NoteDatabase(this, "Note.db", null, 1);
        SQLiteDatabase db = noteDatabase.getWritableDatabase();
        Cursor cursor = db.query("Note", null, null, null, null, null, null);
        noteList.clear();
        if (cursor.moveToFirst()) {
            do {
                Note note;
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                note = new Note(id, time, getRandomLengthContent(content));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    /**
     * 获取随机长度的内容
     * @param content
     * @return
     */
    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(25) + 45;
        if(content.length() < 70)
            return content;
        return content.substring(0, length) + "...";
    }
}
