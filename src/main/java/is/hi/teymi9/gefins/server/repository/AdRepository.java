package is.hi.teymi9.gefins.server.repository;

import is.hi.teymi9.gefins.server.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Kristín María
 * @date March 2018
 *
 * Repository fyrir ad.
 */

public interface AdRepository extends JpaRepository<Ad, Long> {

    /**
     * Nær í allar ads
     * @return listi af ads
     */
    @Override
    List<Ad> findAll();

    /**
     * Bætir við ad
     * @param ad
     * @return
     */
    @Override
    Ad save(Ad ad);

    @Override
    Ad findOne(Long id);

}