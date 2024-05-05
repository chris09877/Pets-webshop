package chris.ilg.dierenwinkel.service;

public class LoginRequest {

    private String mail, pwd;

    public LoginRequest() {
    }

    public LoginRequest(String mail, String pwd) {
        this.mail = mail;
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
