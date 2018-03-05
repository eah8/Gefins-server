package is.hi.teymi9.gefins.server.model;


import javax.persistence.*;

/**
 *
 * @author Kristín María
 * @date March 2018
 *
 * Comment klasi til að senda upplýsingar um comment á þjón
 */

@Entity
@Table(name="comment")
public class Comment {

    @Id
    private int commentId;
    private String username;
    private String comment;
    private int adId;

    public Comment(int commentId, String username, String comment, int adId) {
        this.commentId = commentId;
        this.username = username;
        this.comment = comment;
        this.adId = adId;
    }

    /**
     * Tómur smiður
     */
    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }


}
