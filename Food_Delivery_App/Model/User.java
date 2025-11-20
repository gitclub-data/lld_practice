package Food_Delivery_App.Model;

public class User {
    
    private String id;
    private String name;
    private String address;
    private String cartid;

    private static Integer counter = 0;

    public User(String name, String address){
        this.name = name;
        this.address = address;

        counter += 1;
        this.id = String.valueOf(counter);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid){
        this.cartid = cartid;
    }

}
