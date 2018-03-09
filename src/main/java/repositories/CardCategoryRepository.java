/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author luca_universita
 */
@RepositoryRestResource(collectionResourceRel = "cardcategory", path = "cardscategory")
public interface CardCategoryRepository extends CrudRepository<entities.CardCategory, Long> {
    
}
