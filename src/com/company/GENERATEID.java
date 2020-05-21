package com.company;


import java.util.ArrayList;
import java.util.Random;

/**
 * Класс,который генерирует ID
 */
public class GENERATEID {
    private static ArrayList<Long> listNum = new ArrayList<>();

    public static long getNUM(){
        while (true){
            long num=Math.abs(new Random().nextLong());
            boolean b = true;
            for(long l:listNum){
                if(num !=1)
                    b=true;
                else
                    b=false;
            }
            if(b){
                listNum.add(num);
                return num;
            }
        }
    }
}
