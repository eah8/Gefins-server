package is.hi.teymi9.gefins.server.exceptions;

/**
 *
 * @author Einar
 * @date February 2018
 */
public class DataException extends Exception{
    
    public DataException (Exception e) {
        super(" database operation unsuccessful");
    }
}
