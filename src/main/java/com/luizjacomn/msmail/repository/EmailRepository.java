package com.luizjacomn.msmail.repository;

import com.luizjacomn.msmail.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> { }
