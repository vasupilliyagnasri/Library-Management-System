import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static final List<Book> books = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleBooks();

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> {
                    System.out.println("Thank you for using the Library Management System.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void seedSampleBooks() {
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", true));
        books.add(new Book("1984", "George Orwell", true));
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }

        books.add(new Book(title, author, true));
        System.out.println("Book added successfully.");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nBooks in the library:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor()
                    + " - " + (book.isAvailable() ? "Available" : "Issued"));
        }
    }

    private static void issueBook() {
        viewBooks();
        if (books.isEmpty()) {
            return;
        }

        System.out.print("Enter the number of the book to issue: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index < 1 || index > books.size()) {
                System.out.println("Invalid book number.");
                return;
            }

            Book book = books.get(index - 1);
            if (!book.isAvailable()) {
                System.out.println("This book is already issued.");
                return;
            }

            book.setAvailable(false);
            System.out.println("Book issued successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private static void returnBook() {
        viewBooks();
        if (books.isEmpty()) {
            return;
        }

        System.out.print("Enter the number of the book to return: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index < 1 || index > books.size()) {
                System.out.println("Invalid book number.");
                return;
            }

            Book book = books.get(index - 1);
            if (book.isAvailable()) {
                System.out.println("This book is already available.");
                return;
            }

            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            scanner.nextLine();
        }
    }

    static class Book {
        private String title;
        private String author;
        private boolean available;

        public Book(String title, String author, boolean available) {
            this.title = title;
            this.author = author;
            this.available = available;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }
}
