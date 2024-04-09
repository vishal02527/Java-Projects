import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Book class
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDateTime borrowedDateTime;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.borrowedDateTime = null;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDateTime getBorrowedDateTime() {
        return borrowedDateTime;
    }

    public void setBorrowedDateTime(LocalDateTime borrowedDateTime) {
        this.borrowedDateTime = borrowedDateTime;
    }

    @Override
    public String toString() {
        return "\nbookId = " + bookId +
                "\ntitle = " + title +
                "\nauthor = " + author +
                "\nborrowedDateTime = "
                + (borrowedDateTime != null ? borrowedDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy , HH:mm"))
                        : "N/A");
    }
}

// Library class
class Library {
    private List<Book> books;
    private Map<String, List<Book>> borrowedBooks;
    private DateTimeFormatter dateTimeFormatter;

    // Method to perform login
    static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        if ("vishal".equals(username) && "admin@12".equals(password)) {
            System.out.println("\nLogged in successfully!");
        } else {
            System.out.println("\nInvalid username or password. Please try again.\n");
            System.exit(0);
        }
    }

    public Library() {
        this.books = new ArrayList<>();
        this.borrowedBooks = new HashMap<>();
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy , HH:mm");
    }

    public void addBook(Book book) {
        boolean isDuplicateId = books.stream().anyMatch(b -> b.getBookId() == book.getBookId());

        if (isDuplicateId) {
            System.out.println("\nThis ID already exists. Please give a valid book ID.");
        } else {
            books.add(book);
            System.out.println("\nBook added successfully!");
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void borrowBook(String borrowerName, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            book.setBorrowedDateTime(LocalDateTime.now());
            String formattedDateTime = LocalDateTime.now().format(dateTimeFormatter);
            borrowedBooks.computeIfAbsent(borrowerName, k -> new ArrayList<>()).add(book);
            System.out.println("\n" + borrowerName + " borrowed " + book.getTitle() + " on " + formattedDateTime);
        } else {
            System.out.println("\n" + "Sorry, " + book.getTitle() + " is not available.");
        }
    }

    public void returnBook(String borrowerName, Book book) {
        if (borrowedBooks.containsKey(borrowerName) && borrowedBooks.get(borrowerName).contains(book)) {
            book.setAvailable(true);
            book.setBorrowedDateTime(null);
            String formattedDateTime = LocalDateTime.now().format(dateTimeFormatter);
            borrowedBooks.get(borrowerName).remove(book);
            System.out.println("\n" + borrowerName + " returned " + book.getTitle() + " on " + formattedDateTime);
        } else {
            System.out.println("\n" + "Sorry, " + borrowerName + " did not borrow " + book.getTitle());
        }
    }

    public void displayBooks() {
        boolean hasAvailableBooks = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("\nbookId = " + book.getBookId() +
                        "\ntitle = " + book.getTitle() +
                        "\nauthor = " + book.getAuthor());
                hasAvailableBooks = true;
            }
        }
        if (!hasAvailableBooks) {
            System.out.println("\nNo available books found.");
        }
    }

    public void displayBorrowedBooks() {
        boolean hasBorrowedBooks = false;
        for (Map.Entry<String, List<Book>> entry : borrowedBooks.entrySet()) {
            String borrowerName = entry.getKey();
            List<Book> books = entry.getValue();
            for (Book book : books) {
                String formattedDateTime = book.getBorrowedDateTime().format(dateTimeFormatter);
                System.out.println("\nBorrower Name: " + borrowerName);
                System.out.println("Book Name: " + book.getTitle());
                System.out.println("Borrowed Date and Time: " + formattedDateTime);
                hasBorrowedBooks = true;
            }
        }
        if (!hasBorrowedBooks) {
            System.out.println("\nNo borrowed books found.");
        }
    }

    public void removeBook(int bookId) {
        boolean removed = books.removeIf(book -> book.getBookId() == bookId);
        if (removed) {
            System.out.println("\nBook with ID " + bookId + " removed successfully.");
            borrowedBooks.values().forEach(list -> list.removeIf(book -> book.getBookId() == bookId));
        } else {
            System.out.println("\nNo book found with ID: " + bookId);
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("\n**************************");
        System.out.println("Library Management System");
        System.out.println("**************************\n");

        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        System.out.println("Login:");
        Library.login();

        while (true) {
            System.out.println("\nMain Menu:\n");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Display Borrowed Books");
            System.out.println("6. Remove Book by ID");
            System.out.println("7. Exit\n");

            System.out.print("Enter your choice[1-7]: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("\n1. Add Book");
                    System.out.print("\nEnter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;
                case 2:
                    System.out.println("\n2. Borrow Book");
                    System.out.print("\nEnter Borrower Name: ");
                    String borrowerName = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    int borrowBookId = scanner.nextInt();
                    Book borrowBook = library.getBooks().stream().filter(b -> b.getBookId() == borrowBookId).findFirst()
                            .orElse(null);
                    if (borrowBook != null) {
                        library.borrowBook(borrowerName, borrowBook);
                    } else {
                        System.out.println("\nInvalid Book ID!");
                    }
                    break;
                case 3:
                    System.out.println("\n3. Return Book");
                    System.out.print("\nEnter Borrower Name: ");
                    String returnborrowerName = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    int returnBookId = scanner.nextInt();
                    Book returnBook = library.getBooks().stream().filter(b -> b.getBookId() == returnBookId).findFirst()
                            .orElse(null);
                    if (returnBook != null) {
                        library.returnBook(returnborrowerName, returnBook);
                    } else {
                        System.out.println("\nInvalid Book ID!");
                    }
                    break;
                case 4:
                    System.out.println("\n4. Display Available Books");
                    library.displayBooks();
                    break;
                case 5:
                    System.out.println("\n5. Display Borrowed Books");
                    library.displayBorrowedBooks();
                    break;
                case 6:
                    System.out.println("\n7. Remove Book by ID");
                    System.out.print("Enter Book ID: ");
                    int removeBookId = scanner.nextInt();
                    library.removeBook(removeBookId);
                    break;
                case 7:
                    System.out.println("\nExiting Library Management System. Goodbye!\n");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice! Please enter a valid option.\n");
            }
        }
    }
}
