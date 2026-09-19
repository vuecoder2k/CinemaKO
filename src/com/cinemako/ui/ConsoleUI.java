package com.cinemako.ui;

import com.cinemako.model.*;
import com.cinemako.enums.*;
import com.cinemako.ui.TableColumn.Alignment;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.io.IOException;

public class ConsoleUI {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void showMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("           CINEMAKO MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Danh sách tất cả phim");
        System.out.println("2. Phim đang chiếu");
        System.out.println("3. Phim sắp chiếu");
        System.out.println("4. Lịch chiếu hôm nay");
        System.out.println("5. Bản đồ phòng chiếu");
        System.out.println("6. Đặt vé");
        System.out.println("7. Vé của tôi");
        System.out.println("8. Hủy vé");
        System.out.println("9. Lịch sử giao dịch");
        System.out.println("0. Thoát");
        System.out.println("----------------------------------------");
    }

    public void showMovies(List<Movie> movies, String title) {
        System.out.println("\n--- " + title + " ---");
        ConsoleTable table = new ConsoleTable(
                new TableColumn("ID", Alignment.CENTER, 6),
                new TableColumn("TÊN PHIM", Alignment.LEFT, 25),
                new TableColumn("THỂ LOẠI", Alignment.LEFT, 15),
                new TableColumn("THỜI LƯỢNG", Alignment.CENTER, 12),
                new TableColumn("TRẠNG THÁI", Alignment.CENTER, 15)
        );

        for (Movie movie : movies) {
            table.addRow(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getGenre(),
                    movie.getDuration() + " phút",
                    movie.getStatus().toString()
            );
        }
        table.print();
    }

    public void showShowTimes(List<ShowTime> showTimes) {
        System.out.println("\n--- LỊCH CHIẾU HÔM NAY ---");
        ConsoleTable table = new ConsoleTable(
                new TableColumn("ID", Alignment.CENTER, 6),
                new TableColumn("TÊN PHIM", Alignment.LEFT, 20),
                new TableColumn("PHÒNG", Alignment.CENTER, 8),
                new TableColumn("BẮT ĐẦU", Alignment.CENTER, 18),
                new TableColumn("GIÁ VÉ", Alignment.RIGHT, 12)
        );

        for (ShowTime st : showTimes) {
            table.addRow(
                    st.getId(),
                    st.getMovie().getTitle(),
                    st.getRoom().getName(),
                    st.getStartTime().format(DATE_TIME_FORMATTER),
                    String.format("%,.0f", st.getTicketPrice())
            );
        }
        table.print();
    }

    public void showSeatMap(ShowTime showTime) {
        Room room = showTime.getRoom();
        List<Seat> seats = room.getSeats();
        Set<String> booked = showTime.getBookedSeats();
        
        System.out.println("\n--- BẢN ĐỒ GHẾ: " + room.getName() + " ---");
        System.out.println("Phim: " + showTime.getMovie().getTitle());
        System.out.println("Suất chiếu: " + showTime.getStartTime().format(DATE_TIME_FORMATTER));
        System.out.println();
        
        int maxRow = 0;
        int maxColumn = 0;

        for (Seat seat : seats) {
            maxRow = Math.max(maxRow, seat.getRow());
            maxColumn = Math.max(maxColumn, seat.getColumn());
        }

        System.out.print("    ");
        for (int column = 1; column <= maxColumn; column++) {
            System.out.printf("%-6d", column);
        }
        System.out.println();

        for (int row = 1; row <= maxRow; row++) {
            char rowName = (char) ('A' + row - 1);
            System.out.printf("%-4s", rowName);

            for (int column = 1; column <= maxColumn; column++) {
                Seat seat = findSeat(seats, row, column);
                if (seat == null) {
                    System.out.printf("%-6s", " ");
                    continue;
                }

                String display = getSeatDisplay(seat, booked);
                System.out.printf("%-6s", display);
            }
            System.out.println();
        }
        System.out.println("\n[N] Normal    [V] VIP    [C] Couple    [X] Đã đặt");
    }

    private Seat findSeat(List<Seat> seats, int row, int col) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == col) {
                return seat;
            }
        }
        return null;
    }

    private String getSeatDisplay(Seat seat, Set<String> booked) {
        if (booked.contains(seat.getSeatNumber().toUpperCase())) {
            return "[X]";
        }
        switch (seat.getType()) {
            case VIP: return "[V]";
            case COUPLE: return "[C]";
            default: return "[N]";
        }
    }

    public void showMyTickets(List<Ticket> tickets) {
        System.out.println("\n========================================");
        System.out.println("             VÉ CỦA TÔI");
        System.out.println("========================================");

        if (tickets.isEmpty()) {
            System.out.println("Bạn chưa có vé nào.");
            return;
        }

        for (Ticket ticket : tickets) {
            showTicket(ticket);
        }
    }

    public void showTicket(Ticket ticket) {
        ShowTime st = ticket.getShowTime();
        System.out.println("----------------------------------------");
        System.out.println("Mã vé:       " + ticket.getId());
        System.out.println("Phim:        " + st.getMovie().getTitle());
        System.out.println("Phòng:       " + st.getRoom().getName());
        System.out.println("Thời gian:   " + st.getStartTime().format(DATE_TIME_FORMATTER));
        System.out.println("Ghế:         " + String.join(", ", ticket.getSeats()));
        System.out.println("Tổng tiền:   " + String.format("%,.0f VNĐ", ticket.getTotalPrice()));
        System.out.println("Trạng thái:  " + ticket.getStatus());
    }
    
    public void showCinemakoMap() {
         String filePath = "src/com/cinemako/data/cinema_map.txt";
         try {
             List<String> lines = Files.readAllLines(Paths.get(filePath));
             System.out.println();
             for (String line : lines) {
                 System.out.println(line);
             }
         } catch (IOException e) {
             System.err.println("Không thể đọc tệp sơ đồ rạp: " + e.getMessage());
         }
    }

    public void showTransactions(List<Transaction> transactions) {
        System.out.println("\n--- LỊCH SỬ GIAO DỊCH ---");
        if (transactions.isEmpty()) {
            System.out.println("Chưa có giao dịch nào.");
            return;
        }

        ConsoleTable table = new ConsoleTable(
                new TableColumn("ID", Alignment.CENTER, 6),
                new TableColumn("LOẠI", Alignment.CENTER, 10),
                new TableColumn("SỐ TIỀN", Alignment.RIGHT, 15),
                new TableColumn("THỜI GIAN", Alignment.CENTER, 18),
                new TableColumn("MÔ TẢ", Alignment.LEFT, 25)
        );

        for (Transaction tx : transactions) {
            table.addRow(
                    tx.getId(),
                    tx.getType().toString(),
                    String.format("%,.0f", tx.getAmount()),
                    tx.getTime().format(DATE_TIME_FORMATTER),
                    tx.getDescription()
            );
        }
        table.print();
    }
}
