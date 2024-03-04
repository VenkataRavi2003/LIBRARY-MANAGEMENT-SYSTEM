import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
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

class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }

    public Book borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                return book;
            }
        }
        return null;
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailable(true);
                return;
            }
        }
        System.out.println("Book with title \"" + title + "\" not found in the library.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Java Programming", "John Doe"));
        library.addBook(new Book("Introduction to Algorithms", "Thomas Cormen"));
        library.addBook(new Book("Clean Code", "Robert C. Martin"));

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Library Management System");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book to borrow: ");
                    scanner.nextLine(); // Consume the newline character
                    String borrowTitle = scanner.nextLine();
                    Book borrowedBook = library.borrowBook(borrowTitle);
                    if (borrowedBook != null) {
                        System.out.println("You have borrowed: " + borrowedBook.getTitle());
                    } else {
                        System.out.println("Book not available or does not exist!");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to return: ");
                    scanner.nextLine(); // Consume the newline character
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    System.out.println("You have returned: " + returnTitle);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
