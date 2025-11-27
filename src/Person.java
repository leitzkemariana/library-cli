import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String email;
    private String password;

    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        //System.out.println("Password: " + password);
        System.out.println("");
    }
}