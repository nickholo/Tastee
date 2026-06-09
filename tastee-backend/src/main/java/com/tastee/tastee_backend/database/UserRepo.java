package com.tastee.tastee_backend.database;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.tastee.tastee_backend.beans.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    

}
