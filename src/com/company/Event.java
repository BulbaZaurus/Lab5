package com.company;

import java.time.LocalDate;

/**
 * От данного класса создаются все Event`ы
 */
public class Event {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate date; //Поле может быть null
    private EventType eventType; //Поле может быть null

    public Event(Long id,String name, EventType eventType, LocalDate date){
        this.id=GENERATEID.getNUM();
        this.name=name;
        this.eventType=eventType;
        this.date=date;
    }

    public Long getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return

                 "\n" + "event Type: " + getEventType()
                + "\n" + "event Name: " + getName()
                + "\n" + "event ID : " + getId()

                + "\n" + "LocalDate: "  + LocalDate.now()

                + "\n" + "---------------------------------------";

    }
}
