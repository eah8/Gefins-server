package is.hi.teymi9.gefins.server.repository;

import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


/**
 *
 * @author Ólöf Fríða Magnúsdóttir
 * @date March 2018
 *
 * Repository fyrir athugasemdir.
 */


public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Nær í allar athugasemdir
     * @return listi af athugasemdum
     */
    @Override
    List<Comment> findAll();

    /**
     * Bætir við athugasemd
     * @param comment
     * @return athugasemd
     */
    @Override
    Comment save(Comment comment);

    /**
     * Finnur athugasemd með viðeigandi id
     * @param id
     * @return athugasemd
     */
   @Override
   Comment findOne(Long id);

   // finnur allar athugasemdir með ákveðið id
   List<Comment> findById(UUID id);

    // Finnur allar athugasemdir eftir auglýsingu
    List<Comment> findByAd(Ad ad);
}
