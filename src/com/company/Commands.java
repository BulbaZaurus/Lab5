package com.company;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс,отвечающий за реализацию различных комманд
 */

public class Commands {
    /**
     * Комманда "help"
     */
    static void help() {
        System.out.println(
                "help : вывести справку по доступным командам\n" +
                        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                        "add {element} : добавить новый элемент в коллекцию\n" +
                        "update_id {ID} : обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_by_id id : удалить элемент из коллекции по его id\n" +
                        "clear : очистить коллекцию\n" +
                        "save : сохранить коллекцию в файл\n" +
                        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                        "exit :завершить программу (без сохранения в файл)\n " +
                        "add_if_max  : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего  price элемента этой коллекции\n" +
                        "remove_greater  : удалить из коллекции все элементы, превышающие заданный price\n" +
                        "history : вывести последние 15 команд (без их аргументов)\n"+
                        "filter_less_than_event eventID: вывести элементы, значение поля event которых меньше заданного\n"+
                        "print_field_descending_comment : вывести значения поля comment всех элементов в порядке убывания\n" +
                        "print_field_descending_price : вывести значения поля price всех элементов в порядке убывания");
    }


    /**
     * Комманда "history"
     */
    static void history() {
        System.out.println("Последнии введеные пользователем комманды: ");
        Archive.add("history");
        Archive.write();
    }

    /**
     * Комманда "info"
     * @param collection
     * @param creation_date
     */
    static void info(TreeSet<Ticket> collection, LocalDateTime creation_date){
        System.out.println("Тип: TreeSet \n" +
                "Дата инициализации: " + creation_date + '\n' +
                "Количество элементов: " + collection.size() );
    }
    /**
     * Комманда "show"
     * @param collection
     */
    static void show(TreeSet<Ticket> collection){
        Iterator<Ticket> iter=collection.iterator();
        while (iter.hasNext()){
            System.out.println('\n' + iter.next().toString() + '\n');
        }
    }


    /**
     * Комманда "print_field_descending_comment"
     * @param collection
     */
    static void  print_field_descending_comment(TreeSet<Ticket> collection){
        NavigableSet<Ticket> treereverse=collection.descendingSet();
        Iterator<Ticket> iterator=treereverse.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().print_Comment() );
        }
    }
    /**
     * Комманда "print_field_descending_price"
     * @param collection
     */
    static void  print_field_descending_price(TreeSet<Ticket> collection){
        NavigableSet<Ticket> treereverse=collection.descendingSet();
        Iterator<Ticket> iterator=treereverse.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().print_Price() );
        }
    }
    /**
     * Комманда "remove_by_id"
     * @param id
     * @param collection
     */
    static void remove_by_id(long id,TreeSet<Ticket> collection){
        Boolean found =false;
        try{
            found=collection.removeIf((i)->{
                return i.getId()==id;
            });
            if(found){
                System.out.println("Элемент с id"+ id + " "+"был удален");
            }else{
                System.out.println("Элемента с таким id не существует");
            }
        }catch (Exception e){
            System.out.println("Произошла ошибка при удаление элемента по его id");
        }
    }

    /**
     * Комманда "filter_less_than_event"
     * Фильтрация проиходит по id event`a
     * @param collection
     * @param id
     */
    static void filter_less_than_event (TreeSet<Ticket> collection,long id){
        NavigableSet<Ticket> finder=collection;
        Iterator<Ticket> iterator=finder.iterator();
        Ticket t=null;
        Event e=null;
        while (iterator.hasNext()){
            t=iterator.next();
            if(t.getEvent().getId()==id){

                break;
            }
            else {
                t=null;
            }

        }
        if(t!=null){
            e=collection.lower(t).getEvent();
        }else{
            System.out.println("event с меньшим id не был найден");
            return;
        }
        System.out.println(e.toString());

    }
    /**
     * Комманда "remove_greater"
     * Фильтрация проиходит по price
     * @param collection
     * @param price
     */
    static Ticket remove_greater(float price,TreeSet<Ticket> collection) {
        Iterator<Ticket> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getPrice() > price) return ticket;
        }
        System.out.println("Price не найден");
        return null;
    }
    /*static void remove_greater(TreeSet<Ticket> collection,Ticket ticket){
     try{
         collection.add(Commands.add());
         Boolean found =collection.removeIf(x->x.getPrice()<ticket.getPrice());
         if(found){
             System.out.println("Элементы , price которых больше  были удалены ");
         }else{
             System.out.println("Элементов с эквивалентным price не найдено");
         }

     }catch (Exception e){
         System.out.println("Ошибка при выполнение команды  ");
     }
    }
    */
    /**
     * Комманда "add'
     */
    static Ticket add() {
        long id = GENERATEID.getNUM();
        long id_event=GENERATEID.getNUM();
        ;
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = scan.nextLine();
        while (true) {
            if (name.isEmpty()) {
                System.out.println("Пустое имя.Попробуйте еще раз");
                name = scan.nextLine();
            } else break;
        }
        System.out.println("Введите координаты");
        System.out.println("Сначала вводится координата X,а после Y. Вводите координаты через space");
       String str_coords=scan.nextLine();
        float x ;
        double y ;
        while (true){
           try{
              String[] array=str_coords.split(" ",2);

              x=(float) Float.parseFloat(array[0]);
              y=(double) Double.parseDouble(array[1]);
             break;


           }catch (Exception e){
               System.out.println("Некоректно былла введена координата(ы)");
               str_coords=scan.nextLine();

           }

        }

        Coordinates coordinates=new Coordinates(x,y);


        System.out.println("Введите цену за билет");
        String cool_price=scan.nextLine();
        Float price;
        while (true){
            try {
                if(cool_price.isEmpty()){
                    System.out.println("Развбе билет может не иметь цену? Попробуйте еще раз");
                    cool_price=scan.nextLine();

                }
                price=Float.parseFloat(cool_price);
                break;
            }catch (Exception e){
                System.out.println("Не шалите.Введите цену еще раз");
                cool_price=scan.nextLine();
            }
        }

        System.out.println("Введите  комментарии или пожелания");
        String sub_comment=scan.nextLine();
        String comment;
        while (true){
            if(sub_comment.isEmpty()){
                System.out.println("Милсдарь,давай попробуем еще раз.Введи комментарий!");
                sub_comment=scan.nextLine();
            }else break;
        }

        System.out.println("А как на счет возврата? ");
        System.out.println("Введи одно из двух: Да или Нет");
        String ref=scan.nextLine();
        Boolean refundable = null;
        while (true){
          if(ref.equalsIgnoreCase("Да")){
               refundable=true;
               break;
            }else if(ref.equalsIgnoreCase("Нет")){
              refundable=false;
              break;
          }else {

              System.out.println("Утвердите возвращаемость еще раз");
              ref=scan.nextLine();
              continue;
          }

        }


        System.out.println("Давайте поговорим о мироприятиях");
        System.out.println("Есть слудующие виды : BASEBALL, THEATRE_PERFOMANCE, EXPOSITION");
        String sub_event_Type=scan.nextLine();
        EventType eventType;
        while (true){
            if(sub_event_Type.equalsIgnoreCase(EventType.EXPOSITION.toString())){
                eventType=EventType.EXPOSITION;
                break;
            }else  if (sub_event_Type.equalsIgnoreCase(EventType.BASEBALL.toString())){
                eventType=EventType.BASEBALL;
                break;
            }else if (sub_event_Type.equalsIgnoreCase(EventType.THEATRE_PERFORMANCE.toString())){
                eventType=EventType.THEATRE_PERFORMANCE;
                break;
            }else{
                System.out.println("Некоректный вид мироприятия");
                sub_event_Type=scan.nextLine();
                continue;
            }
        }
        System.out.println("Введите имя ивента");
        String sub_event_name=scan.nextLine();
        String event_name;
        while (true){
            if(sub_event_name.isEmpty()){
                System.out.println("Давайте попробуем еще раз.Введите имя event`a");
                sub_event_name=scan.nextLine();
            }else break;
        }

        System.out.println("А теперь о цене - какой тип билете желаем приобрести?");
        System.out.println("Имеются : VIP,USUAL,BUDGETARY, CHEAP" );
        String ticket_sub_type=scan.nextLine();
        TicketType ticketType;
        while (true){
        if(ticket_sub_type.equalsIgnoreCase(TicketType.BUDGETARY.toString())){
            ticketType=TicketType.BUDGETARY;
            break;
        }else if(ticket_sub_type.equalsIgnoreCase(TicketType.VIP.toString())){
            ticketType=TicketType.VIP;
            break;
        }else if(ticket_sub_type.equalsIgnoreCase(TicketType.USUAL.toString())){
            ticketType=TicketType.USUAL;
            break;
        }else if (ticket_sub_type.equalsIgnoreCase(TicketType.CHEAP.toString())){
            ticketType=TicketType.CHEAP;
            break;
        }else {
            System.out.println("Хммм...а таких билетов у нас нет .Давайте попробуем еще раз");
            ticket_sub_type=scan.nextLine();
            continue;
        }
        }
        Date creationDate= new Date();
        LocalDate date=LocalDate.now();



        Event event =new Event(id,sub_event_name,eventType,date);

        return new Ticket(id_event,name,coordinates,price,sub_comment,refundable,ticketType,event,creationDate);
    }
    /**
     * Комманда "clear'
     * @param collection
     */
    static  void clear(TreeSet<Ticket> collection){
        collection.clear();
    }
    /**
     * Комманда "update_id"
     * @param collection
     * @param id
     */
    static void update_id(long id,TreeSet<Ticket> collection){
        try{
            boolean found=false;
            found=collection.removeIf((i)->{
                return i.getId()==id;
            });
            if(found){
                collection.add(Commands.add());

                System.out.println("Успешно обновлен элемент с id "+ id);
            }else {
                System.out.println("Не найден элемент с таким id");
            }
        }catch (Exception e){
            System.out.println("Ошибка во время обновления элемента");
        }
    }
    /**
     * Комманда "add_if_max"
     * @param collection

     */
   /*static void add_if_max(TreeSet<Ticket> collection,Ticket ticket){

       try{
           if(ticket.getPrice()<collection.last().getPrice() && ticket!=null){
               collection.add(Commands.add());
               System.out.println("Элемент с большим price был успешно добавлен");
           }else
               System.out.println("К сожалению,этот элемент имел price ,который меньше максимального");


       }catch (Exception e){
           System.out.println("Все идеть по плану (нет).Произошла ошибка");
       }
   }

    */
   static Ticket add_if_max(TreeSet<Ticket> collection){
       Ticket ticket=add();
       if(ticket.compareTo(collection.last())>0)
           return ticket;
       else return null;
   }
    /*static void save(TreeSet<Ticket> collection) throws IOException, XMLStreamException {
        String outputFileName="file.txt";
        BufferedWriter writer=new BufferedWriter(new FileWriter(outputFileName));
        XMLOutputFactory of = XMLOutputFactory.newInstance();
        XMLStreamWriter XMLWriter = of.createXMLStreamWriter(writer);
        XMLWriter.flush();
        XMLWriter.writeStartDocument();
        writer.append("\n");
        XMLWriter.writeStartElement("Ticket");
        writer.append("\n");
        for(Ticket ticket:collection){
            XMLWriter.writeStartElement("ID");
            XMLWriter.writeCharacters(Long.toString(ticket.getId()));
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("Ticket");
            writer.append("\n");
            XMLWriter.writeStartElement("name");
            XMLWriter.writeCharacters(ticket.getName());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("coordinatesX");
            XMLWriter.writeCharacters(Float.toString(ticket.getCoordinates().getX()));
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("coordinatesY");
            XMLWriter.writeCharacters(Double.toString(ticket.getCoordinates().getY()));
            XMLWriter.writeEndElement();
            writer.append("\n");

            XMLWriter.writeStartElement("creationDate");
            XMLWriter.writeCharacters(ticket.getCreationDate().toString());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("price");
            XMLWriter.writeCharacters(Float.toString(ticket.getPrice()));
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("comment");
            XMLWriter.writeCharacters(ticket.getComment());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("type");
            XMLWriter.writeCharacters(ticket.getType().toString());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("eventType");
            XMLWriter.writeCharacters(ticket.getEvent().getEventType().toString());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("eventName");
            XMLWriter.writeCharacters(ticket.getEvent().getName());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("eventID");
            XMLWriter.writeCharacters(ticket.getEvent().getId().toString());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("hashCode");
            XMLWriter.writeCharacters(Integer.toString(ticket.hashCode()));
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("LocalDate");
            XMLWriter.writeCharacters(ticket.timetoString());
            XMLWriter.writeEndElement();
            writer.append("\n");
            XMLWriter.writeStartElement("Refundable");
            XMLWriter.writeCharacters(ticket.getRefundable().toString());
            XMLWriter.writeEndElement();
            writer.append("\n");

        }
        XMLWriter.writeEndElement();
        XMLWriter.writeEndDocument();
        XMLWriter.close();
        System.out.println("Коллекция была сохранена");
   }

     */












}

