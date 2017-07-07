package com.br.denis.classesBasicas;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by denis on 06/07/17.
 */

public class Util {
    public static String dateToString(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", new Locale("pt", "BR"));
        return f.format(date);
    }

    public static String dateToStringBR(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy", new Locale("pt", "BR"));
        return f.format(date);
    }
    public static Date stringToDateComplete(String s) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static Date stringToOnlyDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy", new Locale("pt", "BR"));
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String stringToStringWS(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy", new Locale("pt", "BR"));
        SimpleDateFormat dateFormatWS = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate;
        String result = "";
        try {
            convertedDate = dateFormat.parse(s);
            result = dateFormatWS.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date changeDay(Date dataInicial, int numeroDias) {
        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(dataInicial);

        calendarData.add(Calendar.DATE,numeroDias);
        return calendarData.getTime();
    }

    static String dateMonthToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", new Locale("pt", "BR"));

        return formatter.format(date);
    }

    public static Date stringHourToDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String dateToHourMinute(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public static int dateToHour(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        return Integer.parseInt(dateFormat.format(date));
    }

    public static int dateToMinute(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
        return Integer.parseInt(dateFormat.format(date));
    }

    public static boolean checkHour(int startHour, int startMinute, int endHour, int endMinute) {
        if(endHour > startHour) {
            return true;
        } else if (endHour == startHour){
            return endMinute > startMinute;
        }
        return false;
    }

    public static String stringToStringWSComplete(String currentDate, String currentStartTime) {
        String aux = currentDate + " " + currentStartTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM, yyyy HH:mm");
        SimpleDateFormat dateFormatWS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt", "BR"));
        Date convertedDate;
        String result = "";
        try {
            convertedDate = dateFormat.parse(aux);
            result = dateFormatWS.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return replaceSpaceWS(result);
    }

    public static String replaceSpaceWS(String s) {
        return s.replace(" ", "%20");
    }

    public static String stringToStringDayWeek(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy", new Locale("pt", "BR"));
        SimpleDateFormat dateFormatWS = new SimpleDateFormat("EEEE d MMMM, yyyy", new Locale("pt", "BR"));
        Date convertedDate;
        String result = "";
        try {
            convertedDate = dateFormat.parse(s);
            result = dateFormatWS.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String stringToDiffDate(String inicio, String fim) {
        return ""+dateToHourMinute(stringToDateComplete(inicio))+" ~ "+dateToHourMinute(stringToDateComplete(fim));
    }

    public static String dateToMonthyear(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-");
        return dateFormat.format(date);
    }

    public static String datetoDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        return dateFormat.format(date);
    }

    public static String datetoMonthExtend(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        return dateFormat.format(date);
    }

    public static String datetoDayAndMonth(Date date) {
        return datetoDay(date) + " de " + datetoMonthExtend(date);
    }
}
