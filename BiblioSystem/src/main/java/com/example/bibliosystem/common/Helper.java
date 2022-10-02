package com.example.bibliosystem.common;

public class Helper {
    public static Boolean checkStringLengthBetween(String sToCheck, Integer iFrom, Integer iTo){
        if (iFrom < 0){ iFrom = 0; }
        if (iTo < 0 || iTo < iFrom){ iTo = iFrom; }
        if (sToCheck.equals("null")
                || sToCheck.length() < iFrom
                || sToCheck.length() > iTo){
            return false;
        }
        return false;
    }
}
