package com.kir.auth;

import org.json.JSONObject;

import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

/**
 * Created by Kirill Zhitelev on 02.02.2016.
 */
public class Util {

    public static boolean checkDate(String date) {
        if (!date.matches("(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(19|20)\\d\\d")) {
            return false;
        }
        String[] dates = date.split("\\.");
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime enteredDate = LocalDateTime.of(parseInt(dates[2]), parseInt(dates[1]), parseInt(dates[0]), 0, 0);

        return enteredDate.isAfter(currentDate);
    }

    public static String convertDateToBaseFormat(String date) {
        String[] dates = date.split("\\.");

        return dates[2] + "-" + dates[1] + "-" + dates[0];
    }


    //If use REST
    public static void convertDateToJson(JSONObject dateObj, String date) {
        dateObj.put("date", date);
    }

    public static String convertDateToViewFormat(String date) {
        String[] dates = date.split("-");

        return dates[2] + "." + dates[1] + "." + dates[0];
    }

}
