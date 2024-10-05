package com.example.lab4_20213849.Dtos;

import java.io.Serializable;

public class PositionDto implements Serializable {
    private Position[] table;

    public Position[] getTable() {
        return table;
    }

    public void setTable(Position[] table) {
        this.table = table;
    }
}
