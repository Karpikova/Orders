package main.controllers;

import main.entity.Comment;
import main.entity.Order;
import main.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ModelAndView addComment(@RequestParam(value = "id") long id,
                                   @RequestParam(value = "description") String description) {
        ModelAndView mav = new ModelAndView();
        Comment comment = new Comment(new Order(id), description);
        commentService.create(comment);
        mav.getModelMap().addAttribute("addedNumber", comment.getId());
        mav.setViewName("addedComment");
        return mav;
    }

}
