package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    Account findAccountByUsername(String username);

    Account findAccountByUsernameAndPassword(String username,String password);

    void insertAccount(Account account);

    void updateAccount(Account account);

    void insertProfile(Account account);

    void insertSignOn(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);
}
