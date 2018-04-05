package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.Comment;
import is.hi.teymi9.gefins.server.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Ólöf Fríða Magnúsdóttir
 * @date March 2018
 *
 * Þjónusta sem sér um samskipti við Comment repository
 *
 */

@Service
public class CommentServiceImp implements CommentService {

    // Tenging yfir í safn af commentum
    @Autowired
    CommentRepository commentRep;


    /**
     * Bætir við athugasemd í CommentRep
     *
     * @param comment auglýsing
     * @throws DataException
     */
    @Override
    public void addComment(Comment comment) throws DataException {
        try {
            commentRep.save(comment);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Skilar öllum athugasemdum í commentRep
     *
     * @return listi af athugasemdum
     * @throws DataException
     */
    @Override
    public List<Comment> allComments() throws DataException {
        try {
            return commentRep.findAll();    // Notum findAll í staðinn fyrir getAll
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Vistar athugasemd
     * @param comment athugasemd sem skal vista
     * @return athugasemdin sem vistuð var
     * @throws DataException
     */
    @Override
    public Comment save(Comment comment) throws DataException {

        try {
            return commentRep.save(comment);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Leitar að ad með ákveðin id og skilar því ef finnst
     * @param id token sem leita skal eftir
     * @return ad með token ef til, annars null
     */
    @Override
    public Comment findCommentById(UUID id) {
        List<Comment> comments = commentRep.findById(id);
        if (comments != null)
        {
            return comments.get(0);
        }
        return null;
    }

    /**
     * Uppfærir athugasemd í gagnagrunni
     * @param comment Uppfærðar upplýsingar auglýsingar
     * @return skilaboð um hvort uppfærsla tókst
     * @throws DataException
     */
    @Override
    public String updateComment(Comment comment) throws DataException {
        Comment oldComment = findCommentById(comment.getId());
        if (oldComment == null)  {
            return "Update ad failed";
        }
        oldComment = comment;
        save(oldComment);
        return "Update ad successful!";
    }

    /**
     * Finnur athugasem sem tilheyrir viðeigandi auglýsingu
     * @param ad auglýsing
     * @return comments, listi af athugasemdum
     */
    @Override
    public List<Comment> findCommentByAd(Ad ad) {
        List<Comment> comments = commentRep.findByAd(ad);
        return comments;
    };

    /**
     * Eyðir athugasemd
     * @param comment athugasemd sem skal eyða
     */
    @Override
    public void deleteComment(Comment comment) {
        commentRep.delete(comment);
    }

}
