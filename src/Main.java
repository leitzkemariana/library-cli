import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Librarian librarian = new Librarian("Librarian", "Libri@gmail", "123", library);
        library.addLibrarian(librarian);

        while(true) {
            Scanner input = new Scanner(System.in);
            Integer option = 0;
            System.out.println("---- Library Menu ----");
            System.out.println("1. Access librarian menu");
            System.out.println("2. Access client menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try{
                option = input.nextInt();
                if (option < 1 || option > 3) {
                    System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
            }

            if (option == 1){
                System.out.println("Enter name: ");
                String libName = input.next();
                System.out.println("Enter password: ");
                String password = input.next();
                Boolean libFound = false;

                for (Librarian l : library.getLibrarians()) {
                    if (l.getName().equals(libName) && l.getPassword().equals(password)) {
                        libFound = true;
                        while (true) {
                            Integer opLibrarian = 0;
                            System.out.println("----Librarian Menu----");
                            System.out.println("1. Add book");
                            System.out.println("2. Delete book");
                            System.out.println("3. Show books");
                            System.out.println("4. Search book");
                            System.out.println("5. Loan book");
                            System.out.println("6. Return book");
                            System.out.println("7. Create client");
                            System.out.println("8. Delete client");
                            System.out.println("9. Show clients");
                            System.out.println("10. Search client");
                            System.out.println("11. Exit");
                            System.out.print("Enter your choice: ");

                            try {
                                opLibrarian = input.nextInt();
                                if (opLibrarian < 1 || opLibrarian > 11) {
                                    System.out.println("Invalid choice");
                                    continue;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter an integer.");
                                input.next();
                                continue;
                            }

                            if (opLibrarian == 1) {

                                System.out.print("Enter book title: ");
                                String bookTitle = input.next();
                                System.out.print("Enter book author: ");
                                String bookAuthor = input.next();
                                System.out.print("Enter book publisher: ");
                                String bookPublisher = input.next();
                                Integer bookYear = null;

                                while (bookYear == null) {
                                    System.out.print("Enter book year: ");
                                    try {
                                        bookYear = input.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid year. Please enter an integer.");
                                        input.next();
                                    }
                                }

                                Book book = new Book(bookTitle, bookAuthor, bookPublisher, bookYear);
                                librarian.addBook(book);


                            } else if (opLibrarian == 2) {
                                System.out.print("Enter book title: ");
                                String bookTitle = input.next();
                                librarian.removeBook(bookTitle);

                            } else if (opLibrarian == 3) {
                                librarian.showBooks();

                            } else if (opLibrarian == 4) {
                                System.out.print("Enter book title: ");
                                String bookTitle = input.next();
                                librarian.searchBook(bookTitle);

                            } else if (opLibrarian == 5) {
                                System.out.print("Enter book title: ");
                                String bookTitle = input.next();
                                System.out.print("Enter client name: ");
                                String ClientName = input.next();
                                librarian.loanBook(bookTitle, ClientName);

                            } else if (opLibrarian == 6) {
                                System.out.print("Enter book title: ");
                                String bookTitle = input.next();
                                librarian.returnBook(bookTitle);

                            } else if (opLibrarian == 7) {
                                System.out.print("Enter client name: ");
                                String clientName = input.next();
                                System.out.print("Enter client email: ");
                                String clientEmail = input.next();
                                System.out.print("Enter client password: ");
                                String clientPassword = input.next();
                                Client client = new Client(clientName, clientEmail, clientPassword);
                                librarian.addClient(client);

                            } else if (opLibrarian == 8) {
                                System.out.println("Enter client name: ");
                                String clientName = input.next();
                                librarian.removeClient(clientName);

                            } else if (opLibrarian == 9) {
                                librarian.showClients();

                            } else if (opLibrarian == 10) {
                                System.out.println("Enter client name: ");
                                String clientName = input.next();
                                librarian.searchClient(clientName);

                            } else {
                                break;
                            }
                        }
                    }
                }

                if (libFound == false) {
                    System.out.println("Librarian not found");
                }
            }

            if (option == 2){
                System.out.println("Name: ");
                String nome = input.next();
                System.out.println("Password: ");
                String password = input.next();
                Boolean clientFound = false;

                for (Client client : library.getClients()){
                    if (client.getName().equals(nome) && client.getPassword().equals(password)){
                        clientFound = true;
                        while (true){
                            Integer opClient = 0;
                            System.out.println("----Client Menu----");
                            System.out.println("1. Show borrowed books");
                            System.out.println("2. Search book");
                            System.out.println("3. Exit");
                            System.out.print("Enter your choice: ");

                            try {
                                opClient = input.nextInt();
                                if (opClient < 1 || opClient > 3) {
                                    System.out.println("Invalid choice");
                                    continue;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter an integer.");
                                input.next();
                            }

                            if (opClient == 1){
                                for (Book book : library.getBooks()){
                                    if ((book.getClient() != null) && (book.getClient().getName().equals(nome))){
                                        book.showBook();
                                    }
                                }

                            } else if (opClient == 2) {
                                System.out.print("Enter book title: ");
                                String bookName = input.next();
                                Boolean findBook = false;

                                for (Book book : library.getBooks()){
                                    if (book.title.equals(bookName)){
                                        book.showBook();
                                        findBook = true;
                                        break;
                                    }
                                }

                                if (findBook == false){
                                    System.out.println("Book not found");
                                }


                            } else if (opClient == 3) {
                                break;
                            }
                        }
                    }
                }

                if (!clientFound){
                    System.out.println("Client not found");
                }
            }

            if (option == 3) {
                break;
            }
        }

    }
}
