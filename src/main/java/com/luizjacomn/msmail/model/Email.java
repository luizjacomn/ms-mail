package com.luizjacomn.msmail.model;

import com.luizjacomn.msmail.enumeration.EmailStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Email implements Serializable {

    private static final long serialVersionUID = -1536081741034765749L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "owner_ref", nullable = false)
    private String ownerRef;

    @Column(name = "email_from", nullable = false)
    private String emailFrom;

    @Column(name = "email_to", nullable = false)
    private String emailTo;

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "email_status")
    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus;
}
