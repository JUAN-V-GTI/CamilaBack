package ACCES.CAMILA.CamilaBack.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ACCES.CAMILA.CamilaBack.Models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}