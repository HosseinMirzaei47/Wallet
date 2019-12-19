package com.example.hossein.wallet;

import java.util.ArrayList;

public class SuggestionsList {

    ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> getList() {
        list.add("Eating out");
        list.add("Groceries");
        list.add("Entertainment");
        list.add("Hair cut");
        list.add("Hair cut");
        list.add("Shopping");
        list.add("Trip");
        list.add("Rent");
        list.add("Insurance");
        list.add("Utilities(cell, water...)");
        list.add("Transportation / Fuel");
        list.add("Personal care");
        list.add("Gifts");
        list.add("Taxes");
        list.add("Internet");
        list.add("Savings");
        list.add("Others");
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
