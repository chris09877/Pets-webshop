package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.User;

import java.sql.Date;
import java.util.ArrayList;

public class UserDto {
    private int id;
    private String firstname;
    private String lastname;
    private String address;
    private String postcode;
    private String number;
    private Date birthdate;
    private String phone;
    private String mail;
    private String password;
    private ArrayList<Integer> orderIds;
    public UserDto() {
    }

    public UserDto(User user) {
        this.id = id;
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.address = user.getAddress();
        this.postcode = user.getPostcode();
        this.number = user.getNumber();
        this.birthdate = user.getBirthdate();
        this.phone = user.getPhone();
        this.mail = user.getMail();
        this.password = user.getPassword();
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        ArrayList<Integer> orders = new ArrayList<>();
        user.getOrders().forEach(order ->  orders.add(order.getId()));
        this.orderIds = orders;
    }

    public UserDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(ArrayList<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
