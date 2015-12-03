package com.siwei.carmen;


import java.io.Serializable;

/**
 * Created by wkd on 15-12-2.
 */
public class Card implements Serializable{
    private String Alias;
    private int BillDay;
    private int DueDay;

    public Card(String pAlias,int pBillDay,int pDueDay){
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
}
