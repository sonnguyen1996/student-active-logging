/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.media.soft.vdm.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProc {

    public static Date convertStringToDate(String input) {
        Date date = null;
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setLenient(false);
            date = sdf.parse(input);
        } catch (Exception ex) {
            return null;
        }
        return date;
    }

    public static String dateToStringddMMyyhhmm(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(date);
    }

    public static String dateToStringddMMyyHHmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date);
    }

    public static Timestamp createTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    public static void main(String[] args) {
        
        System.out.println(stringToDate("20180323", "yyyyMMdd"));
    }

    public static Date stringToDate(String str, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.parse(str);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return new Date();
        }
    }

}
