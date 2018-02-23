package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.User;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date February 2018
 * 
 * Þjónusta sem sér um samskipti við repositories
 *
 */
public interface UserService {


    /**
     * Bætir við user í userRep
     *
     * @param u user
     * @throws DataException
     */
    public void addUser(User u) throws DataException;

    /**
     * Skilar öllum users í userRep
     *
     * @return listi af users
     * @throws DataException
     */
    public List<User> allUsers() throws DataException;
    
    /**
     * Vistar user
     * @param u user sem skal vista
     * @return userinn sem vistaður var
     * @throws DataException
     */
    public User save(User u) throws DataException;
    
    /**
     * Finnur user með nafn username og lykilorð password
     * @param username
     * @param password
     * @return 
     * @throws DataException
     */
    public User findUser(String username, String password) throws DataException;

    /**
     * Leitar að notanda með ákveðin token og skilar honum ef finnst
     * @param token token sem leita skal eftir
     * @return notandi með token ef til, annars null
     */
    public User findUserByToken(UUID token);

}
