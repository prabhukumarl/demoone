package com.datajpa.demo.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;

//convert obj to table and vice versa - In Wallet Repository
//@Repository - Not explicitly needed
public interface WalletRepository extends JpaRepository<Wallet,Integer> {
//SimpleJpaRepository
    //custom JPQL query by method name
    //SELECT wallet FROM Wallet wallet WHERE wallet.email =?1
    Optional<Wallet> findByEmail(String email);

    //custom JPQL
    @Query("SELECT wallet FROM Wallet wallet WHERE wallet.email =?1")
    Wallet searchForWalletByEmail(String email);

    //custom SQL
    @Query(value = "SELECT * From wallet WHERE email = ?1",nativeQuery = true)
    Wallet searchForWalletByEmailNative(String email);

    //Return more than one object. All the records from table is fetched.
    @Query("SELECT wallet FROM Wallet wallet")
    List<Wallet> getAllWallets();

}
