package pe.roalwh.exSpringBoot.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import pe.roalwh.exSpringBoot.model.User;
import pe.roalwh.exSpringBoot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public User loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException((email)));
  }
}