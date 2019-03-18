package com.anto004.app_finals.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.anto004.app_finals.model.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public static final String LOG_TAG = "DBHelper";
    public static final String DATABASE_NAME = "todolist.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "todolist";

    //Column Names
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_ADDITIONAL_INFO = "additional_info";
    public static final String KEY_DUE_DATE = "due_date";

    //Column indexes
    public static final int COLUMN_ID = 0;
    public static final int COLUMN_TITLE = 1;
    public static final int COLUMN_DETAILS = 2;
    public static final int COLUMN_ADDITIONAL_INFO = 3;
    public static final int COLUMN_DUE_DATE = 4;

    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    private static final String INSERT =
            "INSERT INTO " + TABLE_NAME + "(" +
                    KEY_TITLE + "," +
                    KEY_DETAILS + "," +
                    KEY_ADDITIONAL_INFO + "," +
                    KEY_DUE_DATE + ") values(?,?,?,?)";

    public DBHelper(Context context) throws Exception{
        this.context = context;

        try {
            ToDoListDBOpenHelper helper = new ToDoListDBOpenHelper(context);
            db = helper.getReadableDatabase();
            insertStatement = db.compileStatement(INSERT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long insert(ToDoList toDoList){
        insertStatement.bindString(COLUMN_TITLE, toDoList.getTitle());
        insertStatement.bindString(COLUMN_DETAILS, toDoList.getDetails());
        insertStatement.bindString(COLUMN_ADDITIONAL_INFO, toDoList.getAdditionalInfo());
        insertStatement.bindString(COLUMN_DUE_DATE, toDoList.getDueDate());

        long value = -1;

        try {
            value = insertStatement.executeInsert();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public boolean deleteRecord(long rowId){
        String WHERE = KEY_ID + "=?";
        String WHERE_ARGS = String.valueOf(rowId);
        return db.delete(TABLE_NAME, WHERE, new String[]{WHERE_ARGS}) > 0;
    }

    public List<ToDoList> selectAll(){
        List<ToDoList> list = new ArrayList<>();

        String[] columns = new String[]{
                KEY_ID,
                KEY_TITLE,
                KEY_DETAILS,
                KEY_ADDITIONAL_INFO,
                KEY_DUE_DATE
        };

        Cursor cursor = db.query(TABLE_NAME, columns,
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                ToDoList toDoList = new ToDoList();
                toDoList.setId(Long.parseLong(cursor.getString(COLUMN_ID)));
                toDoList.setTitle(cursor.getString(COLUMN_TITLE));
                toDoList.setDetails(cursor.getString(COLUMN_DETAILS));
                toDoList.setAdditionalInfo(cursor.getString(COLUMN_ADDITIONAL_INFO));
                toDoList.setDueDate(cursor.getString(COLUMN_DUE_DATE));

                list.add(toDoList);
            }while(cursor.moveToNext());
        }

        if(cursor != null && !cursor.isClosed()){
            cursor.close();
        }

        return list;
    }

    private static class ToDoListDBOpenHelper extends SQLiteOpenHelper{

        public static final String LOG_TAG = "ToDoListDBOpenHelper";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        KEY_ID + " INTEGER PRIMARY KEY," +
                        KEY_TITLE + " TEXT," +
                        KEY_DETAILS + " TEXT," +
                        KEY_ADDITIONAL_INFO + " TEXT," +
                        KEY_DUE_DATE + " TEXT" +
                        ");";

        public static final String DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;


        public ToDoListDBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
