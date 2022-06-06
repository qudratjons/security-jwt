package com.jonso.security.repository;

import com.jonso.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query(value = "SELECT exists(SELECT * FROM app_user u WHERE u.username = :username OR u.email = :email)", nativeQuery = true)
    Boolean existsUser(@Param("username") String username,
                       @Param("email") String email);

    Optional<AppUser> findByUsername(String username);
}
