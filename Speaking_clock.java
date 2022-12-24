package uk.co.wowcher;

import java.util.HashMap;

public class Main {
    static boolean isPM = false;
    static HashMap<String, String> hoursInWords = new HashMap<String, String>() {{
        put("1", "one");
        put("2", "two");
        put("3", "three");
        put("4", "four");
        put("5", "five");
        put("6", "six");
        put("7", "seven");
        put("8", "eight");
        put("9", "nine");
        put("10", "ten");
        put("11", "eleven");
        put("12", "twelve");
    }};

    static HashMap<String, String> specialMinutesInWords = new HashMap<String, String>() {{
        put("11", "eleven");
        put("12", "twelve");
        put("13", "thirteen");
        put("14", "thirteen");
        put("15", "fifteen");
        put("16", "sixteen");
        put("17", "seventeen");
        put("18", "eighteen");
        put("19", "nineteen");

    }};

    static HashMap<String, String> minutesTensInWords = new HashMap<String, String>() {{
        put("0", " o ");
        put("2", "twenty ");
        put("3", "thirty ");
        put("4", "forty ");
        put("5", "fifty ");
    }};

    public static String[] splitTime(String time) {
        return time.split(":");
    }

    public static String getHoursInWords(String hours) {
        if (hours.startsWith("0") && hoursInWords.containsKey(String.valueOf(hours.toCharArray()[1]))) {
            String hour = String.valueOf(hours.toCharArray()[1]);
            return hoursInWords.get(hour);
        } else if (Integer.parseInt(hours) > 12 ) {
            // since it doesn't start with 0, and isn't before 12, we can calculate the hour by substracting 12.
            int hour = Integer.parseInt(hours) - 12;
            isPM = true;
            return hoursInWords.get(String.valueOf(hour));
        }

        return hoursInWords.get(hours);
    }

    public static String getUnitMinutes(String unitMinute, boolean isMinuteDoubleZero) {
         return isMinuteDoubleZero ? "'clock" : hoursInWords.get(unitMinute);
    }

    public static String getMinutes(String minutes) {
        String[] minutesArray = minutes.split("");
        if (Integer.parseInt(minutesArray[0]) > 1) {
            String minutesStr = " " + minutesTensInWords.get(minutesArray[0]);
            if (minutesArray[1].equals("0")) {
                return minutesStr;
            }
            return minutesStr + (getUnitMinutes(minutesArray[1], false));
        } else if (minutesArray[0].equals("0")) {
            String minutesStr = minutesTensInWords.get("0");
            if (minutesArray[1].equals("0")) {
                return getUnitMinutes(minutesArray[1], true);
            }
            return minutesStr + (getUnitMinutes(minutesArray[1], false));
        }

        return specialMinutesInWords.get(minutes);
    }

    public static String getAmPm() {
        return isPM ? " (PM)" : " (AM)";
    }

    public static String getTimeInWords(String time) {
        if (time.equals("12:00")) {
            return "midday";
        } else if (time.equals("24:00")|| time.equals("00:00")) {
            return "midnight";
        }
        String[] splitTime = splitTime(time);
        return getHoursInWords(splitTime[0]) + getMinutes(splitTime[1]) + getAmPm();
    }

    public static void main(String[] args) {
        System.out.println("It's " + getTimeInWords("11:11"));
        System.out.println("It's " + getTimeInWords("24:00"));
        System.out.println("It's " + getTimeInWords("15:12"));
        System.out.println("It's " + getTimeInWords("7:30"));
        System.out.println("It's " + getTimeInWords("13:21"));
    }
}
