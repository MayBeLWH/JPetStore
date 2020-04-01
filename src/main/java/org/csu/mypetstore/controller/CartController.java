package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@RequestMapping("/cart")
public class CartController {

//    @GetMapping("/viewCart")
//    public String viewCart(@SessionAttribute("cart") Cart cart, Model model){
//
//    }
//    @GetMapping("/addItemToCart")
//    public String addItemToCart(String itemId){
//
//    }
}
