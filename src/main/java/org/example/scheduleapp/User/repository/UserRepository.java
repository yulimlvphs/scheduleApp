package org.example.scheduleapp.User.repository;

import org.example.scheduleapp.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
