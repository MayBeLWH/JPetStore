package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    public Account findAccountByUsername(String username);

    public Account findAccountByUsernameAndPassword(String username,String password);

    public void insertAccount(Account account);

    public void updateAccount(Account account);
}
