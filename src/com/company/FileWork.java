package com.company;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс,отвечающий за чтение и запись коллекции в файл
 */
public class FileWork {
    private String path;
    public FileWork(String path){
        this.path=path;
    }

    /**
     * Метод ,отвечающий за запись в файл
     * @param collection  коллекция Ticket`ов
     * @return boolean
     * @throws IOException исключение
     */
    public boolean writeToFile(TreeSet<Ticket> collection) throws IOException {
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(this.path));
        }catch (Exception e){
            System.out.println("Файл указаный при запуске не был обнаружен"+"\n" +"запись была произведена в резервный файл");
            return false;
        }
        Iterator<Ticket> iterator=collection.iterator();
        bufferedWriter.write("<Tickets>"+"\n");
        while (iterator.hasNext()){
            Ticket ticket=iterator.next();
            bufferedWriter.write("  <Ticket>"+"\n");
            bufferedWriter.write("    <ID>"+" "+ticket.getId()+" "+"</ID>"+"\n");
            bufferedWriter.write("    <name>"+" "+ticket.getName()+" "+"</name>"+"\n");
            bufferedWriter.write("    <coordinatesX>"+" "+ticket.getCoordinates().getX()+" "+"</coordinatesX>"+"\n");
            bufferedWriter.write("    <coordinatesY>"+" "+ticket.getCoordinates().getY()+" "+"</coordinatesY>"+"\n");
            bufferedWriter.write("    <price>"+" "+ticket.getPrice()+" "+"</price>"+"\n");
            bufferedWriter.write("    <comment>"+" "+ticket.getComment()+" "+"</comment>"+"\n");
            bufferedWriter.write("    <type>"+" "+ticket.getType()+" "+"</type>"+"\n");
            bufferedWriter.write("    <eventType>"+" "+ticket.getEvent().getEventType()+" "+"</eventType>"+"\n");
            bufferedWriter.write("    <eventName>"+" "+ticket.getEvent().getName()+" "+"</eventName>"+"\n");
            bufferedWriter.write("    <eventID>"+" "+ticket.getEvent().getId()+" "+"</eventID>"+"\n");
            bufferedWriter.write("    <Refundable>"+" "+ticket.getRefundable()+" "+"</Refundable>"+"\n");
            bufferedWriter.write("  </Ticket>"+"\n");
        }
        bufferedWriter.write("</Tickets>");
        bufferedWriter.close();
        return true;
    }

    /**
     * Метод,отвечающий за чтение из файла
     * @return TreeSet
     *
     */
    public TreeSet<Ticket> readFromFile(){
        TreeSet<Ticket> ticketTreeSet =new TreeSet<Ticket>();
        try {
            Scanner scanner = new Scanner(new File(this.path));
            scanner.nextLine();
            while (scanner.next().equals("<Ticket>")){

                scanner.nextLine();
                scanner.skip("    <ID>");
                long ID=scanner.nextLong();
                scanner.nextLine();
                scanner.skip("    <name>");
                String name=scanner.next();
                scanner.nextLine();
                scanner.skip("    <coordinatesX>");
                String x=scanner.next();
                scanner.nextLine();
                scanner.skip("    <coordinatesY>");
                String y=scanner.next();
                scanner.nextLine();
                Coordinates coordinates=new Coordinates(Float.valueOf(x),Double.valueOf(y));
                scanner.skip("    <price>");
                String price=scanner.next();
                scanner.nextLine();
                scanner.skip("    <comment>");
                String comment=scanner.next();
                scanner.nextLine();
                scanner.skip("    <type>");
                String typetype=scanner.next();
                TicketType type;
                type=TicketType.valueOf(typetype);
                scanner.nextLine();
                scanner.skip("    <eventType>");
                String eventtypetype=scanner.next();
                EventType eventType;
                eventType=EventType.valueOf(eventtypetype);
                scanner.nextLine();
                scanner.skip("    <eventName>");
                String eventName=scanner.next();
                scanner.nextLine();
                scanner.skip("    <eventID>");
                long eventID=scanner.nextLong();
                scanner.nextLine();
                Event event=new Event(eventID,eventName,eventType, LocalDate.now());
                scanner.skip("    <Refundable>");
                boolean refundable=scanner.nextBoolean();
                scanner.nextLine();
                scanner.nextLine();
                ticketTreeSet.add(new Ticket(ID,name,coordinates,Float.parseFloat(price),comment,refundable,type,event,new Date()));

            }

        }catch (IllegalArgumentException e){
            System.out.println("Опачки.А тут эксепшион.Файл то у вас битый");
        }
        catch (NoSuchElementException e){
            System.out.println("Файл наверное пустой или что то еще ");
        }
        catch (FileNotFoundException e){
            System.out.println("Файл  для загрузки не найден");
            System.out.println("Добавьте элемент через пользовательские команды");
        }catch (Exception e){
            System.out.println("Экшепшоны повсюду");
        }
        return ticketTreeSet;
    }
}
