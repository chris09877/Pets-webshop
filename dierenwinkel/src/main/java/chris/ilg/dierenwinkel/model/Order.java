package chris.ilg.dierenwinkel.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = true)
    @ElementCollection
    private List<String> delivery_information;
    @Column(nullable = false, length = 100)
    private Date date;

    @Column(nullable = true)
    private String userInfo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getDelivery_information() {
        return delivery_information;
    }

    public void setDelivery_information(List<String> delivery_information) {
        this.delivery_information = delivery_information;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }






}
