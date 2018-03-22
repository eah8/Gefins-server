package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.Comment;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Ólöf Fríða Magnúsdóttir
 * @date March 2018
 * 
 * Þjónusta sem sér um samskipti við comment repository
 *
 */

/**
 *
 * @author Kristín María
 * @date March 2018
 *
 * Þjónusta sem sér um samskipti við repositories
 *
 */
public interface CommentService {

    /**
     * Bætir við ad í adRep
     *
     * @param comment Athugasemd
     * @throws DataException
     */
    public void addComment(Comment comment) throws DataException;

    /**
     * Skilar öllum athugasemdum í commentRep
     *
     * @return listi af athugasemdum
     * @throws DataException
     */
    public List<Comment> allComments() throws DataException;

    /**
     * Vistar athugasemd
     * @param comment athugasemd sem skal vista
     * @return athugasemdin sem var vistuð
     * @throws DataException
     */
    public Comment save(Comment comment) throws DataException;

    /**
     * Leitar að ad með ákveðin id og skilar því ef finnst
     * @param id token sem leita skal eftir
     * @return ad með token ef til, annars null
     */
    public Comment findCommentById(UUID id);

    /**
     * Uppfærir ad í gagnagrunni
     * @param comment Uppfærðar athugasemdina
     * @return skilaboð um hvort uppfærsla tókst
     * @throws DataException
     */
    public String updateComment(Comment comment) throws DataException;

    /**
     * Finnur allar athugasemdir fyrir viðeigandi auglýsingu
     * @param ad auglýsing
     * @return listi af athugasemdum
     */
    public List<Comment> findCommentByAd(Ad ad);

}
