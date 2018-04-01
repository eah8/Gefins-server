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

/**
 *
 * @author Kristín María
 * @date March 2018
 *
 * Þjónusta sem sér um samskipti við repositories
 */

@Service
public class AdServiceImp implements AdService {

    // Tenging yfir í safn af ads
    @Autowired
    AdRepository adRep;
    //Tómur strengur
    String EMPTY_STRING = "";


    /**
     * Bætir við ad í adRep
     *
     * @param u auglýsing
     * @throws DataException
     */
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
     * Skilar auglýsingum af ákveðnum flokki frá adRep
     * adType       tegund yfirflokk
     * adTypeOfType tegund undirflokks
     * @return listi af auglýsingum af ákveðnum flokki
     * @throws DataException
     */
    @Override
    public List<Ad> findAdsOfType(String adType, String adTypeOfType) throws DataException {
        try {
            /*
            //ef yfirflokkur er tómur strengur þá er leitað að ÖLLUM auglýsingum (þ.e. enginn flokkun)
            if(adType == EMPTY_STRING){
                return adRep.findAll();
            }
            //ef undirflokkur er tómur stengur þá er leitað að öllum auglýsingum sem hafa yfirflokkinn
            //og með alla undirflokka (þ.e. engin undirflokkun)
            else if(adTypeOfType == EMPTY_STRING){
                return adRep.findByAdType(adType);
            }
            //ef bæði yfirflokkur og undirflokkur eru fyrir hendi þá er leitað að þeim auglýsingum
            //sem innihalda báða flokkana
            else{
            */
                return adRep.findByAdTypeAndAdTypeOfType(adType, adTypeOfType);
            //}
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
