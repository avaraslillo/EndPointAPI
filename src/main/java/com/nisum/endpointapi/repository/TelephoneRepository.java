package com.nisum.endpointapi.repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nisum.endpointapi.model.Telephone;
import com.nisum.endpointapi.model.User;

@Repository("telephoneRepository")
public interface TelephoneRepository extends JpaRepository<Telephone,Serializable>{
    List<Telephone> findByUsuario(User usuario);

    //List<Telephone> findByUsuarioUUID(@Param("usuario_uuid") UUID usuario_uuid);
	
	//List<User> findByName(@Param("name") String name);
	
}
