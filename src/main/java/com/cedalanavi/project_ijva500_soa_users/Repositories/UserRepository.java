package com.cedalanavi.project_ijva500_soa_users.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cedalanavi.project_ijva500_soa_users.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByIdUser(String idUser);
	
	User findByUsername(String username);
}
