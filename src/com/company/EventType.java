package com.company;
/**
 * В данном ENUM находятся все возможные виды EventType
 */
public enum EventType {
    BASEBALL("BASEBALL",200),
    THEATRE_PERFORMANCE("THEATRE_PERFOMANCE",300),
    EXPOSITION("EXPOSITION",100);
    public final String NAME;
    public final int VALUE;

    EventType(String NAME,int VALUE) {
        this.NAME=NAME;
        this.VALUE=VALUE;
    }

    public int getVALUE() {
        return VALUE;
    }



    @Override
    public String toString() {
        return NAME;
    }
}
