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
     * Segir til um hvort notandi með ákveðið notandanafn sé þegar til
     * @param username notandanafn sem athuga skal
     * @return true ef notandi er til, annars false
     */
    public boolean doesUserExist(String username);

    /**
     * Finnur user með nafn username og lykilorð password
     * @param username
     * @param password
     * @return 
     * @throws DataException
     */
    public User findUserByUsernameAndPassword(String username, String password) throws DataException;

    public User findUserByUsername(String username);

    /**
     * Leitar að notanda með ákveðin token og skilar honum ef finnst
     * @param id token sem leita skal eftir
     * @return notandi með token ef til, annars null
     */
    public User findUserById(UUID id);

    /**
     * Uppfærir notanda í gagnagrunni
     * @param u Uppfærðar upplýsingar notanda
     * @return skilaboð um hvort uppfærsla tókst
     * @throws DataException
     */
    public String updateUser(User u) throws DataException;

}
