package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(long id);
    User findUserByEmail(String email);

    User findUserByUsername(String username);
}
