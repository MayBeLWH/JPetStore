package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Account findAccountByUsername(String username){
        return accountMapper.findAccountByUsername(username);
    }

    public Account findAccountByUsernameAndPassword(String username,String password){
        return accountMapper.findAccountByUsernameAndPassword(username,password);
    }

    public void insertAccount(Account account){
        accountMapper.insertAccount(account);
    }

    public void editAccount(Account account){
        accountMapper.updateAccount(account);
    }
}
