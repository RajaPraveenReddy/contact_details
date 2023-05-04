package com.recykal.contactdata.repository;
import com.recykal.contactdata.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepo extends JpaRepository<ContactDetails,Long> {
    ContactDetails findByMobNo(long mobNo);
}