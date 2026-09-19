package com.cinemako.ui;

public class TableColumn {

    public enum Alignment {
         LEFT,
         CENTER,
         RIGHT
    }

    private final String header;
    private final Alignment alignment;
    private final int minWidth;

    public TableColumn(
            String header,
            Alignment alignment,
            int minWidth
    ) {
        this.header = header;
        this.alignment = alignment;
        this.minWidth = minWidth;
    }

    public TableColumn(String header) {
        this(
                header,
                Alignment.LEFT,
                3
        );
    }

    public String getHeader() {
        return header;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public int getMinWidth() {
        return minWidth;
    }
}
