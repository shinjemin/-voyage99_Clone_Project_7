package com.clone.facebook.repository;

import com.clone.facebook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByMail (String mail);
    Optional<User> findByMailAndPassword (String mail, String pw_sha256);

    Optional<User> findByFamilyName (String familyName);
    Optional<User> findByGivenName (String givenName);
    Optional<User> findByYear(String year);
    Optional<User> findByMonth(String month);
    Optional<User> findByDay(String day);

}
