package com.recykal.contactdata.entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Sust_contact_details")
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String compName;
    @Column(unique = true,nullable = false)
    private String emailId;
    @Column(unique = true,nullable = false)
    private long mobNo;
    @Column(nullable = false)
    private String clientType;
    @Column(nullable = false)
    private String servReq;
    @Column(nullable = false)
    private String queries;

}

