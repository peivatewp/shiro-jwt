package com.woniuxy.component;

import java.util.Random;

public class SaltUtil {

    public static String salt(Integer num){
        Random random = new Random();
        String str = "qwertyuiopasdfghjklzxcvbnm1234567890";
        String salt= "";
        for (int i = 0; i < num; i++) {
            char ss = str.charAt(random.nextInt(33));
            salt = salt+ss;
        }
        System.out.println(salt);
        return salt;
    }
}
