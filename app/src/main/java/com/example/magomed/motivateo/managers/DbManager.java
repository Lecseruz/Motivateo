package com.example.magomed.motivateo.managers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.magomed.motivateo.models.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DbManager {

    private static final int VERSION = 1;
    private ContentValues cv = new ContentValues();

    @SuppressLint("StaticFieldLeak")
    private static final DbManager INSTANCE = new DbManager();

    private static final String TABLE_NAME = "tasks";

    public static DbManager getInstance(Context context) {
        INSTANCE.context = context.getApplicationContext();
        return INSTANCE;
    }

    private final Executor executor = Executors.newSingleThreadExecutor();

    private Context context;

    private SQLiteDatabase database;

    public void insert(final Task task) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                insertInternal(task);
            }
        });
    }

    public void readAll(final ReadAllListener<String> listener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                readAllInternal(listener);
            }
        });
    }

    public void clean() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cleanInternal();
            }
        });
    }

    private void checkInitialized() {
        if (database != null) {
            return;
        }

        SQLiteOpenHelper helper = new SQLiteOpenHelper(context, "MyDatabase.db", null, VERSION) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                createDatabase(db);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };

        database = helper.getWritableDatabase();
    }


    public void updateTime(Task task){
        checkInitialized();
        cv.put("time", task.getName());


        database.update(TABLE_NAME, cv, "name = ?", new String[] {task.getName()});
    }

    public void updateRepeat(Task task) {
        checkInitialized();
        cv.put("name", task.getName());


        database.update(TABLE_NAME, cv, "name = ?", new String[] {task.getName()});
    }

    private void createDatabase(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, time text, repeat TEXT, remind INTEGER DEFAULT 0, count INTEGER )");
    }

    private void insertInternal(Task task) {
        checkInitialized();

        database.execSQL("INSERT INTO tasks (name, repeat, remind, count) VALUES (?, ?, ?, ?)", new Object[]{task.getName()});
    }

    private void readAllInternal(final ReadAllListener<String> listener) {
        checkInitialized();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor == null) {
            listener.onReadAll(Collections.<String>emptyList());
            return;
        }

        final List<String> result = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
            }
        } finally {
            cursor.close();
        }
        listener.onReadAll(result);
    }

    public void cleanInternal() {
        checkInitialized();

        database.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public interface ReadAllListener<T> {
        void onReadAll(final Collection<T> allItems);
    }
}

