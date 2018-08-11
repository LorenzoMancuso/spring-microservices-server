/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
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
    entities.Users findOneByUsername(String username);

}
