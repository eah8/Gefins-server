package is.hi.teymi9.gefins.server.controller;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Credentials;
import is.hi.teymi9.gefins.server.model.User;
import is.hi.teymi9.gefins.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 *
 * @author Einar
 * @date February 2018
 * @version 1.0
 *
 * Sér um þjónustu við notanda: innskráning, nýskráning etc
 */

@Controller
@RequestMapping("")
public class UserController {

    // Tenging yfir í þjónustu klasa fyrir forritið
    @Autowired
    UserService userService;

    // Logger til að geta skrifað út villuboð
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Birtir login síðu
     * @param model síðumodel
     * @return index síða
     */
    @RequestMapping("")
    public String logIn(ModelMap model) {
        return "index";
    }

    /**
     * Skilar lista af öllum users sem JSON hlut
     * @return JSON hlutur sem inniheldur alla users
     */
    @RequestMapping(value="getUsers", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<User> getUsers() throws DataException {
        // bý til test users:
        List<User> users = userService.allUsers();
        if (users.isEmpty()) {
            LOGGER.info("generating users");
            User user1 = new User("olla", "Olof Frida Magnusdottir", "olla@hi.is", "1234567", "olla", 200, "Sturlugata 2", true);
            User user2 = new User("sandra", "Sandra Mar Huldudottir", "sandra@hi.is", "1234567", "sandra", 201, "Sturlugata 3", false);
            userService.addUser(user1);
            userService.addUser(user2);
        }
        LOGGER.info("fetching XML states");
        users = userService.allUsers();
        return users;
    }

    /**
     *
     * @param u user sem búa á til
     * @return Skilaboð um að tekist hafi að búa til user
     * @throws DataException
     */
    @RequestMapping(value = "createUser", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String createUser(@RequestBody User u) throws DataException {
        LOGGER.info("JSON create user message: " + u.toString());
        if(!userService.doesUserExist(u.getUsername())) {
            userService.addUser(u);
            LOGGER.info("User " + u.getUsername() + " created!");
            return "JSON message received! User " + u.toString() + " created!";
        }
        else {
            return "Create user failed. Please try again.";
        }

    }

    /**
     * Skráir inn notanda ef hann er til staðar í gagnagrunni
     * @param c notandi sem skal skrá inn
     * @return notandi sem skráður var inn
     * @throws DataException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody User sendMessage(@RequestBody Credentials c) throws DataException {
        LOGGER.info("JSON login message: " + c.toString());
        User user = userService.findUserByUsernameAndPassword(c.getUsername(), c.getPassword());
        //System.out.println(user == null);
        if (user == null) {
            return new User();
        }
        return user;
    }

    /**
     *
     * @param u user sem uppfæra á
     * @return Skilaboð um að tekist hafi að uppfæra user
     * @throws DataException
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String updateUser(@RequestBody User u) throws DataException {
        LOGGER.info("JSON update user message: " + u.toString());
        String result = userService.updateUser(u);
        return result;

    }


    /**
     *
     * @param user notandi sem eyða skal
     * @return Skilaboð um að tekist hafi að eyða notanda
     * @throws DataException
     */
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String deleteUser(@RequestBody User user) throws DataException {
        LOGGER.info("JSON delete user message: " + user.toString());
        userService.deleteUser(user);
        LOGGER.info("User " + user.getUsername() + " deleted!");
        return "User deleted successfully";
    }
}
