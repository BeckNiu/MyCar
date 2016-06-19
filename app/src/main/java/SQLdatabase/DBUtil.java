package SQLdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.niuben.mycar.Bean.CarBean;

import java.util.ArrayList;

/**
 * Created by hp on 2015/11/24.
 */
public class DBUtil {
    private static ArrayList<CarBean> list;
    private static SQLiteDatabase db;
    private static DateHelp helper;


    public DBUtil() {
        creatDatebase();
    }

    //???????

    public static ArrayList check(Context context,String tableName) {
        list = new ArrayList<>();
        helper = new DateHelp(context);
        db = helper.getWritableDatabase();
        //???????????α?????
        Cursor cursor = db.query(tableName, null, null, null, null, null, null
        );
        //?????????????????????
        while (cursor.moveToNext()) {
            int timeIndex = cursor.getColumnIndex(PeopleStructure.PeopleEnyry.COLUMN_NAME_Time);
            int addressIndex = cursor.getColumnIndex(PeopleStructure.PeopleEnyry.COLUMN_NAME_Address);
            int thingIndex = cursor.getColumnIndex(PeopleStructure.PeopleEnyry.COLUMN_NAME_Thing);
            String time = cursor.getString(timeIndex);
            String address = cursor.getString(addressIndex);
            String thing = cursor.getString(thingIndex);
            CarBean bean = new CarBean();
            bean.setTime(time);
            bean.setAddress(address);
            bean.setThing(thing);
            list.add(bean);
        }
        return list;
    }
    //???????
    public static void delete(Context context, String time,String tableName) {
        helper = new DateHelp(context);
        db = helper.getWritableDatabase();
        String update_whereClause1 = PeopleStructure.PeopleEnyry.COLUMN_NAME_Time + "=?";
        String[] updateWhereArgs1 = {time};
        int deleteRow = db.delete(tableName, update_whereClause1, updateWhereArgs1);
        if (deleteRow == 1) {
            Toast.makeText(context, "??????", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "??????", Toast.LENGTH_LONG).show();
        }
    }

    //???????
    public static void update(Context context, String name, String newTime, String newAddress,String newTing,String tableName) {
        helper = new DateHelp(context);
        db = helper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(PeopleStructure.PeopleEnyry.COLUMN_NAME_Time, newTime);
        values1.put(PeopleStructure.PeopleEnyry.COLUMN_NAME_Address, newAddress);
        values1.put(PeopleStructure.PeopleEnyry.COLUMN_NAME_Thing, newTing);
        String update_whereClause = PeopleStructure.PeopleEnyry.COLUMN_NAME_Time + "=?";
        String[] updateWhereArgs = {name};
        db.update(tableName, values1, update_whereClause, updateWhereArgs);
        Toast.makeText(context, "?????", Toast.LENGTH_LONG).show();
        db.close();
    }

    //????????
    public static void add(Context context, String time, String address,String ting,String tableName) {
        helper = new DateHelp(context);
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PeopleStructure.PeopleEnyry.COLUMN_NAME_Time, time);
        values.put(PeopleStructure.PeopleEnyry.COLUMN_NAME_Address, address);
        values.put(PeopleStructure.PeopleEnyry.COLUMN_NAME_Thing, ting);
        long id = db.insert(tableName, null, values);
        if (id != -1) {
            Toast.makeText(context, "?????", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(context, "??????", Toast.LENGTH_LONG).show();
        }
        db.close();
    }


    private SQLiteDatabase creatDatebase() {
        return db;
    }
}