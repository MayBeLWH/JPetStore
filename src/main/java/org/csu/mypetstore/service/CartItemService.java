package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.persistence.CartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private CatalogService catalogService;

    public List<CartItem> getCartItemListByUsername(String username){
        List<CartItem>  cartItemList = cartItemMapper.getCartItemListByUsername(username);
        for (int i = 0; i < cartItemList.size(); i++) {
            cartItemList.get(i).setItem(catalogService.getItem(cartItemList.get(i).getItemid()));
        }
        return cartItemList;
    }

    public void removeItemFromCart(String username,String itemId){
        cartItemMapper.removeItemFromCart(username, itemId);
    }

    public void updateQuantityByItemIdAndNumber(String username,String itemId,int number){
        cartItemMapper.updateQuantityByItemIdAndNumber(username, itemId, number);
    }

    public void insertCartByCartItem(String username,String itemId){
        cartItemMapper.insertCartByCartItem(username+"+"+itemId,username,itemId);
    }

}
