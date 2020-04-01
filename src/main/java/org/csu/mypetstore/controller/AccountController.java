package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
@SessionAttributes({"account", "myList", "authenticated"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/singOnForm")
    public String singOnForm(){
        return "account/SignOn";
    }

    @PostMapping("/signOn")
    public String signOn(String username, String password, Model model){
        Account loginAccount=accountService.findAccountByUsernameAndPassword(username, password);

        if(loginAccount == null){
            String msg = "Invalid username or password.  Signon failed.";
            model.addAttribute("msg",msg);
            return "account/SignOn";
        }else {
            loginAccount.setPassword(null);
//            List<Product> myList =catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", loginAccount);

//            model.addAttribute("myList",myList);
            model.addAttribute("authenticated",authenticated);
            return "catalog/Main";
        }
    }

    @GetMapping("/signOut")
    public String signOut(Model model){
        Account loginAccount = new Account();
//        List<Product> myList = null;
        boolean authenticated = false;
        model.addAttribute("account", loginAccount);
//        model.addAttribute("myList", myList);
        model.addAttribute("authenticated", authenticated);
        return "catalog/Main";
    }

    @GetMapping("/registerForm")
    public String registerForm(Model model){
        model.addAttribute("account",new Account());
        return "account/Register";
    }

    @PostMapping("/creatAccount")
    public String creatAccount(Account registerAccount,Model model){
        accountService.insertAccount(registerAccount);
        model.addAttribute("account",registerAccount);
        return "catalog/Main";
    }

    @GetMapping("/viewAccount")
    public String viewAccount(@SessionAttribute("account")Account account, Model model){
        if (model.getAttribute("account") == null)
            return "catalog/Main";
        else
            return "account/EditAccount";
    }
    @PostMapping("/editAccount")
    public String editAccount(Account editAccount,Model model){
        accountService.editAccount(editAccount);
        editAccount = accountService.findAccountByUsername(editAccount.getUsername());
//        List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
        boolean authenticated = true;
        model.addAttribute("account", editAccount);
//        model.addAttribute("myList", myList);
        model.addAttribute("authenticated", authenticated);
        return "catalog/Main";
    }
}
