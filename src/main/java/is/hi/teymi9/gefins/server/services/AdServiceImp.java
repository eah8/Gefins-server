package is.hi.teymi9.gefins.server.services;


import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.repository.AdRepository;
import is.hi.teymi9.gefins.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.util.List;


/**
 *
 * @author Kristín María
 * @date March 2018
 *
 * Þjónusta sem sér um samskipti við repositories
 */

public class AdServiceImp implements AdService {

    // Tenging yfir í safn af ads
    @Autowired
    AdRepository adRep;

    @Override
    public void addAd(Ad u) throws DataException {
        try {
            adRep.save(u);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Skilar öllum ads í adRep
     *
     * @return listi af ads
     */
    @Override
    public List<Ad> allAds() throws DataException {
        try {
            return adRep.findAll();    // Notum findAll í staðinn fyrir getAll
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

}
