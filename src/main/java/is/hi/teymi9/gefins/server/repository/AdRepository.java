package is.hi.teymi9.gefins.server.repository;

import is.hi.teymi9.gefins.server.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 *
 * @author Kristín María og Einar
 * @date March 2018
 *
 * Repository fyrir ad.
 */


public interface AdRepository extends JpaRepository<Ad, Long> {

    /**
     * Nær í allar ads
import java.util.List;
import java.util.UUID;


public interface AdRepository extends JpaRepository<Ad, Long>{
    /**
     * Nær í öll ads
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

   // finnur öll ads með ákveðið id
   List<Ad> findById(UUID id);

   // finnur öll ads eftir ákveðinn höfund
    List<Ad> findByAdUsername(String username);
}
