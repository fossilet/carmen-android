package com.siwei.carmen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wkd on 15-12-10.
 */
public class CarmenDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CARD = "create table card ("
            + "id integer primary key autoincrement, "
            + "alias text , "
            + "billday integer, "
            + "dueday integer)";
    private Context mContext;

    public CarmenDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,
                                int version){
        super(context,name,factory,version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CARD);
        Log.i("TMP","database created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS carmen");
        db.execSQL(CREATE_CARD);
        Log.i("TMP","database upgrade");

    }
}
