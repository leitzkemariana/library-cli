public class Book {
    public String title;
    public String author;
    public String publisher;
    public Integer year;
    private Boolean available;
    private Client client;

    public Book(String title, String author, String publisher, Integer year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.available = true;
        this.client = null;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public Boolean getAvailable() {
        return available;
    }

    public void showBook() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Year: " + year);
        System.out.println(available ? "Available" : "Unavailable");
        if (!available){
            System.out.println("Client: " + client.getName());
        }
        System.out.println("");
    }
}