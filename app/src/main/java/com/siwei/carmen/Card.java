package com.siwei.carmen;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by wkd on 15-12-2.
 */
public class Card implements Serializable{
    private int Id;
    private String Alias;
    private int BillDay;
    private int DueDay;

    public Card(){

    }
    public Card(int pId,String pAlias,int pBillDay,int pDueDay){
        this.Id = pId;
        this.Alias = pAlias;
        this.BillDay = pBillDay;
        this.DueDay = pDueDay;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public int getBillDay() {
        return BillDay;
    }

    public void setBillDay(int billDay) {
        BillDay = billDay;
    }

    public int getDueDay() {
        return DueDay;
    }

    public void setDueDay(int dueDay) {
        DueDay = dueDay;
    }

    public int getMonthMaxDays () {
        Calendar c = Calendar.getInstance();
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getMaxIFP() {
        // IFP: interest-free period
        if (DueDay >= BillDay) {
            return 30 + DueDay - BillDay - 1;
        } else {
            return 60 - (BillDay - DueDay) - 1;
        }
    }

    public int getMinIFP() {
        if (DueDay >= BillDay) {
            return DueDay - BillDay;
        } else {
            return 30 - (BillDay - DueDay);
        }
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
