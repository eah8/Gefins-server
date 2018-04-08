package is.hi.teymi9.gefins.server.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * @author Sandra
 * @date 14.02.2018
 * @version 1.0
 */

@Entity
@Table(name="useraccount")
public class User {

    // unique auðkenni fyrir notanda
    @Id
    private UUID id = UUID.randomUUID();
    // Notendanafn notanda
    private String username;
    // Fullt nafn notanda
    private String fullName;
    // Netfang notanda
    private String email;
    // Símanúmer notanda
    private String phonenr;
    // Lykilorð notanda
    private String password;
    // Póstfang notanda
    private int zip;
    // Heimilisfang notanda
    private String address;
    // Breyta sem segir til um hvort notandi sé admin eða ekki
    private boolean hasadminauthority;


    /**
     * Smiður fyrir notanda
     * @param username notendanafn
     * @param fullName fullt nafn
     * @param email netfang
     * @param phonenr símanúmer
     * @param password lykilorð
     * @param zip póstfang
     * @param address heimilisfang
     * @param hasadminauthority er notandi admin
     */
    public User(String username, String fullName, String email, String phonenr, String password, int zip, String address, boolean hasadminauthority) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phonenr = phonenr;
        this.password = password;
        this.zip = zip;
        this.address = address;
        this.hasadminauthority = hasadminauthority;
    }

    /**
     * Tómi smiðurinn
     */
    public User() {
    }

    /**
     * Skilar id á notanda
     * @return id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Nær í notandanafnið á notanda
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Stillir notandanafnið á notanda
     * @param username notendanafn
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Nær í fullt nafn á notanda
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Stillir fulla nafnið á notanda
     * @param fullName Fullt nafn á notanda
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Nær í netfangið hjá notanad
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Stillir netfangið hjá notanda
     * @param email netfang
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * nær í símanúmer hjá notanda
     * @return phonenr
     */
    public String getPhonenr() {
        return phonenr;
    }

    /**
     * Stillir símanúmer hjá notanada
     * @param phonenr símanúmer
     */
    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    /**
     * Nær í lykilorð hjá notanda
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Stillir lykilorð hjá notanda
     * @param password lykilorð
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Nær í póstfang hjá notanda
     * @return zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * Stillir póstfang hjá notanda
     * @param zip póstfang
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Nær í heimilisfang notanda
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * stillir heimilisfang notanda
     * @param address heimilisfang
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Skilar boolean um hvort notandi sé admin
     * @return hasadminauthority
     */
    public boolean isHasadminauthority() {
        return hasadminauthority;
    }

    /**
     * stillir boolean breytu sem ræður hvort notandi sé admin eða ekki
     * @param hasadminauthority boolean breyta
     */
    public void setHasadminauthority(boolean hasadminauthority) {
        this.hasadminauthority = hasadminauthority;
    }



}

