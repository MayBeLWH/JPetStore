package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.LineItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemMapper {
    void insertLineItem(LineItem lineItem);

    List<LineItem> getLineItemsByOrderId(int orderId);
}
