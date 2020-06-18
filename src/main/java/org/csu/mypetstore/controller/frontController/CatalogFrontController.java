package org.csu.mypetstore.controller.frontController;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.domain.ResponseTemplate;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/catalogs")
public class CatalogFrontController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/products")
    @ResponseBody
    public ResponseTemplate getProducts(String catalog){

        List<Product> productList=catalogService.getProductListByCategory(catalog);
        processProductDescription(productList);

        ResponseTemplate responseTemplate=new ResponseTemplate();
        responseTemplate.setData(productList);
        responseTemplate.setJudgement(true);
        return responseTemplate;
    }


    private void processProductDescription(Product product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage("http://localhost:8081/"+temp[1]);
        product.setDescriptionText(temp[2].substring(1));
    }
    private void processProductDescription(List<Product> productList){
        for(Product product : productList) {
            processProductDescription(product);
        }
    }
}
