package com.recykal.contactdata.controller;
import com.recykal.contactdata.entity.ContactDetails;
import com.recykal.contactdata.service.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ContactDetailsController {

    @Autowired
    private ContactDetailsService service;

    @PostMapping("/addContact")
    public ContactDetails addContact(@RequestBody ContactDetails contact){
        return service.addContact(contact);
    }
    @GetMapping("/contacts")
    public List<ContactDetails> findContactDetails(){
        return service.getContactDetails();
    }
    @GetMapping("/contact/{id}")
    public ContactDetails findContactById(@PathVariable long id){
        return service.getDetailsById(id);
    }
    @GetMapping("/contactByMob/{mobNo}")
    public ContactDetails findByMobNo(@PathVariable long mobNo){
        return service.getDetailsByMobNo(mobNo);
    }

    @PutMapping("/update")
    public ContactDetails updateContact(@RequestBody ContactDetails cd){
        return service.updateContact(cd);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ContactDetails> deleteContact(@PathVariable long id){
        return service.deleteContact(id);
    }
}
