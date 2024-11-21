package com.project.testing1.firstjob.Repo;

import com.project.testing1.firstjob.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);// No need for add method here
    Optional<User> findOneByEmailAndPassword(String email, String password);
}
