package com.recykal.contactdata.service;
import com.recykal.contactdata.entity.ContactDetails;
import com.recykal.contactdata.exception.InvalidMobileNoException;
import com.recykal.contactdata.exception.ResourceNotFoundException;
import com.recykal.contactdata.exception.ValidationException;
import com.recykal.contactdata.repository.ContactDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactDetailsService {

    @Autowired
    private ContactDetailsRepo detailsRepo;

    public ContactDetails addContact(ContactDetails cd){
        validateUser(cd);
        return detailsRepo.save(cd);
    }

    private void validateUser(ContactDetails cd) throws ValidationException {
        if (cd.getFullName() == null || cd.getFullName().isEmpty()) {
            throw new ValidationException("Invalid full name.");
        }

        if (cd.getCompName() == null || cd.getCompName().isEmpty()) {
            throw new ValidationException("Company name is required.");
        }

        if (cd.getEmailId() == null || cd.getEmailId().isEmpty() || !cd.getEmailId().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ValidationException("Invalid email id.");
        }

        if (cd.getMobNo() == 0) {
            throw new ValidationException("Mobile number is required.");
        } else if (!isValidMobileNo(cd.getMobNo())) {
            throw new InvalidMobileNoException("Invalid mobile number.");
        }

        if (cd.getClientType() == null || cd.getClientType().isEmpty()) {
            throw new ValidationException("Client type is required.");
        }

        if (cd.getServReq() == null || cd.getServReq().isEmpty()) {
            throw new ValidationException("Service required is required.");
        }

        if (cd.getQueries() == null || cd.getQueries().isEmpty()) {
            throw new ValidationException("Queries/Message is required.");
        }
    }

    private boolean isValidMobileNo(long mobNo) {
        if (String.valueOf(mobNo).length() < 10) {
            return false;
        }
        return true;
    }
    public List<ContactDetails> getContactDetails(){
        return detailsRepo.findAll();
    }
    public ContactDetails getDetailsById(long id){
        return detailsRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+id));
    }

    public ContactDetails getDetailsByMobNo(long mobNo){
        return detailsRepo.findByMobNo(mobNo);
    }

    public ContactDetails updateContact(ContactDetails cd){
        ContactDetails existingContact = detailsRepo.findById(cd.getId()).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+cd.getId()));
        existingContact.setFullName(cd.getFullName());
        existingContact.setCompName(cd.getCompName());
        existingContact.setEmailId(cd.getEmailId());
        existingContact.setMobNo(cd.getMobNo());
        existingContact.setClientType(cd.getClientType());
        existingContact.setServReq(cd.getServReq());
        existingContact.setQueries(cd.getQueries());
        return detailsRepo.save(existingContact);
    }

//    public String deleteContact(long id){
//        detailsRepo.deleteById(id);
//        return "Contact removed for the given : "+id;
//    }

    public ResponseEntity<ContactDetails> deleteContact(long id){
        ContactDetails existingContact = detailsRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+id));
        detailsRepo.delete(existingContact);
        return ResponseEntity.ok().build();
    }
}

