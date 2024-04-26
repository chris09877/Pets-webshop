package chris.ilg.dierenwinkel.model;

import chris.ilg.dierenwinkel.repository.OrderRepo;
import chris.ilg.dierenwinkel.service.OrderServiceImpl;
import chris.ilg.dierenwinkel.service.UserDto;
import chris.ilg.dierenwinkel.service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String firstname, lastname, address;
    @Column(nullable = false, length = 255)
    private String postcode, number, password;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false, length = 40, unique = true)
    private String phone, mail;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;


    public User(UserDto userDto, PasswordEncoder passwordEncoder) {
        this.address = userDto.getAddress();
        this.birthdate = userDto.getBirthdate();
        this.mail = userDto.getMail();
        this.firstname = userDto.getFirstname();
        this.lastname = userDto.getLastname();
        this.number = userDto.getNumber();
        this.phone = userDto.getPhone();
        this.postcode = userDto.getPostcode();
        this.password = passwordEncoder.encode(userDto.getPassword()).toString();
//        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
//        this.orders =  orderServiceImpl.getAllOrdersById(userDto.getOrderIds());


    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}