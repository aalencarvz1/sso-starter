package com.oiis.sso_starter.database.repositories.sso;

import com.oiis.sso_starter.database.entities.sso.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends BaseSsoRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
