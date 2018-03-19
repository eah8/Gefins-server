package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Message;
import is.hi.teymi9.gefins.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date March 2018
 *
 * Þjónusta sem sér um samskipti við Message repository
 *
 */

@Service
public class MessageServiceImp implements MessageService {

    // Tenging yfir í safn af messages
    @Autowired
    MessageRepository messageRep;


    /**
     * Bætir við message í messageRep
     *
     * @param m message
     * @throws DataException
     */
    @Override
    public void addMessage(Message m) throws DataException {
        try {
            messageRep.save(m);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Skilar öllum messages í messageRep
     *
     * @return listi af messages
     * @throws DataException
     */
    @Override
    public List<Message> allMessages() throws DataException {
        try {
            return messageRep.findAll();    // Notum findAll í staðinn fyrir getAll
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Vistar message
     * @param m skilaboð sem vista skal
     * @return skilaboðin sem voru vistuð
     * @throws DataException
     */
    @Override
    public Message save(Message m) throws DataException {

        try {
            return messageRep.save(m);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Leitar að message með ákveðin id og skilar því ef finnst
     * @param id token sem leita skal eftir
     * @return message með token ef til, annars null
     */
    @Override
    public Message findMessageById(UUID id) {
        List<Message> messages = messageRep.findById(id);
        if (messages != null)
        {
            return messages.get(0);
        }
        return null;
    }

    /**
     * Finnur öll message eftir ákveðinn notanda
     * @param sender notendannafn höfundar
     * @return Listi af messages
     */
    @Override
    public List<Message> findMessageBySender(String sender) {
        return messageRep.findBySender(sender);
    }

    /**
     * Finnur öll message til ákveðins notanda
     * @param recipient notendannafn viðtakanda
     * @return Listi af messages
     */
    @Override
    public List<Message> findMessageByRecipient(String recipient) {
        return messageRep.findByRecipient(recipient);
    }}
