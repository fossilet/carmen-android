package com.siwei.carmen;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wkd on 15-12-10.
 * The DAO class to fetch and save card.
 */
public class CardDAO {
    private CarmenDatabaseHelper mDbHelper;

    public CardDAO(CarmenDatabaseHelper DbHelper){
        this.mDbHelper = DbHelper;
    }

    // Fetch cards accord by query condition
    List<Card> FetchCardList(String Condition){
        List<Card> cards = new ArrayList<Card>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query("card",null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String alias = cursor.getString(cursor.getColumnIndex("alias"));
                int billDay = cursor.getInt(cursor.getColumnIndex("billday"));
                int dueDay = cursor.getInt(cursor.getColumnIndex("dueday"));

                Card c = new Card(id,alias,billDay,dueDay);
                cards.add(c);

            }while(cursor.moveToNext());
        }
        cursor.close();

        return cards;
    }

    /**
     * insert card into database.
     * @param pCard
     * @return if success, return true
     */
    Boolean InsertCard(Card pCard){
        Boolean isOk = false;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("alias",pCard.getAlias());
        values.put("billday",pCard.getBillDay());
        values.put("dueday",pCard.getDueDay());

        long rowId = db.insert("card", null, values);
        if(rowId>0)
            isOk = true;

        return isOk;
    }

    Boolean EditCard(Card pCard){
        Boolean isOk = false;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("alias",pCard.getAlias());
        values.put("billday",pCard.getBillDay());
        values.put("dueday",pCard.getDueDay());

        long rowId = db.update("card", values, "Id=?", new String[]{Integer.toString(pCard.getId())});
        if(rowId>0)
            isOk = true;

        return isOk;
    }

    /**
     * delete card from database
     * @param pCard
     * @return if success, return true
     */
    boolean DeleteCard(Card pCard){
        Boolean isOk =false;
        if(pCard!=null) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            int ret = db.delete("card", "alias=?", new String[]{pCard.getAlias()});
            if (ret > 0)
                isOk = true;
        }

        return isOk;
    }

    boolean IsExist(String pAlias){
        Boolean isExist =false;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor tmpCursor = db.rawQuery("select * from card where alias=?", new String[]{pAlias});
        if(tmpCursor.getCount()>0)
            isExist = true;

        return isExist;
    }
    boolean IsExistExceptId(String pAlias,int Id){
        Boolean isExist =false;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor tmpCursor = db.rawQuery("select * from card where alias=? and id <> ?", new String[]{pAlias,String.valueOf(Id)});
        if(tmpCursor.getCount()>0)
            isExist = true;

        return isExist;
    }
}
