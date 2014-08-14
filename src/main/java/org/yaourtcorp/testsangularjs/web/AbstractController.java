/**
 * 
 */
package org.yaourtcorp.testsangularjs.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Optional;

/**
 * @author jaguiar
 *
 */
public abstract class AbstractController {
	
	protected <T> ResponseEntity<T> getResponseFromOptional(Optional<T> t) {
		if(t.isPresent()){
			return new ResponseEntity<T>(t.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
	}
}
