package com.optum.AccountService.service;

import com.optum.AccountService.dto.AccountDTO;
import com.optum.AccountService.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AccountService {

    private final Map<String, Account> accounts = new HashMap<>();
private Account currentAccount;
    public AccountService()
    {
        Account account1 = new Account("Khushi","khushipawar636@gmail.com","password123");
        Account account2 = new Account("Hritwika","sarkarhritwika636@gmail.com","password127");
        Account account3 = new Account("Kritika","singhkritika36@gmail.com","password124");


        accounts.put(account1.getUsername(), account1);
        accounts.put(account2.getUsername(), account2);
        accounts.put(account3.getUsername(), account3);
    }
    public void createAccount(AccountDTO accountDTO)
    {
        Account account = new Account(accountDTO.getUsername(), accountDTO.getEmail(), accountDTO.getPassword() );
        accounts.put(account.getUsername(), account);
    }
    public Account getAccountByUsername(String username)
    {
        return accounts.get(username);
    }
    public List<Account> getAllAccounts()
    {
        return new ArrayList<>(accounts.values());
    }
public boolean updateAccount(AccountDTO accountDTO)
{
    Account account = accounts.get(accountDTO.getUsername());
    if(account!= null)
    {
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        return true;
    }
     return false;
}
public boolean deleteAccount(String username)
{
    return accounts.remove(username)!=null;
}

public Account getCurrentAccount()
{
    return currentAccount;
}
public void setCurrentAccount(Account account)
{
    this.currentAccount = account;
}
public  Account getDemoAccount()
{
    Account demoAccount = new Account("demo","demo@gmail.com","demopassword");
    return demoAccount;

}
public Account saveAccount(Account account)
{
    accounts.put(account.getUsername(),account);
    return account;
}
public Account registerAccount(Account account)
{
    accounts.put(account.getUsername(),account);
    return account;
}


//    public boolean login(Account account) {
//    }
}
