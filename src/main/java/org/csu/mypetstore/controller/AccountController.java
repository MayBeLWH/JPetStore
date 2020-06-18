package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartItemService;
import org.csu.mypetstore.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/account")
@SessionAttributes({"account", "myList", "authenticated","cartItemList","word","accountState"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CartItemService cartItemService;

    String word="";
    @GetMapping("/singOnForm")
    public String singOnForm(){
        return "account/SignOn";
    }

    @PostMapping("/signOn")
    public String signOn( @SessionAttribute("accountState") boolean accountState, String username, String password,String codeWord, Model model){

        Account loginAccount=accountService.findAccountByUsernameAndPassword(username, Md5Util.encrypt(password));
        if (!word.equals(codeWord)){
            String msg = "Invalid verification code.Login failed.";
            model.addAttribute("msg",msg);
            return "account/SignOn";
        }else{
            if(loginAccount == null){
                String msg = "Invalid username or password.Login failed.";
                model.addAttribute("msg",msg);
                return "account/SignOn";
            }else {
                loginAccount.setPassword(null);
                model.addAttribute("accountState",!accountState);
//            List<Product> myList =catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                List<CartItem> cartItemList=cartItemService.getCartItemListByUsername(loginAccount.getUsername());
                boolean authenticated = true;
                model.addAttribute("account", loginAccount);
                model.addAttribute("cartItemList",cartItemList);
//            model.addAttribute("myList",myList);
                model.addAttribute("authenticated",authenticated);
                return "catalog/Main";
            }
        }

    }

    @GetMapping("/signOut")
    public String signOut(@SessionAttribute("accountState") boolean accountState,Model model){
        Account loginAccount = new Account();
//        List<Product> myList = null;
        boolean authenticated = false;
        model.addAttribute("account", loginAccount);
        model.addAttribute("accountState",!accountState);
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
        registerAccount.setPassword(Md5Util.encrypt(registerAccount.getPassword()));
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

    @GetMapping("/codeServlet")
    public void codeServlet(HttpServletResponse response) throws IOException {
        int width = 147;
        int height = 22;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setFont(new Font("宋体", Font.BOLD, 18));
        Random random = new Random();

        word = getWords();

        int x = 10;
        for (int i = 0; i < word.length(); i++) {
            graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            int jiaodu = random.nextInt(60) - 30;
            double theta = jiaodu * Math.PI / 180;
            char c = word.charAt(i);
            graphics2d.rotate(theta, x, 20);
            graphics2d.drawString(String.valueOf(c), x, 20);
            graphics2d.rotate(-theta, x, 20);
            x += 30;
        }
        graphics.setColor(getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
        }
        graphics.dispose();
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private String getWords(){
        String result=String.valueOf(new Random().nextInt(9000)+1000);
        System.out.println(result);
        return result;
    }
}
