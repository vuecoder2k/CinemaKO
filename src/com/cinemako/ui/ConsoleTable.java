package com.cinemako.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleTable {

    private final List<TableColumn> columns;
    private final List<List<String>> rows = new ArrayList<>();

    private int padding = 1;

    public ConsoleTable(TableColumn... columns) {

        if (columns == null || columns.length == 0) {
            throw new IllegalArgumentException(
                    "Table must have at least one column."
            );
        }

        this.columns = Arrays.asList(columns);
    }

    /*
     * ============================================================
     * Add row
     * ============================================================
     */

    public ConsoleTable addRow(Object... values) {

        List<String> row = new ArrayList<>();

        for (Object value : values) {

            if (value == null) {
                row.add("");
            } else {
                row.add(value.toString());
            }
        }

        /*
         * Nếu thiếu data
         *
         * columns = 4
         * values   = 2
         *
         * => tự thêm 2 giá trị rỗng
         */

        while (row.size() < columns.size()) {
            row.add("");
        }

        /*
         * Nếu thừa data
         */

        if (row.size() > columns.size()) {

            row = new ArrayList<>(
                    row.subList(0, columns.size())
            );
        }

        rows.add(row);

        return this;
    }

    /*
     * ============================================================
     * Clear rows
     * ============================================================
     */

    public void clearRows() {
        rows.clear();
    }

    /*
     * ============================================================
     * Print
     * ============================================================
     */

    public void print() {

        int terminalWidth = getTerminalWidth();

        int[] widths = calculateColumnWidths(
                terminalWidth
        );

        String separator = createSeparator(widths);

        System.out.println(separator);

        // Header
        List<String> headers = new ArrayList<>();

        for (TableColumn column : columns) {
            headers.add(column.getHeader());
        }

        System.out.println(
                createRow(headers, widths)
        );

        System.out.println(separator);

        // Data
        for (List<String> row : rows) {

            System.out.println(
                    createRow(row, widths)
            );
        }

        System.out.println(separator);
    }

    /*
     * ============================================================
     * Calculate column width
     * ============================================================
     */

    private int[] calculateColumnWidths(
            int terminalWidth
    ) {

        int columnCount = columns.size();

        int[] widths = new int[columnCount];

        /*
         * Bước 1:
         * Width tối thiểu / tự nhiên
         */

        for (int i = 0; i < columnCount; i++) {

            TableColumn column = columns.get(i);

            int width = Math.max(
                    column.getMinWidth(),
                    displayWidth(column.getHeader())
            );

            for (List<String> row : rows) {

                String value = row.get(i);

                width = Math.max(
                        width,
                        displayWidth(value)
                );
            }

            widths[i] = width;
        }

        /*
         * Bước 2:
         * Nếu bảng vừa terminal
         */

        int tableWidth = getTableWidth(widths);

        if (tableWidth <= terminalWidth) {
            return widths;
        }

        /*
         * Bước 3:
         * Bảng quá rộng
         *
         * Ưu tiên giảm column lớn nhất.
         */

        while (tableWidth > terminalWidth) {

            int index = findLargestReducibleColumn(
                    widths
            );

            if (index == -1) {
                break;
            }

            widths[index]--;

            tableWidth = getTableWidth(widths);
        }

        return widths;
    }

    /*
     * ============================================================
     * Find column that can be reduced
     * ============================================================
     */

    private int findLargestReducibleColumn(
            int[] widths
    ) {

        int index = -1;

        for (int i = 0; i < widths.length; i++) {

            int minimumWidth =
                    columns.get(i).getMinWidth();

            if (widths[i] <= minimumWidth) {
                continue;
            }

            if (
                    index == -1
                            || widths[i] > widths[index]
            ) {
                index = i;
            }
        }

        return index;
    }

    /*
     * ============================================================
     * Calculate total table width
     * ============================================================
     */

    private int getTableWidth(int[] widths) {

        /*
         *
         * | ID | TITLE | RATING |
         *
         * Mỗi column:
         *
         * padding trái
         * data
         * padding phải
         * |
         *
         */

        int width = 1;

        for (int columnWidth : widths) {

            width +=
                    columnWidth
                            + padding * 2
                            + 1;
        }

        return width;
    }

    /*
     * ============================================================
     * Create separator
     * ============================================================
     */

    private String createSeparator(int[] widths) {

        StringBuilder result =
                new StringBuilder();

        result.append("+");

        for (int width : widths) {

            result.append(
                    "-".repeat(
                            width + padding * 2
                    )
            );

            result.append("+");
        }

        return result.toString();
    }

    /*
     * ============================================================
     * Create row
     * ============================================================
     */

    private String createRow(
            List<String> values,
            int[] widths
    ) {

        StringBuilder result =
                new StringBuilder();

        result.append("|");

        for (int i = 0; i < widths.length; i++) {

            String value = values.get(i);

            value = truncate(
                    value,
                    widths[i]
            );

            result.append(
                    " ".repeat(padding)
            );

            result.append(
                    align(
                            value,
                            widths[i],
                            columns.get(i).getAlignment()
                    )
            );

            result.append(
                    " ".repeat(padding)
            );

            result.append("|");
        }

        return result.toString();
    }

    /*
     * ============================================================
     * Alignment
     * ============================================================
     */

    private String align(
            String value,
            int width,
            TableColumn.Alignment alignment
    ) {

        int remaining =
                width - displayWidth(value);

        if (remaining <= 0) {
            return value;
        }

        return switch (alignment) {

            case LEFT ->
                    value
                            + " ".repeat(remaining);

            case RIGHT ->
                    " ".repeat(remaining)
                            + value;

            case CENTER -> {

                int left = remaining / 2;
                int right = remaining - left;

                yield
                        " ".repeat(left)
                                + value
                                + " ".repeat(right);
            }
        };
    }

    /*
     * ============================================================
     * Truncate
     * ============================================================
     */

    private String truncate(
            String value,
            int maxWidth
    ) {

        if (value == null) {
            return "";
        }

        if (displayWidth(value) <= maxWidth) {
            return value;
        }

        if (maxWidth <= 3) {
            return value.substring(
                    0,
                    Math.min(
                            value.length(),
                            maxWidth
                    )
            );
        }

        return value.substring(
                0,
                maxWidth - 3
        ) + "...";
    }

    /*
     * ============================================================
     * Display width
     * ============================================================
     */

    private int displayWidth(String value) {

        if (value == null) {
            return 0;
        }

        return value.length();
    }

    /*
     * ============================================================
     * Get terminal width
     * ============================================================
     */

    private int getTerminalWidth() {

        /*
         * Linux / macOS
         */

        try {

            Process process =
                    new ProcessBuilder(
                            "bash",
                            "-c",
                            "tput cols 2>/dev/null"
                    ).start();

            String output =
                    new String(
                            process.getInputStream()
                                    .readAllBytes()
                    ).trim();

            process.waitFor();

            int width =
                    Integer.parseInt(output);

            if (width >= 20) {
                return width;
            }

        } catch (Exception ignored) {
        }

        /*
         * Fallback
         */

        return 80;
    }
}
