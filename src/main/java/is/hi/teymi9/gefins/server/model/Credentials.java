package is.hi.teymi9.gefins.server.model;

/**
 *
 * @author Einar
 * @date February 2018
 * @version 1.0
 *
 * Credentials klasi til að senda login upplýsingar á þjón
 */

public class Credentials {

    // Notendanafn á notanda
    private String username;
    // Lykilorð
    private String password;

    // Tómi smiðurinn
    public Credentials() {

    }

    /**
     * Smiður fyrir credentials
     * @param username
     * @param password
     */
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
