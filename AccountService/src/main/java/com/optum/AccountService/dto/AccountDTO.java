package com.optum.AccountService.dto;

import com.optum.AccountService.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDTO {
    private String username;
    private String email;
    private String password;

    public AccountDTO(String username, String email,String password) {
        this.username = username;
        this.email =email;
        this.password=password;
    }

    public AccountDTO() {

    }
public static AccountDTO fromAccount(Account account)
{
    return new AccountDTO(account.getUsername(), account.getEmail(), account.getPassword());
}
public static List<AccountDTO> fromAccountList(List<Account> accountList)
{
    List<AccountDTO> accountDTOList = new ArrayList<>();
    for(Account account:accountList)
    {
        accountDTOList.add(fromAccount(account));

    }
    return accountDTOList;
}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount(Account account)
    {
        this.username=account.getUsername();
        this.email=account.getEmail();
        this.password=account.getPassword();
    }
}
