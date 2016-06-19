package SQLdatabase;

import android.provider.BaseColumns;

/**
 * Created by hp on 2015/11/23.
 */
public class PeopleStructure {
    public static final String DB_NAME="car.db";
    public static final int DB_VERSION=1;
    public static final String TEXT_TYPE=" TEXT";
    public static final String POINT_TYPE=",";
    public static abstract class PeopleEnyry implements BaseColumns{
        public static final String TABLE_NAME1="car1";
        public static final String TABLE_NAME2="car2";
        public static final String TABLE_NAME3="car3";
        public static final String TABLE_NAME4="car4";
        public static final String COLUMN_NAME_CARID="carId";
        public static final String COLUMN_NAME_Thing="carThing";
        public static final String COLUMN_NAME_Address="carAddress";
        public static final String COLUMN_NAME_Time="carTime";

    }
}
