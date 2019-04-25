package main.controllers;

import main.entity.Order;
import main.entity.Status;
import main.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView addOrder(@RequestParam(value = "description") String description) throws SQLException {
        ModelAndView mav = new ModelAndView();
        Order order = new Order(description, Status.NEW);
        orderService.create(order);
        mav.getModelMap().addAttribute("addedNumber", order.getId());
        mav.setViewName("addedOrder");
        return mav;
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ModelAndView updateStatus(@RequestParam(value = "id") long id,
                                     @RequestParam(value = "status") String status) {
        ModelAndView mav = new ModelAndView();
        orderService.updateStatusByID(Status.valueOf(status), id);
        mav.setViewName("updatedStatus");
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@RequestParam(value = "id") long id) {
        ModelAndView mav = new ModelAndView();
        orderService.delete(new Order(id));
        mav.setViewName("deletedOrder");
        return mav;
    }
}
