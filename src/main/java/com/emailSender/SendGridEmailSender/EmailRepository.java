package com.emailSender.SendGridEmailSender;

import com.emailSender.SendGridEmailSender.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
