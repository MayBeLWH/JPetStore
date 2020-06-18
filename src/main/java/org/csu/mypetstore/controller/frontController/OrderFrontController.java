package org.csu.mypetstore.controller.frontController;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.ResponseTemplate;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderFrontController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseTemplate getAllOrders(){
        ResponseTemplate responseTemplate=new ResponseTemplate();

        List<Order> orderList=orderService.getAllOrders();
        responseTemplate.setData(orderList);

        return responseTemplate;
    }

    @GetMapping("/deliverOrder")
    @ResponseBody
    public ResponseTemplate deliverOrder(int orderId,

                                         String billToFistName,
                                         String billToLastName,
                                         String billAddress1,
                                         String billAddress2,
                                         String billCity,
                                         String billState,
                                         String billZip,
                                         String billCountry,

                                         String shipToFistName,
                                         String shipToLastName,
                                         String shipAddress1,
                                         String shipAddress2,
                                         String shipCity,
                                         String shipState,
                                         String shipZip,
                                         String shipCountry){
        Order order=new Order();

        order.setOrderId(orderId);

        order.setBillToFirstName(billToFistName);
        order.setBillToLastName(billToLastName);
        order.setBillAddress1(billAddress1);
        order.setBillAddress2(billAddress2);
        order.setBillCity(billCity);
        order.setBillState(billState);
        order.setBillZip(billZip);
        order.setBillCountry(billCountry);


        order.setShipToFirstName(shipToFistName);
        order.setShipToLastName(shipToLastName);
        order.setShipAddress1(shipAddress1);
        order.setShipAddress2(shipAddress2);
        order.setShipCity(shipCity);
        order.setShipState(shipState);
        order.setShipZip(shipZip);
        order.setShipCountry(shipCountry);

        orderService.updateOrder(order);

        ResponseTemplate responseTemplate=new ResponseTemplate();

        responseTemplate.setJudgement(true);

        return responseTemplate;
    }
}
