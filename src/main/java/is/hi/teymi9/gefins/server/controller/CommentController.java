package is.hi.teymi9.gefins.server.controller;

import is.hi.teymi9.gefins.server.exceptions.DataException;
import is.hi.teymi9.gefins.server.model.Ad;
import is.hi.teymi9.gefins.server.model.Comment;
import is.hi.teymi9.gefins.server.model.User;
import is.hi.teymi9.gefins.server.services.AdService;
import is.hi.teymi9.gefins.server.services.CommentService;
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
 * @author Ólöf Fríða Magnúsdóttir
 * @date March 2018
 *
 *
 * Sér um þjónustu í tengslum við auglýsingar
 */

@Controller
@RequestMapping("")
public class CommentController {

    // Tenging yfir í þjónustu klasa fyrir forritið
    @Autowired
    CommentService commentService;

    // Logger til að geta skrifað út villuboð
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Sækir allar auglýsingar sem ákveðinn notandi hefur skrifað
     * @param ad Auglýsingin sem verið er að skoða
     * @return Listi af athugasemdum
     * @throws DataException
     */
    @RequestMapping(value = "getAdComments", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json")
    public @ResponseBody
    List<Comment> getAdComments(@RequestBody Ad ad) throws DataException {
        LOGGER.info("JSON get user ads message: " + ad.toString());
        List<Comment> allComments = commentService.findCommentByAd(ad);
        if (allComments.isEmpty()) {
            LOGGER.info("generating ads");
            Comment c1 = new Comment("user1", "Hér er athugasemd 1",ad);
            Comment c2 = new Comment("user2", "Hér er athugasemd 2", ad);
            commentService.addComment(c1);
            commentService.addComment(c2);
        }
        LOGGER.info("fetching XML states");
        allComments = commentService.findCommentByAd(ad);
        return allComments;
    }

    /**
     *
     * @param comment athugasemd sem búa á til
     * @return Skilaboð um að tekist hafi að búa til ad
     * @throws DataException
     */
    @RequestMapping(value = "createComment", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public @ResponseBody String createUser(@RequestBody Comment comment) throws DataException {
        LOGGER.info("JSON create ad message: " + comment.toString());
        commentService.addComment(comment);
        LOGGER.info("Comment: " + comment.getComment() + " created!");
        return "JSON message received! Comment " + comment.toString() + " created!";
    }


}
