package com.company;

/**
 * В данном ENUM находятся все возможные виды TicketType
 */
public enum TicketType {
    VIP("VIP"),
    USUAL("USUAL"),
    BUDGETARY("BUDGETARY"),
    CHEAP("CHEAP");

    public final String NAMETIcket;

    TicketType(String NAMETIcket) {
        this.NAMETIcket=NAMETIcket;
    }
    @Override
    public String toString(){
        return NAMETIcket;
    }
}
