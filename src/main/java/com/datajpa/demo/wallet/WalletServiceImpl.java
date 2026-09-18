package com.datajpa.demo.wallet;


import com.datajpa.demo.transaction.Transaction;
import com.datajpa.demo.transaction.TransactionRepository;
import com.datajpa.demo.transaction.TransactionType;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Wallet registerNewWalletUser(Wallet newWallet) {
        //check if email already exists
        if (this.walletRepository.findByEmail(newWallet.getEmail()).isPresent()) {
            throw new WalletException("Account with give email already exist.");
        }
        ;
        newWallet.setCreatedOnDate(LocalDate.now());
        newWallet.setActive(true);
        return this.walletRepository.save(newWallet);
    }

    @Override
    public Wallet getUserWalletById(Integer walletId) {
        Optional<Wallet> foundWalletOpt = this.walletRepository.findById(walletId);
        if (foundWalletOpt.isPresent())
            return foundWalletOpt.get();
        //throw exception
        return null;
    }

    @Override
    public Wallet updateUserWallet(Wallet updateWallet) {
        return null;
    }

    @Override
    public Double addFundsToWalletById(Integer id, Double newBalance) {
        Wallet foundWallet = this.walletRepository.findById(id)
                .orElseThrow(() -> new WalletException("Wallet id does not exist"));
        //.orElse(null);
        Double oldBalance = foundWallet.getBalance();
        foundWallet.setBalance(oldBalance + newBalance);
        this.walletRepository.save(foundWallet);
        return foundWallet.getBalance();
    }

    @Override
    @Transactional
    public Double withdrawFundsFromWalletById(Integer id, Double amount) {
        Wallet foundWallet = this.walletRepository.findById(id).orElseThrow(() -> new WalletException("Wallet not found!"));
        //if balance <  amonut through exception
        if (foundWallet.getBalance() < amount) {
            throw new WalletException("Insufficient balance, available balance :" + foundWallet.getBalance());
        }
        Double currentBalance = foundWallet.getBalance();
        foundWallet.setBalance(currentBalance - amount);
        return foundWallet.getBalance();
    }

    @Override
    @Transactional
    public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) {
        Wallet fromWallet = this.walletRepository.findById(fromId).orElseThrow(() -> new WalletException("From account"));
        Wallet toWallet = this.walletRepository.findById(toId).orElseThrow(() -> new WalletException("To account"));
        if (fromWallet.getBalance() < amount) throw new WalletException("Insufficient balance in your account");
        Double fromBalance = fromWallet.getBalance();
        fromWallet.setBalance(fromBalance - amount);
        // using Setter
        Transaction debitTransaction = new Transaction(); //Transcient
        debitTransaction.setDate(LocalDate.now());
        debitTransaction.setAmount(amount);
        debitTransaction.setType(TransactionType.DEBIT);
        debitTransaction = this.transactionRepository.save(debitTransaction);
        fromWallet.getTransaction().add(debitTransaction);

        Double toBalance = toWallet.getBalance();
        toWallet.setBalance(toBalance + amount);

////Builder pattern
        Transaction creditTransaction = new Transaction();

        creditTransaction = this.transactionRepository.save(creditTransaction);
        toWallet.getTransaction().add(creditTransaction);
        return true;
    }


    @Override
    public Boolean deactivateWalletById(Integer id) {
        return null;
    }

    @Override
    public Boolean activateWalletById(Integer id) {
        return null;
    }
}
