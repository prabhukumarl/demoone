package com.datajpa.demo.jpa_mappings;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/accounts")
public class UserAccountController {

    private final AddressRepository addressRepository;
    private final PostRespository postRespository;
    // HAS-A dependency
    private final UserAccountRepository userAccountRepository;

    @GetMapping
    public String info(){
        return "Greetings";
    }

    @Autowired // It is not required here. Added for information
    public UserAccountController(AddressRepository addressRepository, PostRespository postRespository, UserAccountRepository userAccountRepository) {
        this.addressRepository = addressRepository;
        this.postRespository = postRespository;
        this.userAccountRepository = userAccountRepository;
    }


    @PostMapping
    public UserAccount registerUser(@RequestBody UserAccount userAccount) {
        return this.userAccountRepository.save(userAccount);
    }

    //below business logic should be in service layer
    @PostMapping("/{id}/address")
    //@Transactional    commended due to persisted
    public UserAccount registerUserAddress(@RequestBody Address newAddress, @PathVariable("id") Integer userId) {
        UserAccount foundUserAccount = this.userAccountRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Id does not exist"));
    //new object of Address is Transcient
        Address address = this.addressRepository.save(newAddress);
        foundUserAccount.setAddress(address);
        return this.userAccountRepository.save(foundUserAccount);
    }

    @PostMapping("{id}/posts")
    public UserAccount addUserPost(@RequestBody Post newPost, @PathVariable Integer id){
        UserAccount foundUserAccount = this.userAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("User Id does not exist"));
        Post savedPost = this.postRespository.save(newPost);
        foundUserAccount.getPosts().add(savedPost);
        return this.userAccountRepository.save(foundUserAccount);
    }

    @GetMapping("/{userId}")
    public UserAccount getUserById(@PathVariable("userId") Integer id){
        return this.userAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("User id does not exist again."));
    }
}
