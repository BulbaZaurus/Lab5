package com.company;

import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Данный класс осуществляет работу программы в целом
 * @author  }{оттабь)ч
 */

public class Main {
    private static Boolean flag=false;


    public static void main(String[] args) throws IOException, XMLStreamException, SAXException {
        System.out.println("Введите путь к файлу или просто прустите этот шаг");
        Scanner scanner=new Scanner(System.in);
       FileWork current=new FileWork(scanner.nextLine());
       FileWork second =new FileWork("mrTramp.xml");
        TreeSet<Ticket> collection = current.readFromFile();
        LocalDateTime dateTime = LocalDateTime.now();
        Ticket ticket = new Ticket(312312414, "me", new Coordinates(200, 900), (float) 56.88, "ds",
                false, TicketType.VIP, new Event((long) 423, "Me", EventType.EXPOSITION, LocalDate.now()), new Date());
        collection.add(ticket);


        HashSet<String> pathcollection = new HashSet<String>();
        commandReading(collection,ticket,dateTime,"System.in",pathcollection,current,second);

      }


    public  static  boolean commandReading(TreeSet<Ticket> collection,Ticket ticket,LocalDateTime dateTime,String sourse,HashSet<String> pathcollection,FileWork current,FileWork second) throws IOException, XMLStreamException {
        if(flag){

        }else {
            System.out.println("Шалом.Введите help для вывода списка доступных комманд");
        }

        Scanner in;
        if(sourse.equals("System.in"))
            in=new Scanner(System.in);
        else {
            try {
                in=new Scanner(new File(sourse));
            }catch (Exception e){
                System.out.println("Если честоно,то я не виноват.Это файлы у вас какие странные");
                return false;
            }
        }



        String input = in.nextLine();

        while (!input.equalsIgnoreCase("exit")) {

            String[] com = input.split(" ");
            switch (com[0].toLowerCase()) {
                case "help":
                    Archive.add("help");
                    Commands.help();
                    System.out.println("Введите новую команду");
                    break;
                case "info":
                    Archive.add("info");
                    Commands.info(collection, dateTime);
                    System.out.println("Введите новую команду");
                    break;
                case "show":
                    Archive.add("show");
                    Commands.show(collection);
                    System.out.println("Введите новую команду");
                    break;
                case "add":
                    Archive.add("add");
                    collection.add(Commands.add());
                    System.out.println("Элеммент успешно добавлен в коллекцию");
                    System.out.println("Введите новую команду");
                    break;
                case "clear":
                    Archive.add("clear");
                    Commands.clear(collection);
                    System.out.println("Коллекция очищена");
                    System.out.println("Введите новую команду");
                    break;
                case "remove_by_id":
                    Archive.add("remove_by_id");
                    if (com.length == 2) {
                        try {
                            Commands.remove_by_id(Long.parseLong(com[1]), collection);
                        } catch (Exception e) {
                            System.out.println("Ошибки.Ошибки везде");
                        }
                    } else {
                        System.out.println("требуется ввод id " + (com.length - 1));
                    }
                    System.out.println("Введите новую команду");
                    break;
                case "update_id":
                    Archive.add("update_id");
                    if (com.length == 2) {
                        try {
                            Commands.update_id(Long.parseLong(com[1]), collection);
                        } catch (Exception e) {
                            System.out.println("Дратути.Вам ошибка - распишитесь ");
                        }
                    } else {
                        System.out.println("Требуется ввод id " + (com.length - 1));
                    }
                    System.out.println("Введите новую команду");
                    break;
                case "add_if_max":
                    Archive.add("add_if_max");
                    Ticket ticket1=Commands.add_if_max(collection);
                    if(ticket1!=null)
                        collection.add(ticket1);
                    System.out.println("Эллемент был добавлен");
                    System.out.println("Введите новую команду");
                    break;
                case "remove_greater":
                    Archive.add("remove_greater");
                    try {
                        float price=Float.parseFloat(com[1]);
                        collection.remove(Commands.remove_greater(price,collection));
                        System.out.println("Успешно удалены все элементы с price больше" + price);
                    }catch (Exception e){
                        System.out.println("Ах как хочеться вернуться \n"
                        +"Ах как хочеться попасть в городок \n"
                        + "И попробовать снова");
                    }

                    System.out.println("Введите новую команду");
                    break;
                case "print_field_descending_comment":
                    Archive.add("print_field_descending_comment");
                    Commands.print_field_descending_comment(collection);
                    System.out.println("Введите новую команду");
                    break;
                case "print_field_descending_price":
                    Archive.add("print_field_descending_price");
                    Commands.print_field_descending_price(collection);
                    System.out.println("Введите новую команду");
                    break;
                case "filter_less_than_event":
                    Archive.add("filter_less_than_event");
                    if (com.length == 2) {
                        try {
                            Commands.filter_less_than_event(collection, Long.parseLong(com[1]));
                        } catch (NullPointerException e) {
                            System.out.println("Проблемы.Проблемы-они везде");
                            System.out.println("Ну или просто нету таких элементов");
                        }
                        catch (Exception e) {
                            System.out.println("Проблемы.Проблемы-они везде");
                            System.out.println("Ну или просто нету таких элементов");
                        }
                    } else {
                        System.out.println("Необходим ввод  значения поля event ");
                    }
                    System.out.println("Введите новую команду");
                    break;

                case "history":
                    Commands.history();
                    System.out.println("Введите новую команду");
                    break;
                case "save":
                    Archive.add("save");
                    try{
                        Boolean good =current.writeToFile(collection);
                        if(good){
                            current.writeToFile(collection);
                            System.out.println("Коллекция была сохранена ");
                        }
                        else {
                            second.writeToFile(collection);
                        }

                    }catch (Exception e){
                        System.out.println("Ах,как хочется вернуться в городок и попытаться снова");
                    }
                    System.out.println("Введите новую команду");
                    break;

                case "execute_script":
                    try {
                        if (pathcollection.contains(com[1]))
                            System.out.println("Файл был вызван ранее");
                        else {
                            pathcollection.add(com[1]);
                            flag=true;
                            commandReading(collection, ticket, dateTime, com[1], pathcollection,current,second);
                            pathcollection.remove(com[1]);
                        }
                    }catch (NoSuchElementException e){
                        System.out.println("Конечный файл поврежден");
                        pathcollection.remove(com[1]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Уууууу.....страшна ,вырубай");
                    }catch (Exception e){
                        System.out.println("Совсем страшно,вырубай и проверь файл");
                    }
                    if(flag){
                        System.out.println("Введите новую команду");
                    }else {

                    }
                    break;


                default:
                    Archive.add(com[0]);
                    System.out.println("Странные у  вас команды.Введите еще раз");

            }
            if (!pathcollection.isEmpty()&&!in.hasNext()){
                in.close();
                return true;
            }
            input=in.nextLine();



        }
        in.close();
        return true;
    }

}