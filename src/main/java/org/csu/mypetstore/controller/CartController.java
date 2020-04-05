package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionScope
@RequestMapping("/cart")
@SessionAttributes({"cart"})
public class CartController {

    @Autowired
    private Cart cart;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/viewCart")
    public String viewCart(@SessionAttribute("cartItemList") List<CartItem> cartItemList, Model model){
        cart.setItemList(cartItemList);
        model.addAttribute("cart",cart);
        return "cart/Cart";
    }

    @GetMapping("/removeItemFromCart")
    public String removeItemFromCart(@SessionAttribute("cart") Cart cart,@SessionAttribute("account") Account account,String workingItemId){
        cartItemService.removeItemFromCart(account.getUsername(),workingItemId);
        List<CartItem> cartItemList=cartItemService.getCartItemListByUsername(account.getUsername());
        cart.setItemList(cartItemList);

        return "cart/Cart";
    }

    @PostMapping("/updateCartQuantities")
    public String updateCartQuantities(@SessionAttribute("cart") Cart cart, @SessionAttribute("account") Account account, HttpServletRequest request, Model model){
        List<CartItem> cartItemList=cart.getCartItemList();
        String username=account.getUsername();
        for (int i = 0; i < cartItemList.size(); i++) {
            String itemId=cartItemList.get(i).getItemid();
            int newInt = Integer.parseInt(request.getParameter(itemId));
            cartItemService.updateQuantityByItemIdAndNumber(username,itemId,newInt);
        }
        List<CartItem> newCartItemList=cartItemService.getCartItemListByUsername(account.getUsername());
        model.addAttribute("cartItemList",newCartItemList);
        cart.setItemList(newCartItemList);
        return "cart/Cart";
    }

    @GetMapping("/addItemToCart")
    public String addItemToCart(HttpServletRequest request,String workingItemId,Model model){
        Account account = (Account) request.getSession().getAttribute("account");
        List<CartItem> myCartItemList = (List<CartItem>) request.getSession().getAttribute("cartItemList");
        if (account == null){
            return "account/SignOn";
        }else{
            cartItemService.insertCartByCartItem(account.getUsername(),workingItemId);

            Cart cart= (Cart) request.getSession().getAttribute("cart");
            if (cart == null){
                cart= new Cart();
            }

            List<CartItem> newCartItemList=cartItemService.getCartItemListByUsername(account.getUsername());
            cart.setItemList(newCartItemList);
            model.addAttribute("cart",cart);
            return "cart/Cart";
        }

    }
}
