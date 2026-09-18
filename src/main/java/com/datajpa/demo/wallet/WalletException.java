package com.datajpa.demo.wallet;

//custom or user defined exception
public class WalletException extends RuntimeException {
    public WalletException(String message){
        super(message); //custom message
    }
}
