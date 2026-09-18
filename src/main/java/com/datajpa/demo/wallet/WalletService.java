package com.datajpa.demo.wallet;

public interface WalletService {
    //CRUD
    //1. Add funds 2.Withdraw 3.Transfer 4.Account activate/deactivate
    //Wallet createNewWallet(Wallet newWallet);
    Wallet registerNewWalletUser(Wallet newWallet);
    Wallet getUserWalletById(Integer walletId);
    Wallet updateUserWallet(Wallet updateWallet);

    //1 add funds
    Double addFundsToWalletById(Integer id,Double amount);
    //2 withdraw
    Double withdrawFundsFromWalletById(Integer id,Double amount);
    //3 Transfer
    Boolean fundTransfer(Integer fromId, Integer toId, Double amount);
    //4 Activate
    Boolean deactivateWalletById(Integer id);
    Boolean activateWalletById(Integer id);
}
