package com.datajpa.demo.wallet;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/wallets")
@CrossOrigin(origins = "http://localhost:4200/")
public class WalletController {

    @Autowired
    private WalletService walletService;



    @GetMapping
    public String info() {
        return "Wallet App Running!";
    }

    //Rest API OR one end point created
    //Register new wallet user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Wallet registerNewWalletUser(@Valid @RequestBody Wallet newWallet) {
        return this.walletService.registerNewWalletUser(newWallet);
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable("id") Integer walletId) {
        return this.walletService.getUserWalletById(walletId);
    }

    //PUT Or POST or PATCH
    @PatchMapping
    public Double addFundsToWalletById(@RequestBody WalletDto walletDto) {
        try {
            return this.walletService
                    .addFundsToWalletById(walletDto.getToId(), walletDto.getAmount());
        } catch (WalletException e) {
            throw e;
        }

    }



    // withdraw funds
    @PatchMapping("/withdraw")
    public Double withdrawFundsFromWalletById(@RequestBody WalletDto walletDto) {
        return this.walletService
                .withdrawFundsFromWalletById(walletDto.getFromId(), walletDto.getAmount());
    }

    @PatchMapping("/transfer")
    public Boolean transferFund(@RequestBody WalletDto walletDto) {
        return this.walletService.fundTransfer(walletDto.getFromId(),walletDto.getToId(), walletDto.getAmount());
    }

}

