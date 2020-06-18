package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/catalog")
@SessionAttributes({"accountState"})
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/view")
    public String view(Model model){
        model.addAttribute("accountState",false);
        return "catalog/Main";
    }

    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId, Model model){
        if (categoryId != null){
            Category category = catalogService.getCategory(categoryId);
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            model.addAttribute("category",category);
            model.addAttribute("productList",productList);
            return "catalog/Category";
        }
        return "catalog/Main";
    }

    @GetMapping("/viewProduct")
    public String viewProduct(String productId, Model model){
        if (productId != null){
            Product product = catalogService.getProduct(productId);
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            System.out.println(itemList.get(0).getProductId());
            model.addAttribute("product",product);
            model.addAttribute("itemList",itemList);
            return "catalog/Product";
        }
        return "catalog/Main";
    }

    @GetMapping("/viewItem")
    public String viewItem(String itemId,Model model){
        if (itemId != null){
            Item item=catalogService.getItem(itemId);
            Product product=item.getProduct();
            processProductDescription(product);
            model.addAttribute("product",product);
            model.addAttribute("item",item);
            return "catalog/Item";
        }
        return "catalog/Main";
    }


    @PostMapping("/searchProduct")
    public String searchProduct(String keyword ,Model model){
        List<Product> productList=catalogService.searchProductList(keyword);
        model.addAttribute("productList",productList);
        processProductDescription(productList);
        return "catalog/SearchProducts";
    }
    private void processProductDescription(Product product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage("../"+temp[1]);
        product.setDescriptionText(temp[2].substring(1));
    }
    private void processProductDescription(List<Product> productList){
        for(Product product : productList) {
            processProductDescription(product);
        }
    }
}
