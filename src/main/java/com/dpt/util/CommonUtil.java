package com.dpt.util;

import java.util.Random;

public class CommonUtil {

    public final static String inviteCode(int length) {
        try {
            String base = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
            Random random = new Random();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < length; i++) {
                int number = random.nextInt(base.length());
                stringBuffer.append(base.charAt(number));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
