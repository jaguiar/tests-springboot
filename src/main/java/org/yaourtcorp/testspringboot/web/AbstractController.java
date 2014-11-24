/**
 * 
 */
package org.yaourtcorp.testspringboot.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Optional;

/**
 * @author jaguiar
 *
 */
@RequestMapping(value="/yaoutcorp")
public abstract class AbstractController {
	
	protected <T> ResponseEntity<T> getResponseFromOptional(Optional<T> t) {
		if(t.isPresent()){
			return new ResponseEntity<T>(t.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
	}
}
