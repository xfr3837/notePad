package com.example.xfr.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 适配器，用于首页瀑布流
 * Created by xfr on 2017/12/23.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> implements View.OnClickListener {

    private List<Note> mNoteList;
    private static int id;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View noteView;
        TextView time;
        TextView content;

        public ViewHolder(View view) {
            super(view);
            noteView = view;
            time = (TextView) view.findViewById(R.id.note_time);
            content = (TextView) view.findViewById(R.id.note_content);
        }
    }

    public NoteAdapter(List<Note> noteList) {
        mNoteList = noteList;
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(view.getContext());
        deleteDialog.setTitle("是否删除");
        deleteDialog.setMessage("是否删除此条便签");
        deleteDialog.setCancelable(false);
        deleteDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
                db.delete("Note", "id = ?", new String[] {String.valueOf(id)});
            }
        });
        deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        deleteDialog.show();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);


        /**
         * 短按
         */
        holder.noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Note note = mNoteList.get(position);
                id = note.getId();
                Intent editIntent = new Intent(view.getContext(), NoteEdit.class);
                editIntent.putExtra("noteId", id);
                view.getContext().startActivity(editIntent);
            }
        });


        /**
         * 长按
         */
        holder.noteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                Note note = mNoteList.get(position);
                id = note.getId();
                onClick(view);
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.time.setText(note.getTime());
        holder.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
