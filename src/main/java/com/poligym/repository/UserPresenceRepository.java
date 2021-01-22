package com.poligym.repository;

import com.poligym.models.UserPresence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPresenceRepository extends JpaRepository<UserPresence, Integer> {
    UserPresence findByUsersId(int id);
}
