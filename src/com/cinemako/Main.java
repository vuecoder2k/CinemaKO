package com.cinemako;

import com.cinemako.model.*;
import com.cinemako.enums.*;
import com.cinemako.ui.*;
import com.cinemako.data.*;
import com.cinemako.service.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final ConsoleUI ui = new ConsoleUI();
    private static final BookingService bookingService = new BookingService();
    private static InputHandler input;

    public static void main(String[] args) {
        try {
            Scanner stdin = new Scanner(System.in);
            input = new InputHandler(stdin);

            Customer customer = new Customer("UID001", "Vue Thang", "0123456789", "thangvu@gmail.com");
            customer.refund(1_000_000);

            List<Movie> movies = MovieData.getMovies();
            List<Room> rooms = RoomData.getRooms();
            List<ShowTime> showTimes = ShowTimeData.getShowTimes(movies, rooms);

            while (true) {
                ui.showMenu();
                int choice = input.readInt("Chọn: ");

                if (choice == 0) {
                    System.out.println("Chương trình kết thúc!");
                    break;
                }

                switch (choice) {
                    case 1:
                        ui.showMovies(movies, "DANH SÁCH TẤT CẢ PHIM");
                        break;
                    case 2:
                        ui.showMovies(filterMovies(movies, MovieStatus.NOW_SHOWING), "PHIM ĐANG CHIẾU");
                        break;
                    case 3:
                        ui.showMovies(filterMovies(movies, MovieStatus.UPCOMING), "PHIM SẮP CHIẾU");
                        break;
                    case 4:
                        ui.showShowTimes(showTimes);
                        break;
                    case 5:
                        ui.showCinemakoMap();
                        break;
                    case 6:
                        bookTicketFlow(showTimes, customer);
                        break;
                    case 7:
                        ui.showMyTickets(customer.getTickets());
                        break;
                    case 8:
                        cancelTicketFlow(customer);
                        break;
                    case 9:
                        ui.showTransactions(customer.getTransactions());
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
                input.pause();
            }
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi nghiêm trọng: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<Movie> filterMovies(List<Movie> movies, MovieStatus status) {
        return movies.stream().filter(m -> m.getStatus() == status).collect(Collectors.toList());
    }

    private static void bookTicketFlow(List<ShowTime> showTimes, Customer customer) {
        ui.showShowTimes(showTimes);
        String stId = input.readLine("Nhập ID suất chiếu: ");
        ShowTime showTime = showTimes.stream()
                .filter(st -> st.getId().equalsIgnoreCase(stId))
                .findFirst().orElse(null);

        if (showTime == null) {
            System.out.println("Không tìm thấy suất chiếu!");
            return;
        }

        ui.showSeatMap(showTime);
        List<String> seats = input.readSeats("Chọn ghế (VD: A1, A2): ");
        if (seats.isEmpty()) return;

        double totalPrice = 0;
        for (String sId : seats) {
            if (!showTime.isSeatAvailable(sId)) {
                System.out.println("Ghế " + sId + " không khả dụng!");
                return;
            }
            totalPrice += showTime.getTicketPrice();
        }

        System.out.println("Tổng tiền: " + String.format("%,.0f VNĐ", totalPrice));
        if (input.readConfirmation("Xác nhận đặt vé? (Y/N): ")) {
            String result = bookingService.bookTicket(customer, showTime, seats, totalPrice);
            if (result.startsWith("SUCCESS")) {
                System.out.println("Đặt vé thành công! Mã vé: " + result.split(":")[1]);
            } else {
                System.out.println("Lỗi: " + result);
            }
        }
    }

    private static void cancelTicketFlow(Customer customer) {
        ui.showMyTickets(customer.getTickets());
        String ticketId = input.readLine("Nhập mã vé muốn hủy: ");
        Ticket ticket = bookingService.findTicket(customer.getTickets(), ticketId);

        if (ticket == null) {
            System.out.println("Không tìm thấy vé!");
            return;
        }

        if (input.readConfirmation("Xác nhận hủy vé " + ticketId + "? (Y/N): ")) {
            String result = bookingService.cancelTicket(customer, ticket);
            if (result.equals("SUCCESS")) {
                System.out.println("Hủy vé thành công! Đã hoàn tiền.");
            } else {
                System.out.println("Lỗi: " + result);
            }
        }
    }
}
