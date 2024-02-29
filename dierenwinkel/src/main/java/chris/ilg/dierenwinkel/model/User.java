package chris.ilg.dierenwinkel.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String firstname,lastname, address;
    @Column(nullable = false, length = 255)
    private String postcode, number;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false, length = 40, unique = true)
    private String phone, mail;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String number) {
        this.phone = phone;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
