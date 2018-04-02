package is.hi.teymi9.gefins.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.UUID;

/**
 *
 * @author Kristín María og Ólöf
 * @date March 2018
 *
 * Comment klasi til að senda upplýsingar um comment á þjón
 */


@Entity
@Table(name="comment")
public class Comment {
    // unique auðkenni fyrir comment
    @Id
    private UUID id = UUID.randomUUID();
    // höfundur, þ.e. sá sem skrifaði commentið
    private String username;
    // commentið sjálft
    private String comment;
    // auglýsing sem commentið tilheyrir
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonBackReference
    private Ad ad;

    /**
     * smiður með viðföngum
     * @param username notendanafn höfundar
     * @param comment commentið sjálft
     * @param ad auglýsing sem comment tilheyrir
     */
    public Comment( String username, String comment, Ad ad) {

        this.username = username;
        this.comment = comment;
        this.ad = ad;
    }

    /**
     * Tómur smiður
     */
    public Comment() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
