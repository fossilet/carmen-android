package com.siwei.carmen;

import org.joda.time.LocalDate;

import java.io.Serializable;

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

    public static int daysOfMonth(int year, int month) {
        LocalDate date = new LocalDate(year, month, 1);
        return date.dayOfMonth().getMaximumValue();
    }

    public int getIFP(LocalDate date) {
        int day = date.getDayOfMonth();
        if (BillDay <= DueDay) {
            if (day <= BillDay) {
                //     <-current month->
                return getDueDay() - day;
            } else {
                //     <-------------current month------------->   <next month>
                return date.dayOfMonth().getMaximumValue() - day + getDueDay();
            }
        } else {
            if (day <= BillDay) {
                //     <-------------current month------------->   <next month>
                return date.dayOfMonth().getMaximumValue() - day + getDueDay();
            } else {
                //     <-------------current month------------->  <------------------next month-------------------->   <third month>
                return date.dayOfMonth().getMaximumValue() - day + date.plusMonths(1).dayOfMonth().getMaximumValue() + getDueDay();
            }

        }
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
