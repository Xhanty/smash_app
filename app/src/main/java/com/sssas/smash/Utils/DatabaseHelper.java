package com.sssas.smash.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB_SMASH";

    //USUARIOS
    public static final String TBL_USUARIOS = "TBL_USUARIOS";
    public static final String QUERY_USUARIOS = "CREATE TABLE TBL_USUARIOS (ID INTEGER PRIMARY KEY, " +
            "USUARIO TEXT, NOMBRE_USUARIO TEXT, ID_INSTITUCION INTEGER, ID_PERFIL INTEGER, TELEFONO TEXT, EMAIL TEXT, CLAVE TEXT, ID_ESTATUS INTEGER," +
            "FECHA_REGISTRO DATE, FECHA_UPDATE_CLAVE DATE, ESTADO_CLAVE INTEGER, PAIS INTEGER)";

    //PROYECTOS
    public static final String TBL_PROYECTOS = "TBL_PROYECTOS";
    public static final String QUERY_PROYECTOS = "";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_USUARIOS);
        //db.execSQL(QUERY_PROYECTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USUARIOS);
        //db.execSQL("DROP TABLE IF EXISTS " + TBL_PROYECTOS);
        onCreate(db);
    }

    public Cursor getData(String query){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(query, null);
        return res;
    }

    public boolean setData(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, values);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String table, ContentValues values, String clause, String[] params){
        SQLiteDatabase db = this.getWritableDatabase();

        /*String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE, title);
        String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "MyOldTitle" };*/
        long result = db.update(table, values, clause, params);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean delAllData(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table, null, null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean delSelectData(String table, String selection, String[] params){
        SQLiteDatabase db = this.getWritableDatabase();
        //String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        //String[] selectionArgs = { "MyTitle" };
        long result = db.delete(table, selection, params);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }
}
