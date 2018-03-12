package is.hi.teymi9.gefins.server.controller;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.Comment;
import is.hi.teymi9.gefins.server.model.Credentials;
import is.hi.teymi9.gefins.server.model.User;
import is.hi.teymi9.gefins.server.services.AdService;
import is.hi.teymi9.gefins.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Einar
 * @date March 2018
 *
 * Sér um þjónustu í tengslum við auglýsingar
 */

@Controller
@RequestMapping("")
public class AdController {

    // Tenging yfir í þjónustu klasa fyrir forritið
    @Autowired
    AdService adService;

    // Logger til að geta skrifað út villuboð
    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    /**
     * Sækir allar auglýsingar sem ákveðinn notandi hefur skrifað
     * @param u user hver auglýsingar skal sækja
     * @return Listi af auglýsingum
     * @throws DataException
     */
    @RequestMapping(value = "getUserAds", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json")
    public @ResponseBody List<Ad> getUserAds(@RequestBody User u) throws DataException {
        LOGGER.info("JSON get user ads message: " + u.toString());
        List<Ad> allAds = adService.findAdByAdUsername(u.getUsername());
        return allAds;
    }


    /**
     * Býr til og skilar lista af dummy ad sem JSON hlut
     * @return JSON hlutur sem inniheldur öll ads
     */
    @RequestMapping(value="getDummyAds", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Ad> getDummyAds() throws DataException {
        // bý til test ad:
        List<Ad> ads = adService.allAds();
        if (ads.isEmpty()) {
            LOGGER.info("generating ads");
            ArrayList<Comment> comments = new ArrayList();
            Ad ad1 = new Ad("gefins", "Mjúkur sófi", "Húsgögn", "Sófi", "Svartur", "Mjúkur 3ja sæta sófi úr microsoft efni", "olla", comments, "105");
            Ad ad2 = new Ad("óska eftir", "Eldhúsborð", "Húsgögn", "Borð", "Hvítur", "4 manna eldhúsborð úr Ikea", "sandra", comments, "201");
            Ad ad3 = new Ad("gefins", "Leðurjakki", "Fatnaður", "Yfirhöfn", "Svartur", "Stuttur leðurjakki í stærð 38", "sandra", comments, "123");
            adService.addAd(ad1);
            adService.addAd(ad2);
            adService.addAd(ad3);
        }
        LOGGER.info("fetching XML states");
        ads = adService.allAds();
        return ads;
    }
}
