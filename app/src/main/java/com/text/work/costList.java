package com.text.work;

public class costList {
    private String ID;
    private String Title;
    private String Date;
    private String Moneyin;
    private String Moneyout;

    public String getMoneyin() {
        return Moneyin;
    }
    public void setMoneyin(String moneyin) {
        Moneyin = moneyin;
    }
    public String getMoneyout() {
        return Moneyout;
    }
    public void setMoneyout(String moneyout) {
        Moneyout = moneyout;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID =ID;
    }
}
