package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.User;
import is.hi.teymi9.gefins.server.repository.AdRepository;
import is.hi.teymi9.gefins.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Einar
 * @date March 2018
 *
 * Þjónusta sem sér um samskipti við Ad repository
 *
 */
@Service
public class AdServiceImp implements AdService {

    // Tenging yfir í safn af ads
    @Autowired
    AdRepository adRep;

    /**
     * Bætir við ad í adRep
     *
     * @param ad auglýsing
     * @throws DataException
     */
    @Override
    public void addAd(Ad ad) throws DataException {
        try {
            adRep.save(ad);    // Notum save en ekki add
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Skilar öllum ads í adRep
     *
     * @return listi af ads
     * @throws DataException
     */
    @Override
    public List<Ad> allAds() throws DataException {
        try {
            return adRep.findAll();    // Notum findAll í staðinn fyrir getAll
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Vistar ad
     * @param ad auglýsing sem skal vista
     * @return auglýsingin sem vistuð var
     * @throws DataException
     */
    @Override
    public Ad save(Ad ad) throws DataException {

        try {
            return adRep.save(ad);
        } catch (DataAccessException s) {
            throw new DataException(s);
        }
    }

    /**
     * Leitar að ad með ákveðin id og skilar því ef finnst
     * @param id token sem leita skal eftir
     * @return ad með token ef til, annars null
     */
    @Override
    public Ad findAdById(UUID id) {
        List<Ad> ads = adRep.findById(id);
        if (ads != null)
        {
            return ads.get(0);
        }
        return null;
    }

    /**
     * Uppfærir ad í gagnagrunni
     * @param ad Uppfærðar upplýsingar auglýsingar
     * @return skilaboð um hvort uppfærsla tókst
     * @throws DataException
     */
    @Override
    public String updateAd(Ad ad) throws DataException {
        Ad oldAd = findAdById(ad.getId());
        if (oldAd == null)  {
            return "Update ad failed";
        }
        oldAd = ad;
        save(oldAd);
        return "Update ad successful!";
    }

    /**
     * Finnur allar ads eftir ákveðinn notanda
     * @param username notendannafn notandans
     * @return Listi af ads
     */
    @Override
    public List<Ad> findAdByAdUsername(String username) {
        return adRep.findByAdUsername(username);
    }
}
