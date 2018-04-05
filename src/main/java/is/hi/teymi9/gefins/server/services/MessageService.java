package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Message;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date March 2018
 * @version 1.0
 *
 * Þjónusta sem sér um samskipti við Ad repository
 *
 */

public interface MessageService {

    /**
     * Bætir við message í messageRep
     *
     * @param m message
     * @throws DataException
     */
    public void addMessage(Message m) throws DataException;

    /**
     * Skilar öllum messages í messageRep
     *
     * @return listi af messages
     * @throws DataException
     */
    public List<Message> allMessages() throws DataException;

    /**
     * Vistar message
     * @param m skilaboð sem vista skal
     * @return skilaboðin sem voru vistuð
     * @throws DataException
     */
    public Message save(Message m) throws DataException;

    /**
     * Leitar að message með ákveðin id og skilar því ef finnst
     * @param id token sem leita skal eftir
     * @return message með token ef til, annars null
     */
    public Message findMessageById(UUID id);

    /**
     * Finnur öll message eftir ákveðinn notanda
     * @param sender notendannafn höfundar
     * @return Listi af messages
     */
    public List<Message> findMessageBySender(String sender);

    /**
     * Finnur öll message til ákveðins notanda
     * @param recipient notendannafn viðtakanda
     * @return Listi af messages
     */
    public List<Message> findMessageByRecipient(String recipient);
}
