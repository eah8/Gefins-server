package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;

import java.util.List;


/**
 *
 * @author Kristín María
 * @date March 2018
 *
 * Þjónusta sem sér um samskipti við repositories
 *
 */

public interface AdService {


    /**
     * Bætir við ad í adRep
     *
     * @param u ad
     * @throws DataException
     */

    public void addAd(Ad u) throws DataException;


    /**
     * Skilar öllum ads í adRep
     *
     * @return listi af ads
     * @throws DataException
     */
    public List<Ad> allAds() throws DataException;

}
