package com.nisum.endpointapi.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nisum.endpointapi.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Serializable>{
	
	List<User> findByName(@Param("name") String name);

	List<User> findByEmail(@Param("email") String email);
	
}

