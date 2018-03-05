package is.hi.teymi9.gefins.server.controller;


import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.Comment;
import is.hi.teymi9.gefins.server.services.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kristín María
 * @date March 2018
 *
 * Sér um þjónustu við auglýsingar: bæta við osfrv...
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
     * Skilar lista af öllum ads sem JSON hlut
     * @return JSON hlutur sem inniheldur allar ads
     */
    @RequestMapping(value="getAds", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Ad> getAds() throws DataException {
        // bý til test ads:
        List<Ad> ads = adService.allAds();
        if (ads.isEmpty()) {
            LOGGER.info("generating ads");
            ArrayList<Comment> comments = new ArrayList<Comment>();
            Ad ad = new Ad(1, "gefins", "Mjúkur sófi", "Húsgögn", "Sófi", "Svartur", "Mjúkur 3ja sæta sófi úr microsoft efni", "olla", comments, "105");
            Ad ad2 = new Ad(2, "óska eftir", "Eldhúsborð", "Húsgögn", "Borð", "Hvítur", "4 manna eldhúsborð úr Ikea", "sandra", comments, "201");
            Ad ad3 = new Ad(3, "gefins", "Leðurjakki", "Fatnaður", "Yfirhöfn", "Svartur", "Stuttur leðurjakki í stærð 38", "sandra", comments, "123");
            adService.addAd(ad);
            adService.addAd(ad2);
            adService.addAd(ad3);
        }
        LOGGER.info("fetching XML states");
        ads = adService.allAds();
        return ads;
    }


    /**
     *
     * @param u ad sem búa á til
     * @return Skilaboð um að tekist hafi að búa til ad
     * @throws DataException
     */
    @RequestMapping(value = "createAd", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String createUser(@RequestBody Ad u) throws DataException {
        LOGGER.info("JSON create ad message: " + u.toString());
        adService.addAd(u);
        LOGGER.info("Ad " + u.getAdName() + " created!");
        return "JSON message received! Ad " + u.toString() + " created!";
    }


}
