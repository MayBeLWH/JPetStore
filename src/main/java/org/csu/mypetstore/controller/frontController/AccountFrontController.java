package org.csu.mypetstore.controller.frontController;

import org.csu.mypetstore.domain.Manger;
import org.csu.mypetstore.domain.ResponseTemplate;
import org.csu.mypetstore.service.MangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class AccountFrontController {

    @Autowired
    private MangerService mangerService;

    @GetMapping("")
    @ResponseBody
    public ResponseTemplate login(String account,String password){
        Manger manger=mangerService.Login(account, password);

        ResponseTemplate responseTemplate = new ResponseTemplate();
        if (manger == null){
            responseTemplate.setJudgement(false);
        }else{
            responseTemplate.setJudgement(true);
            responseTemplate.setData(manger);
        }
        return responseTemplate;
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseTemplate userInfo(String account,String username,String password,
                                     String city,String address,String telephone,String power){
        Manger manger=new Manger();
        manger.setAccount(account);
        manger.setPassword(password);
        manger.setUsername(username);
        manger.setCity(city);
        manger.setAddress(address);
        manger.setPower(power);
        manger.setTelephone(telephone);

        mangerService.changeInformation(manger);

        Manger newManger = mangerService.Login(manger.getAccount(),manger.getPassword());

        ResponseTemplate responseTemplate = new ResponseTemplate();
        if (newManger == null){
            responseTemplate.setJudgement(false);
        }else{
            responseTemplate.setJudgement(true);
            responseTemplate.setData(newManger);
        }
        return responseTemplate;
    }

    @GetMapping("/userPass")
    @ResponseBody
    public ResponseTemplate userPass(String account,String password){
        mangerService.changePassword(account, password);

        ResponseTemplate responseTemplate=new ResponseTemplate();
        responseTemplate.setJudgement(true);

        return  responseTemplate;
    }
}
