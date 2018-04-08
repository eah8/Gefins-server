package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;

import is.hi.teymi9.gefins.server.model.User;
import is.hi.teymi9.gefins.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date February 2018
 *
 * Þjónusta sem sér um samskipti við repositories
 */
@Service
public class UserServiceImp implements UserService {

    // Tenging yfir í safn af userum
    @Autowired
    UserRepository userRep;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bætir við user í userRep
     *
     * @param u user
     * @throws DataException
     */
    @Override
    public void addUser(User u) throws DataException {
        String hashedPassword = passwordEncoder.encode(u.getPassword());
        //System.out.println(hashedPassword);
        u.setPassword(hashedPassword);
        try {
            userRep.save(u);    // Notum save en ekki add
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Skilar öllum users í userRep
     *
     * @return listi af users
     */
    @Override
    public List<User> allUsers() throws DataException {
        try {
            return userRep.findAll();    // Notum findAll í staðinn fyrir getAll
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Vistar user
     *
     * @param u user sem skal vista
     * @return user sem vistaður var
     */
    @Override
    public User save(User u) throws DataException {

        try {
            return userRep.save(u);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Segir til um hvort notandi með ákveðið notandanafn sé þegar til
     * @param username notandanafn sem athuga skal
     * @return true ef notandi er til, annars false
     */
    @Override
    public boolean doesUserExist(String username) {
        List<User> users = userRep.findByUsername(username);
        if (users.isEmpty()) {
            return false;
        }
        else {
            return true;
        }

    }

    /**
     * Finnur user með nafn username og lykilorð password
     *
     * @param username
     * @param password
     * @return user with matching name and password if exists, else null
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) throws DataException {
        try {
            List<User> users = userRep.findByUsername(username);
            if (users.isEmpty()) {
                return null;
            }
            for (User u : users) {
                if (passwordEncoder.matches(password, u.getPassword())) {
                    return u;
                }
            }
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        List<User> users = userRep.findByUsername(username);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    /**
     * Leitar að notanda með ákveðin token og skilar honum ef finnst
     * @param id token sem leita skal eftir
     * @return notandi með token ef til, annars null
     */
    @Override
    public User findUserById(UUID id) {
        List<User> users = userRep.findById(id);
        if (users != null)
        {
            return users.get(0);
        }
        return null;
    }

    /**
     * Uppfærir notanda í gagnagrunni
     * @param u Uppfærðar upplýsingar notanda
     * @return skilaboð um hvort uppfærsla tókst
     * @throws DataException
     */
    @Override
    public String updateUser(User u) throws DataException {
        User oldUser = findUserById(u.getId());
        User sameNameUser = findUserByUsername(u.getUsername());
        if (oldUser == null || (sameNameUser != null && oldUser.getUsername() != sameNameUser.getUsername()))  {
            return "Update user failed";
        }
        u.setPassword(oldUser.getPassword());
        oldUser = u;
        save(oldUser);
        return "Update user successful!";
    }


    /**
     * Eyðir notanda
     * @param user notandi sem skal eyða
     */
    @Override
    public void deleteUser(User user) {
        userRep.delete(user);
    }

}
