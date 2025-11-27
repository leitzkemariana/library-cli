import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List<Book> borrowedBooks;

    public Client(String name, String email, String password) {
        super(name, email, password);
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return borrowedBooks;
    }

    public void listBooks() {
        for (Book book : borrowedBooks) {
                if (borrowedBooks.isEmpty()){
                System.out.println("There is no books borrowed");
            } else {
                book.showBook();
            }
        }
    }

    public void showClient(){
        super.showDetails();
    }
}
