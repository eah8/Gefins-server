package is.hi.teymi9.gefins.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.UUID;

/**
 *
 * @author Kristín María og Ólöf
 * @date March 2018
 * @version 1.0
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

    /**
     * Nær í id á athugasemd
     * @return id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Stillir id á athugasemd
     * @param id id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Nær í notendanafn á notanda sem skrifaði athugasemdina
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Stillir notendanafnið sem tengist athugasemdinni
     * @param username notendanafn
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Nær í textann sem athugasemdin inniheldur
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Stillir textann sem athugasemdin inniheldur
     * @param comment athugasemd
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Nær í auglýsinguna sem athugasemdin tengist
     * @return ad
     */
    public Ad getAd() {
        return ad;
    }

    /**
     * Stillir auglýsinguna sem athugasemdin tengist
     * @param ad auglýsing
     */
    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
