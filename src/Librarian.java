import java.util.List;

public class Librarian extends Person {
    private Library library;
    private List<Book> books;
    private List<Client> clients;


    public Librarian(String name, String email, String password, Library library) {
        super(name, email, password);
        this.library = library;
        this.books =  library.getBooks();
        this.clients =  library.getClients();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added");
    }

    public void removeBook(String bookTitle) {
        Boolean found = false;
        Book bookFound = null;

        if (!books.isEmpty()) {
            for (Book book : books) {
                if (book.title.toLowerCase().contains(bookTitle.toLowerCase())) {
                    found = true;
                    bookFound = book;
                }
            }
        }

        if (found) {
            books.remove(bookFound);
            System.out.println("Book removed");
        } else  {
            System.out.println("Book not found");
        }
    }

    public void showBooks() {
        for (Book book : books) {
            book.showBook();
        }
    }

    public void searchBook(String bookTitle) {
        Boolean found = false;
        Book bookFound = null;

        if (!books.isEmpty()) {
            for (Book book : books) {
                if (book.title.toLowerCase().contains(bookTitle.toLowerCase())) {
                    found = true;
                    bookFound = book;
                }
            }
        }

        if (found) {
            bookFound.showBook();
        } else  {
            System.out.println("Book not found");
        }
    }

    public void loanBook(String bookTitle, String clientName) {
        Boolean findBook = false;
        Book bookFound = null;

        Boolean findClient = false;
        Client clientFound = null;

        if (!clients.isEmpty()) {
            for (Client client : clients){
                if (client.getName().toLowerCase().contains(clientName.toLowerCase())) {
                    findClient = true;
                    clientFound = client;
                }
            }
        }

        if (!books.isEmpty()) {
            for (Book book : books) {
                if (book.title.toLowerCase().contains(bookTitle.toLowerCase())) {
                    findBook = true;
                    bookFound = book;
                }
            }
        }

        if (findBook && findClient) {
            bookFound.setClient(clientFound);
            bookFound.setAvailable(false);

            System.out.println("Book loaned");
        } else  {
            System.out.println("Book or client not found");
        }
    }

    public void returnBook(String bookTitle) {
        Boolean found = false;
        Book bookFound = null;

        if (!books.isEmpty()) {
            for (Book book : books) {
                if (book.title.toLowerCase().contains(bookTitle.toLowerCase())) {
                    found = true;
                    bookFound = book;
                }
            }
        }

        if (found && (!bookFound.getAvailable())) {
            bookFound.setAvailable(true);
            bookFound.setClient(null);
            System.out.println("Book has been returned");


        } else  {
            System.out.println("Book not found or is not lent");
        }
    }

    public void addClient(Client client) {
        Boolean clientExists = false;

        for(Client c : clients) {
            if (c.getName().toLowerCase().contains(client.getName().toLowerCase())) {
                clientExists = true;
                break;
            }
        }

        if (clientExists) {
            System.out.println("Client already exists");
        } else   {
            clients.add(client);
            System.out.println("Client added");
        }
    }

    public void removeClient(String clientName) {
        Boolean found = false;
        Client clientFound = null;
        Boolean borrowed = false;

        if (!clients.isEmpty()) {
            for (Client client : clients) {
                if (client.getName().toLowerCase().contains(clientName.toLowerCase())) {
                    found = true;
                    clientFound = client;
                }
            }
        }

        if (found) {
            for (Book book : books) {
                if ((book.getClient() != null) && (book.getClient().equals(clientFound))) {
                    borrowed = true;
                }
            }
        } else  {
            System.out.println("Client not found");
        }

        if (borrowed) {
            System.out.println("Client still has borrowed books");
        } else {
            clients.remove(clientFound);
            System.out.println("Client removed");
        }
    }

    public void showClients() {
        for (Client client : clients) {
            client.showClient();
        }
    }

    public void searchClient(String clientName) {
        Boolean found = false;
        Client clientFound = null;

        if (!clients.isEmpty()) {
            for (Client client : clients) {
                if (client.getName().toLowerCase().contains(clientName.toLowerCase())) {
                    found = true;
                    clientFound = client;
                }
            }
        }

        if (found) {
            clientFound.showClient();
        } else  {
            System.out.println("Client not found");
        }
    }
}
