package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/order")
@SessionAttributes({"order"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/askNewOrder")
    public String askCheckOut(){
        return "order/NewOrderForm";
    }

    @PostMapping("/continueOrder")
    public String continueOrder(@SessionAttribute("cart") Cart cart, @SessionAttribute("account") Account account, Order order, Model model){
        order.initOrder(account, cart);
        model.addAttribute("order",order);

        return "order/ShipForm";
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder(Order newOrder, Model model){

        orderService.insertOrder(newOrder);
        model.addAttribute("order",newOrder);
        return "order/ConfirmOrder";
    }

    @GetMapping("/viewOrder")
    public String viewOrder(HttpServletRequest request, String orderId, Model model){
        if (orderId!=null){
            Order order=orderService.getOrder(Integer.valueOf(orderId));
            model.addAttribute("order",order);
        }else{
            Order order=(Order)request.getSession().getAttribute("order");
            model.addAttribute("order",order);
        }
        return "order/ViewOrder";
    }

    @GetMapping("/viewMyOrderList")
    public String viewMyOrderList(@SessionAttribute("account") Account account,Model model){

        List<Order> orderList=orderService.getOrdersByUsername(account.getUsername());
        model.addAttribute("orderList",orderList);
        return "order/ViewMyOrderList";
    }
}
