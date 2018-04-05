package is.hi.teymi9.gefins.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Kristín María
 * @date March 2018
 * @version 1.0
 *
 * Ad klasi til að senda upplýsingar um auglýsingu á þjón
 */

@Entity
@Table(name="ad")
public class Ad {

    // unique auðkenni fyrir ad
    @Id
    private UUID id = UUID.randomUUID();
    // er auglýsingin að gefa eða þiggja?
    private String giveorTake;
    // heiti
    private String adName;
    // tegund
    private String adType;
    // undirtegund
    private String adTypeOfType;
    // litur
    private String adColor;
    // lýsing
    private String adDescription;
    // höfundur auglýsingar
    private String adUsername;
    // athugasemdir sem gerðar hafa verið við auglýsinguna
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="ad")
    @JsonManagedReference
    private List<Comment> adComments;
    // póstnúmer staðsetning hlutar
    private String adLocation;

    /**
     * Smiður með viðföngum
     * @param giveorTake gefa eða þiggja?
     * @param adName heiti
     * @param adType tegund
     * @param adTypeOfType undirtegund
     * @param adColor litur
     * @param adDescription lýsing
     * @param adUsername höfundur
     * @param adComments athugasemdir
     * @param adLocation staðsetning
     */
    public Ad(String giveorTake, String adName, String adType, String adTypeOfType,
              String adColor, String adDescription, String adUsername, ArrayList<Comment> adComments, String adLocation) {
        this.giveorTake = giveorTake;
        this.adName = adName;
        this.adType = adType;
        this.adTypeOfType = adTypeOfType;
        this.adColor = adColor;
        this.adDescription = adDescription;
        this.adUsername = adUsername;
        this.adComments = adComments;
        this.adLocation = adLocation;
    }

    /**
     * Tómur smiður
     */
    public Ad() {
    }

    /**
     * Nær í id á auglýsingu
     * @return id
     */
    public UUID getId() { return id; }

    /**
     * stillir id á auglýsingu
     */
    public void setId(UUID id) { this.id = id; }

    /**
     * Nær í give or take breytuna á auglýsingunni
     * @return giveOrTake
     */
    public String getGiveorTake() { return giveorTake; }

    /**
     * stillir giveOrTake breytuna
     * @param giveOrTake er verið að gefa eða þyggja
     */
    public void setGiveorTake(String giveOrTake) { this.giveorTake = giveOrTake; }

    /**
     * nær í nafnið á auglýsingunni
     * @return adName
     */
    public String getAdName() { return adName; }

    /**
     * stillir nafnið á auglýsingunni
     * @param adName nafnið á auglýsingunni
     */
    public void setAdName(String adName) { this.adName = adName; }

    /**
     * nær í týpuna á auglýsingunni
     * @return adType
     */
    public String getAdType() { return adType; }

    /**
     * stillir týpuna á auglýsingunni
     * @param adType týpan á auglýsingunni
     */
    public void setAdType(String adType) { this.adType = adType; }

    /**
     * nær í "týpu af týpu" breytuna
     * @return adTypeOfType
     */
    public String getAdTypeOfType() { return adTypeOfType; }

    /**
     * stillir "týpu af týpu" breytuna
     * @param adTypeOfType "týpa af týpu"
     */
    public void setAdTypeOfType(String adTypeOfType) { this.adTypeOfType = adTypeOfType; }

    /**
     * nær í litin á vörunni sem verið er að auglýsa
     * @return adColor
     */
    public String getAdColor() { return adColor; }

    /**
     * Stillir litinn á vörunni sem verið er að auglýsa
     * @param adColor liturinn á vörunni
     */
    public void setAdColor(String adColor) { this.adColor = adColor; }

    /**
     * Nær í upplýsingar um vöruna í auglýsingunni
     * @return adDescription
     */
    public String getAdDescription() { return adDescription; }

    /**
     * Stillir upplýsingar um vöruna í auglýsingunni
     * @param adDescription upplýsingar um vöruna
     */
    public void setAdDescription(String adDescription) { this.adDescription = adDescription; }

    /**
     * Nær í notandanafn á notanda sem skrifaði auglýsinguna
     * @return adUsername
     */
    public String getAdUsername() { return adUsername; }

    /**
     * stillir notendanafnið sem er tengt við auglýsinguna
     * @param adUsername notandanafn
     */
    public void setAdUsername(String adUsername) { this.adUsername = adUsername; }

    /**
     * Nær í athugasemdir sem auglýsingin á
     * @return adComments, listi af commentum
     */
    public List<Comment> getAdComments() {
        return adComments;
    }

    /**
     * Stillir athugasemdirnar sem auglýsing á
     * @param adComments listi af athugasemdum
     */
    public void setAdComments(ArrayList<Comment> adComments) {
        this.adComments = adComments;
    }

    /**
     * nær í staðsetningu á vörunni sem auglýsingin lýsir
     * @return adLocation
     */
    public String getAdLocation() { return adLocation; }

    /**
     * stillir staðsetningu á vörunni sem verið er að auglýsa
     * @param adLocation staðsetning
     */
    public void setAdLocation(String adLocation) { this.adLocation = adLocation; }
}
