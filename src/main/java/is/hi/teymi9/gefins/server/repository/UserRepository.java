package is.hi.teymi9.gefins.server.repository;

import is.hi.teymi9.gefins.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date February 2018
 * 
 * Repository fyrir user.
 */

public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * Nær í alla users
     * @return listi af users
     */
    @Override
    List<User> findAll();

    /**
     * Bætir við user
     * @param user
     * @return
     */
    @Override
    User save(User user);

   @Override
   User findOne(Long id);
   
   // Notið sama nafn og dálkanafn í töflunni/tilviksbreytan (e. attribute) 
   List<User> findByUsernameAndPassword(String username, String password);
   
   // finnur alla users með ákveðið nafn username
   List<User> findByUsername(String name);

   // finnur alla users með ákveðin token
   List<User> findByToken(UUID token);
}
