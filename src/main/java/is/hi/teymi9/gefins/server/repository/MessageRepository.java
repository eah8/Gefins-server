package is.hi.teymi9.gefins.server.repository;

import is.hi.teymi9.gefins.server.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date March 2018
 * @version 1.0
 *
 * Repository fyrir skilaboð
 */

public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * Nær í öll message
     * @return listi af messages
     */
    @Override
    List<Message> findAll();

    /**
     * Bætir við message
     * @param m
     * @return message
     */
    @Override
    Message save(Message m);

    /**
     * finnur message með viðeigandi id
     * @param id
     * @return message
     */
   @Override
   Message findOne(Long id);

   // finnur öll message með ákveðið id
   List<Message> findById(UUID id);

   // finnur öll message eftir ákveðinn höfund
    List<Message> findBySenderOrderByDateDesc(String sender);

    // finnur öll message til ákveðins notands
    List<Message> findByRecipientOrderByDateDesc(String recipient);
}
