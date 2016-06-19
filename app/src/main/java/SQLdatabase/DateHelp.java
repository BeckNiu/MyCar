package SQLdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hp on 2015/11/23.
 */
public class DateHelp extends SQLiteOpenHelper {
    public DateHelp(Context content) {
        super(content,PeopleStructure.DB_NAME, null, PeopleStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1="create table "+PeopleStructure. PeopleEnyry.TABLE_NAME1 + "("+ PeopleStructure.PeopleEnyry._ID+" integer primary key autoincrement,"
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Time + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Address + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Thing + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_CARID + PeopleStructure.TEXT_TYPE +")";
        Log.e("语句：",sql1);
        db.execSQL(sql1);
        String sql2="create table "+PeopleStructure. PeopleEnyry.TABLE_NAME2 + "("+ PeopleStructure.PeopleEnyry._ID+" integer primary key autoincrement,"
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Time + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Address + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Thing + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_CARID + PeopleStructure.TEXT_TYPE +")";
       // Log.e("语句：",sql1);
        db.execSQL(sql2);
        String sql3="create table "+PeopleStructure. PeopleEnyry.TABLE_NAME3 + "("+ PeopleStructure.PeopleEnyry._ID+" integer primary key autoincrement,"
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Time + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Address + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Thing + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_CARID + PeopleStructure.TEXT_TYPE +")";
       // Log.e("语句：",sql1);
        db.execSQL(sql3);
        String sql4="create table "+PeopleStructure. PeopleEnyry.TABLE_NAME4 + "("+ PeopleStructure.PeopleEnyry._ID+" integer primary key autoincrement,"
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Time + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Address + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_Thing + PeopleStructure.TEXT_TYPE + PeopleStructure.POINT_TYPE
                + PeopleStructure.PeopleEnyry.COLUMN_NAME_CARID + PeopleStructure.TEXT_TYPE +")";
       // Log.e("语句：",sql1);
        db.execSQL(sql4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
