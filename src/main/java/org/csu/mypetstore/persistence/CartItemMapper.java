package org.csu.mypetstore.persistence;

import org.apache.ibatis.annotations.Param;
import org.csu.mypetstore.domain.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemMapper {
    List<CartItem> getCartItemListByUsername(String username);

    void removeItemFromCart(String username,String itemId);

    void updateQuantityByItemIdAndNumber(@Param("username") String username, @Param("itemId") String itemId,@Param("number")  int number);

    void insertCartByCartItem(@Param("cartItemId") String cartItemId,@Param("username") String username,@Param("itemId") String itemId);
}
