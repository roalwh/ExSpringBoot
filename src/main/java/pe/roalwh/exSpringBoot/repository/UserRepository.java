package pe.roalwh.exSpringBoot.repository;

import java.util.Optional;

import pe.roalwh.exSpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

}
