/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author lorenzo
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "user")
public interface UserRepository extends JpaRepository<entities.Users, Long> {
    @Override
    entities.Users findOne(Long idUser);   
    Long countByName(String name);
    ArrayList<entities.Users> findDistinctUsersByName(String name);
    
    @Query( "select u from Users u where u.username=:username")
    entities.Users findOneByUsername(@Param("username")String username);
    
    entities.Users findOneByEmail(String email);


}
