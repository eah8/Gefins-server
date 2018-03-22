package is.hi.teymi9.gefins.server.repository;

import is.hi.teymi9.gefins.server.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date March 2018
 *
 * Repository fyrir ad.
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
     * @return
     */
    @Override
    Message save(Message m);

   @Override
   Message findOne(Long id);

   // finnur öll message með ákveðið id
   List<Message> findById(UUID id);

   // finnur öll message eftir ákveðinn höfund
    List<Message> findBySenderOrderByDateDesc(String sender);

    // finnur öll message til ákveðins notands
    List<Message> findByRecipientOrderByDateDesc(String recipient);
}
