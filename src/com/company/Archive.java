package com.company;

import java.util.ArrayList;

/**
 * Класс,отвечающий за хранение пользовательских комманд
 */
public class Archive {
    static private ArrayList<String> Archhive=new ArrayList<String>();

    /**
     * Метод запоминает последнии 15 комманд
     * @param command Пользовательская комманда
     */
    public static void add(String command){
        if(Archhive.size()>=15){
            Archhive.remove(0);
        }Archhive.add(command);
    }

    /**
     * Метод записывает комманды
     */
    public static  void write(){
        int i=1;
        for(String command:Archhive){
            System.out.println(i + ". " + command);
            i++;
        }
    }
}
