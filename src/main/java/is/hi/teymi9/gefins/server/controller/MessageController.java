package is.hi.teymi9.gefins.server.controller;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.Comment;
import is.hi.teymi9.gefins.server.model.Message;
import is.hi.teymi9.gefins.server.model.User;
import is.hi.teymi9.gefins.server.services.AdService;
import is.hi.teymi9.gefins.server.services.MessageService;
import is.hi.teymi9.gefins.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Einar
 * @date March 2018
 * @version 1.0
 *
 * Sér um þjónustu í tengslum við einkaskilaboð
 */

@Controller
@RequestMapping("")
public class MessageController {

    // Tenging yfir í þjónustu klasa fyrir forritið
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    // Logger til að geta skrifað út villuboð
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    /**
     * Sækir öll skilaboð sem ákveðinn notandi hefur fengið
     * @param u notandi hvers skilaboð skal sækja
     * @return Listi af skilaboðum
     * @throws DataException
     */
    @RequestMapping(value = "getUserMessages", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json")
    public @ResponseBody
    List<Message> getUserMessages(@RequestBody User u) throws DataException {
        LOGGER.info("JSON get user messages for user: " + u);
        return messageService.findMessageByRecipient(u.getUsername());
    }

    /**
     * Sækir öll skilaboð sem ákveðinn notandi hefur sent
     * @param u notandi þess sem sendi skilaboðin
     * @return Listi af skilaboðum
     * @throws DataException
     */
    @RequestMapping(value = "getMySentMessages", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json")
    public @ResponseBody
    List<Message> getMySentMessages(@RequestBody User u) throws DataException {
        LOGGER.info("JSON get sent messages by user: " + u);
        return messageService.findMessageBySender(u.getUsername());

    }

    /**
     *
     * @param m skilaboð sem vista skal
     * @return Skilaboð um að tekist hafi að vista skilaboð
     * @throws DataException
     */
    @RequestMapping(value = "sendMessage", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody Boolean sendMessage(@RequestBody Message m) throws DataException {
        LOGGER.info("JSON send message: " + m.toString());
        User u = userService.findUserByUsername(m.getRecipient());
        if(u == null) {
            return new Boolean(false);
        }
        messageService.addMessage(m);
        LOGGER.info("Message " + m + " created!");
        return new Boolean(true);
    }


}
