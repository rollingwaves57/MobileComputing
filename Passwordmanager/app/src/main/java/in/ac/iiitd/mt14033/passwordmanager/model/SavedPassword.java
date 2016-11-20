package in.ac.iiitd.mt14033.passwordmanager.model;

/**
 * Created by jarvisx on 10/2/2016.
 */

public class SavedPassword {

    //private variables
    Integer id;
    String master_email;
    String name;
    String username;
    String url;
    String password;

    // Empty constructor
    public SavedPassword(){

    }

    public SavedPassword(Integer id, String master_email, String name, String username, String url, String password) {
        this.id = id;
        this.master_email = master_email;
        this.name = name;
        this.username = username;
        this.url = url;
        this.password = password;
    }

    public SavedPassword(String master_email, String _name, String _username, String _url, String _password) {
        this.master_email = master_email;
        this.name = _name;
        this.username = _username;
        this.url = _url;
        this.password = _password;
    }

    public String getMaster_email() {
        return master_email;
    }

    public void setMaster_email(String master_email) {
        this.master_email = master_email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
