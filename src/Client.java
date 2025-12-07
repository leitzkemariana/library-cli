public class Client extends Person {

    public Client(String name, String email, String password) {
        super(name, email, password);
    }

    public void showClient(){
        super.showDetails();
    }
}

