package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Manger;
import org.csu.mypetstore.persistence.MangerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MangerService {

    @Autowired
    private MangerMapper mangerMapper;

    public Manger Login(String manger,String password){
        return mangerMapper.login(manger, password);
    }

    public void changeInformation(Manger manger){
        mangerMapper.changeInformation(manger);
    }

    public void changePassword(String account,String password){
        mangerMapper.changePassword(account, password);
    }
}
