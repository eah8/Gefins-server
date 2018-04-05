package is.hi.teymi9.gefins.server.controller;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import is.hi.teymi9.gefins.server.model.Comment;
import is.hi.teymi9.gefins.server.services.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


/**
 *
 * @author Kristín María og Einar
 * @date March 2018
 * @version 1.0
 *
 * Sér um þjónustu í tengslum við auglýsingar
 *
 */

@Controller
@RequestMapping("")
public class AdController {

    // Tenging yfir í þjónustu klasa fyrir forritið
    @Autowired
    AdService adService;

    // Logger til að geta skrifað út villuboð
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Sækir allar auglýsingar sem ákveðinn notandi hefur skrifað
     * @param u user hver auglýsingar skal sækja
     * @return Listi af auglýsingum
     * @throws DataException
     */
    @RequestMapping(value = "getUserAds", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json")
    public @ResponseBody
    List<Ad> getUserAds(@RequestBody User u) throws DataException {
        LOGGER.info("JSON get user ads message: " + u.toString());
        List<Ad> allAds = adService.findAdByAdUsername(u.getUsername());
        return allAds;
    }


    /**
     * Sækir allar auglýsingar sem eru í ákveðnum flokki og undirflokki
     * @param ad  auglýsing sem segir hvaða flokk af auglýsingum skal sækja
     * @return Listi af auglýsingum í um beðnum flokki
     * @throws DataException
     */
    @RequestMapping(value = "getAdsByType", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json")
    public @ResponseBody
    List<Ad> getAdsByType(@RequestBody Ad ad) throws DataException {
        LOGGER.info("JSON get type of ads message: " + ad.toString());
        List<Ad> adsOfType = adService.findAdsOfType(ad.getAdType(), ad.getAdTypeOfType(), ad.getGiveorTake(), ad.getAdColor());
        return adsOfType;
    }


    /**
     * Sækir allar auglýsingar
     * @return Listi sem inniheldur allar auglýsingar
     * @throws DataException
     */
    @RequestMapping(value="getAds", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Ad> getAds() throws DataException {
        // bý til test ads:
        List<Ad> ads = adService.allAds();
        // Ef listinn er tómur þá senda "dummy" auglýsingar
        if (ads.isEmpty()) {
            LOGGER.info("generating ads");
            ArrayList<Comment> comments = new ArrayList<Comment>();
            Ad ad = new Ad("Gefins", "Mjúkur sófi", "Húsgögn", "Sófi", "Svartur", "Mjúkur 3ja sæta sófi úr microsoft efni", "olla", comments, "105");
            Ad ad2 = new Ad("Óska eftir", "Eldhúsborð", "Húsgögn", "Borð", "Hvítur", "4 manna eldhúsborð úr Ikea", "sandra", comments, "201");
            Ad ad3 = new Ad("Gefins", "Leðurjakki", "Fatnaður", "Yfirhöfn", "Svartur", "Stuttur leðurjakki í stærð 38", "sandra", comments, "123");
            Ad ad4 = new Ad("Gefins", "Eldhússtóll", "Húsgögn", "Stóll", "Svartur", "Eldhússtóll úr Rúmfatalagernum", "sandra", comments, "123");
            adService.addAd(ad);
            adService.addAd(ad2);
            adService.addAd(ad3);
            adService.addAd(ad4);
        }
        LOGGER.info("fetching XML states");
        ads = adService.allAds();
        return ads;
    }


    /**
     *
     * @param u auglýsing sem búa á til
     * @return Skilaboð um að tekist hafi að búa til auglýsingu
     * @throws DataException
     */
    @RequestMapping(value = "createAd", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String createAd(@RequestBody Ad u) throws DataException {
        LOGGER.info("JSON create ad message: " + u.toString());
        adService.addAd(u);
        LOGGER.info("Ad " + u.getAdName() + " created!");
        return "JSON message received! Ad " + u.toString() + " created!";
    }


    /**
     *
     * @param a auglýsing sem á að uppfæra
     * @return Skilaboð um að tekist hafi að uppfæra auglýsingu
     * @throws DataException
     */
    @RequestMapping(value = "updateAd", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String updateAd(@RequestBody Ad a) throws DataException {
        LOGGER.info("JSON update user message: " + a.toString());
        String result = adService.updateAd(a);
        return result;
    }


    /**
     *
     * @param ad auglýsing sem eyða skal
     * @return Skilaboð um að tekist hafi að eyða auglýsingu
     * @throws DataException
     */
    @RequestMapping(value = "deleteAd", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String deleteAd(@RequestBody Ad ad) throws DataException {
        LOGGER.info("JSON delete ad message: " + ad.toString());
        adService.deleteAd(ad);
        LOGGER.info("Ad " + ad.getAdName() + " deleted!");
        return "Ad deleted successfully";
    }


}

