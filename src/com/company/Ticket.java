package com.company;

import java.lang.invoke.LambdaMetafactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * От данного класса создаются все Ticket`ы
 */

public class Ticket implements Comparable<Ticket> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Поле не может быть null, Значение поля должно быть больше 0
    private String comment; //Строка не может быть пустой, Поле не может быть null
    private Boolean refundable; //Поле может быть null
    private TicketType type; //Поле не может быть null
    private Event event; //Поле не может быть null

    public Ticket(long id,String name,Coordinates coordinates,Float price,String comment,Boolean refundable,TicketType type,Event event,Date creationDate){
        this.id=GENERATEID.getNUM();
        this.name=name;
        this.coordinates=coordinates;
        this.price=price;
        this.comment=comment;
        this.refundable=refundable;
        this.type=type;
        this.event=event;
        this.creationDate= creationDate;

    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public void setEvent(Event event) {
      this.event=event;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Event getEvent() {
        return event;
    }

    public TicketType getType() {
        return type;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public int compareTo(Ticket o) {
        if(this.id>o.id) return 1;
        if(this.id<o.id)return -1;
        return 0;
    }

    @Override
    public String toString(){
        return "\n" + "ID: " + id
                + "\n" + "name: " + name
                + "\n" + "coordinatesX: " + coordinates.getX()
                + "\n" + "coordinatesY: " + coordinates.getY()
                + "\n" + "creationDate: " + creationDate
                + "\n" + "price: " + price
                + "\n" + "comment: " + comment
                + "\n" + "type: " + type
                + "\n" + "event Type: " + event.getEventType()
                + "\n" + "event Name: " + event.getName()
                + "\n" + "event ID : " + event.getId()
                + "\n" + "hashCode: " + hashCode()
                + "\n" + "LocalDate: "  + LocalDate.now()
                + "\n" + "Refundable: "  + getRefundable()
                + "\n" + "---------------------------------------";

    }

    public String print_Comment(){
        return "\n" +"comment: " + comment;
    }
    public String print_Price(){
        return "\n" +"price: " + price;
    }

    @Override
    public int hashCode(){
        final int prime=11;
        int result=1;
        result=prime*result+Math.round(price);
        result=prime*result+Math.round(coordinates.getX());
        return  result;
    }



    public String timetoString(){
        String t=String.valueOf(LocalDate.now());
        return  t;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}