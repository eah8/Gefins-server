package is.hi.teymi9.gefins.server.services;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;

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
public interface AdService {

    /**
     * Bætir við ad í adRep
     *
     * @param ad auglýsing
     * @throws DataException
     */
    public void addAd(Ad ad) throws DataException;

    /**
     * Skilar öllum ads í adRep
     *
     * @return listi af ads
     * @throws DataException
     */
    public List<Ad> allAds() throws DataException;
    
    /**
     * Vistar ad
     * @param ad auglýsing sem skal vista
     * @return auglýsingin sem vistuð var
     * @throws DataException
     */
    public Ad save(Ad ad) throws DataException;

    /**
     * Leitar að ad með ákveðin id og skilar því ef finnst
     * @param id token sem leita skal eftir
     * @return ad með token ef til, annars null
     */
    public Ad findAdById(UUID id);

    /**
     * Uppfærir ad í gagnagrunni
     * @param ad Uppfærðar upplýsingar auglýsingar
     * @return skilaboð um hvort uppfærsla tókst
     * @throws DataException
     */
    public String updateAd(Ad ad) throws DataException;

    /**
     * Finnur allar ads eftir ákveðinn notanda
     * @param username notendannafn notandans
     * @return Listi af ads
     */
    public List<Ad> findAdByAdUsername(String username);

}
