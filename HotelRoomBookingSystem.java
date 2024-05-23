import java.sql.*;
import java.util.Scanner;

public class HotelRoomBookingSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String userName = "root";
    private static final String password = "Vis@25++";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n*************************");
        System.out.println("Hotel Room Booking System");
        System.out.println("*************************\n");

        if (login()) {
            while (true) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Book Room");
                System.out.println("2. Update Booking");
                System.out.println("3. Cancel Booking");
                System.out.println("4. List of All Bookings");
                System.out.println("5. View or Search Booking By Guest Name or Room Number");
                System.out.println("6. Filter By Date Range");
                System.out.println("7. Filter By Booking Price");
                System.out.println("8. Generate Booking Invoice");
                System.out.println("9. Guest Feedback and Ratings");
                System.out.println("10. View Feedback and Ratings");
                System.out.println("11. Exit\n");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\n1. Book Room");
                        bookRoom();
                        break;
                    case 2:
                        System.out.println("\n2. Update Booking");
                        updateBooking();
                        break;
                    case 3:
                        System.out.println("\n3. Cancel Booking");
                        cancelBooking();
                        break;
                    case 4:
                        System.out.println("\n4. List of All Bookings");
                        listAllBookings();
                        break;
                    case 5:
                        System.out.println("\n5. View or Search Booking by Guest Name or Room Number");
                        viewBooking();
                        break;
                    case 6:
                        System.out.println("\n6. Filter by Date Range");
                        filterByDateRange();
                        break;
                    case 7:
                        System.out.println("\n7. Filter by Booking Price");
                        filterByBookingPrice();
                        break;
                    case 8:
                        System.out.println("\n8. Generate Booking Invoice");
                        generateBookingInvoice();
                        break;
                    case 9:
                        System.out.println("\n9. Guest Feedback and Ratings");
                        guestFeedbackAndRatings();
                        break;
                    case 10:
                        System.out.println("\n10. View Feedback and Ratings");
                        viewGuestFeedbackAndRatings();
                        break;
                    case 11:
                        System.out.println("Exiting the system...");
                        System.exit(0);
                        scanner.close();
                        return;
                    default:
                        System.out.println("\nInvalid choice! Please enter a valid option.");
                }
            }
        } else {
            System.out.println("\nLogin failed. Exiting the system...");
        }
    }

    private static boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String inputPassword = scanner.next();

        if (username.equals("vishal") && inputPassword.equals("abc")) {
            System.out.println("\nLogin successful!");
            return true;
        } else {
            return false;
        }
    }

    private static void bookRoom() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter guest name: ");
            scanner.nextLine();
            String guestName = scanner.nextLine();
            System.out.print("Enter contact number: ");
            String contactNo = scanner.next();
            System.out.print("Enter room number: ");
            int roomNo = scanner.nextInt();
            System.out.print("Enter booking price: ");
            double bookingPrice = scanner.nextDouble();

            String query = "INSERT INTO bookings(guest_name, contact_no, room_no, booking_price) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, guestName);
            preparedStatement.setString(2, contactNo);
            preparedStatement.setInt(3, roomNo);
            preparedStatement.setDouble(4, bookingPrice);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nRoom booked successfully");
            } else {
                System.out.println("\nFailed to book room!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateBooking() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter booking ID: ");
            int bookingId = scanner.nextInt();

            // Check if the booking ID exists
            String checkQuery = "SELECT * FROM bookings WHERE booking_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, bookingId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                System.out.print("\nEnter new guest name: ");
                scanner.nextLine();
                String guestName = scanner.nextLine();
                System.out.print("Enter new contact number: ");
                String contactNo = scanner.next();
                System.out.print("Enter new room number: ");
                int roomNo = scanner.nextInt();
                System.out.print("Enter new booking price: ");
                double bookingPrice = scanner.nextDouble();

                String query = "UPDATE bookings SET guest_name = ?, contact_no = ?, room_no = ?, booking_price = ? WHERE booking_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, guestName);
                preparedStatement.setString(2, contactNo);
                preparedStatement.setInt(3, roomNo);
                preparedStatement.setDouble(4, bookingPrice);
                preparedStatement.setInt(5, bookingId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("\nBooking updated successfully");
                } else {
                    System.out.println("\nFailed to update booking!");
                }
            } else {
                System.out.println("\nPlease give a valid booking ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cancelBooking() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter booking ID to delete: ");
            int bookingId = scanner.nextInt();

            String query = "DELETE FROM bookings WHERE booking_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookingId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nBooking deleted successfully");
            } else {
                System.out.println("\nFailed to delete booking!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listAllBookings() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);

            String query = "SELECT * FROM bookings";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean found = false;

            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            System.out.println("| Booking ID |   Guest Name   | Contact Number  | Room Number| Booking Date        | Booking Price|");
            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");

            while (resultSet.next()) {
                found = true;
                System.out.format("| %-10d | %-14s | %-15s | %-10d | %-17s | %-12.2f |%n",
                        resultSet.getInt("booking_id"),
                        resultSet.getString("guest_name"),
                        resultSet.getString("contact_no"),
                        resultSet.getInt("room_no"),
                        resultSet.getString("booking_date"),
                        resultSet.getDouble("booking_price"));
                System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            }

            if (!found) {
                System.out.println("\nNo bookings found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void viewBooking() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter guest name: ");
            scanner.nextLine();
            String guestName = scanner.nextLine();
            System.out.print("Enter room number: ");
            int roomNo = scanner.nextInt();
            System.out.println();

            String query = "SELECT * FROM bookings WHERE guest_name = ? OR room_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, guestName);
            preparedStatement.setInt(2, roomNo);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean found = false;

            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            System.out.println("| Booking ID |   Guest Name   | Contact Number  | Room Number| Booking Date        | Booking Price|");
            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");

            while (resultSet.next()) {
                found = true;
                System.out.format("| %-10d | %-14s | %-15s | %-10d | %-17s | %-12.2f |%n",
                        resultSet.getInt("booking_id"),
                        resultSet.getString("guest_name"),
                        resultSet.getString("contact_no"),
                        resultSet.getInt("room_no"),
                        resultSet.getString("booking_date"),
                        resultSet.getDouble("booking_price"));
                System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            }

            if (!found) {
                System.out.println("\nNo guest found with this room no.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void filterByDateRange() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter start date (yyyy-MM-dd): ");
            String startDate = scanner.next();
            System.out.print("Enter end date (yyyy-MM-dd): ");
            String endDate = scanner.next();
            System.out.println();

            String query = "SELECT * FROM bookings WHERE booking_date BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean found = false;

            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            System.out.println("| Booking ID |   Guest Name   | Contact Number  | Room Number| Booking Date        | Booking Price|");
            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");

            while (resultSet.next()) {
                found = true;
                System.out.format("| %-10d | %-14s | %-15s | %-10d | %-17s | %-12.2f |%n",
                        resultSet.getInt("booking_id"),
                        resultSet.getString("guest_name"),
                        resultSet.getString("contact_no"),
                        resultSet.getInt("room_no"),
                        resultSet.getString("booking_date"),
                        resultSet.getDouble("booking_price"));
                System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            }

            if (!found) {
                System.out.println("\nNo bookings found in the given date range.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void filterByBookingPrice() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter minimum booking price: ");
            double minPrice = scanner.nextDouble();
            System.out.print("Enter maximum booking price: ");
            double maxPrice = scanner.nextDouble();
            System.out.println();

            String query = "SELECT * FROM bookings WHERE booking_price BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean found = false;

            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            System.out.println("| Booking ID |   Guest Name   | Contact Number  | Room Number| Booking Date        | Booking Price|");
            System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");

            while (resultSet.next()) {
                found = true;
                System.out.format("| %-10d | %-14s | %-15s | %-10d | %-17s | %-12.2f |%n",
                        resultSet.getInt("booking_id"),
                        resultSet.getString("guest_name"),
                        resultSet.getString("contact_no"),
                        resultSet.getInt("room_no"),
                        resultSet.getString("booking_date"),
                        resultSet.getDouble("booking_price"));
                System.out.println("+------------+----------------+-----------------+------------+---------------------+--------------+");
            }

            if (!found) {
                System.out.println("\nNo bookings found in the given price range.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generateBookingInvoice() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter booking ID to generate invoice: ");
            int bookingId = scanner.nextInt();

            String query = "SELECT * FROM bookings WHERE booking_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\n*************************************");
                System.out.println("           Booking Invoice");
                System.out.println("*************************************");
                System.out.println("Booking ID: " + resultSet.getInt("booking_id"));
                System.out.println("Guest Name: " + resultSet.getString("guest_name"));
                System.out.println("Contact Number: " + resultSet.getString("contact_no"));
                System.out.println("Room Number: " + resultSet.getInt("room_no"));
                System.out.println("Booking Date: " + resultSet.getString("booking_date"));
                System.out.println("Booking Price: " + resultSet.getDouble("booking_price"));
                System.out.println("-------------------------------------");
                System.out.println("     Total Amount: Rs. " + resultSet.getDouble("booking_price"));
                System.out.println("*************************************");
            } else {
                System.out.println("\nPlease give a valid booking ID.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void guestFeedbackAndRatings() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.print("\nEnter guest name: ");
            scanner.nextLine();
            String guestName = scanner.nextLine();
            System.out.print("Enter room number: ");
            int roomNo = scanner.nextInt();
            System.out.print("Enter feedback: ");
            scanner.nextLine();
            String feedback = scanner.nextLine();
            int rating;
            do {
                System.out.print("Enter your rating (1-5): ");
                rating = scanner.nextInt();
                if (rating < 1 || rating > 5) {
                    System.out.println("\nPlease enter a rating between 1 and 5.\n");
                }
            } while (rating < 1 || rating > 5);
            System.out.println();

            String query = "INSERT INTO feedback(guest_name, room_no, feedback, rating) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, guestName);
            preparedStatement.setInt(2, roomNo);
            preparedStatement.setString(3, feedback);
            preparedStatement.setInt(4, rating);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nFeedback and rating submitted successfully");
            } else {
                System.out.println("\nFailed to submit feedback and rating!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewGuestFeedbackAndRatings() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);

            String query = "SELECT * FROM feedback";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean found = false;

            System.out.println("+------------+----------------+------------+------------------------------------------+---------------------+--------+");
            System.out.println("| Feedback ID|   Guest Name   | Room Number| Feedback                                 | Feedback Date       | Rating |");
            System.out.println("+------------+----------------+------------+------------------------------------------+---------------------+--------+");

            while (resultSet.next()) {
                found = true;
                System.out.format("| %-11d| %-14s | %-10d | %-20s  | %-19s | %-6d |%n",
                        resultSet.getInt("feedback_id"),
                        resultSet.getString("guest_name"),
                        resultSet.getInt("room_no"),
                        resultSet.getString("feedback"),
                        resultSet.getString("feedback_date"),
                        resultSet.getInt("rating"));
                System.out.println("+------------+----------------+------------+------------------------------------------+---------------------+--------+");
            }

            if (!found) {
                System.out.println("\nNo feedback found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
