package com.optum.AccountService.controller;

import com.optum.AccountService.dto.AccountDTO;
import com.optum.AccountService.model.Account;
import com.optum.AccountService.service.AccountService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

@Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody AccountDTO accountDTO)
    {
        accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping("/{username}")
    public ResponseEntity<AccountDTO> getAccountByUsername(@PathVariable String username)
    {
        Account account = accountService.getAccountByUsername(username);
        if(account!=null)
        {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUsername(account.getUsername());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setPassword(account.getPassword());
//            accountDTO.setAccount(account);
            return ResponseEntity.ok(accountDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
@GetMapping()
    public ResponseEntity<List<AccountDTO>>getAllAccounts()
    {
        List<Account> accounts = accountService.getAllAccounts();
        List<AccountDTO> accountDTOs = new ArrayList<>();
        for(Account account:accounts)
        {
            AccountDTO accountDTO = new AccountDTO(account.getUsername(),account.getEmail(),account.getPassword());
            accountDTOs.add(accountDTO);
        }
        return ResponseEntity.ok(accountDTOs);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> updateAccount(@PathVariable String username,@RequestBody AccountDTO accountDTO)
    {
        accountDTO.setUsername(username);
        boolean updated = accountService.updateAccount(accountDTO);
        if(updated)
        {
            return  ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{username}")
    public  ResponseEntity<Void> deleteAccount(@PathVariable String username)
    {
        boolean deleted = accountService.deleteAccount(username);
        if(deleted)
        {
             return  ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/current")
    public ResponseEntity<AccountDTO> getCurrentAccount()
    {
//        Account currentAccount = accountService.getCurrentAccount();
        if(accountService.getCurrentAccount()!=null)
        {
            String username = accountService.getCurrentAccount().getUsername();
            return getAccountByUsername(username);
//            AccountDTO accountDTO = AccountDTO.fromAccount(currentAccount);
//            return ResponseEntity.ok(accountDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/demo")
    public ResponseEntity<AccountDTO> getDemoAccount()
    {
        Account demoAccount = accountService.getDemoAccount();
        if(demoAccount!=null)
        {
            AccountDTO accountDTO = AccountDTO.fromAccount(demoAccount);
            return ResponseEntity.ok(accountDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

@PutMapping("/save")
    public ResponseEntity<AccountDTO> saveCurrentAccount(@RequestBody AccountDTO accountDTO)
    {
        Account currentAccount = accountService.getCurrentAccount();
        if(currentAccount!=null)
        {
            currentAccount.setUsername(accountDTO.getUsername());
            currentAccount.setEmail(accountDTO.getEmail());
            currentAccount.setPassword(accountDTO.getEmail());

            Account savedAccount = accountService.saveAccount(currentAccount);
            AccountDTO saveAccountDTO = AccountDTO.fromAccount(savedAccount);
            return ResponseEntity.ok(saveAccountDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
//    public void saveCurrentAccount(AccountDTO accountDTO)
//    {
//        Account currentAccount = accountService.getCurrentAccount();
//     if(currentAccount!=null)
//     {
//         currentAccount.setUsername(accountDTO.getUsername());
//         currentAccount.setEmail(accountDTO.getEmail());
//         currentAccount.setPassword(accountDTO.getPassword());
//     }
//
//    }
@PostMapping("/register")
    public ResponseEntity<AccountDTO> registerAccount(@RequestBody AccountDTO accountDTO)
    {
        Account account = new Account(accountDTO.getUsername(),accountDTO.getEmail(),accountDTO.getPassword());
        Account registeredAccount = accountService.registerAccount(account);
        AccountDTO registeredAccountDTO = AccountDTO.fromAccount(registeredAccount);
        return ResponseEntity.ok(registeredAccountDTO);
    }
@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountDTO accountDTO)
    {
        Account account = accountService.getAccountByUsername(accountDTO.getUsername());
        if(account!=null && account.getPassword().equals(accountDTO.getPassword()))
        {
            return ResponseEntity.ok("Login Successful");


        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }







}