package com.siwei.carmen;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                String alias = cursor.getString(cursor.getColumnIndex("alias"));
                int billDay = cursor.getInt(cursor.getColumnIndex("billday"));
                int dueDay = cursor.getInt(cursor.getColumnIndex("dueday"));

                Card c = new Card(alias,billDay,dueDay);
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

        long rowId = db.insert("card",null,values);
        if(rowId>0)
            isOk = true;

        return isOk;
    }
}
