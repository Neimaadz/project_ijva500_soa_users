package com.cedalanavi.projet_IJVA500_SOA_users.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedalanavi.projet_IJVA500_SOA_users.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
