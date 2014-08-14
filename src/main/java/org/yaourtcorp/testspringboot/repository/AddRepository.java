/**
 * 
 */
package org.yaourtcorp.testspringboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.yaourtcorp.testspringboot.bo.Add;

/**
 * @author jaguiar
 *
 */
public interface AddRepository extends CrudRepository<Add, Integer> {

	@Query
	Add findByName(String name);

}
