/**
 * 
 */
package org.yaourtcorp.testsangularjs.web;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.yaourtcorp.testsangularjs.bo.Add;
import org.yaourtcorp.testsangularjs.service.AddService;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

/**
 * @author jaguiar
 *
 */
@RestController
@RequestMapping(value="/adds")
public class AddsController extends AbstractController {
	
	@Autowired
	private AddService addService;
	
	@RequestMapping(method=GET, produces="application/json;charset=UTF-8" )
	public List<Add> listAdds(){
		return Lists.newArrayList(addService.listAdds());
	}

	@RequestMapping(value="/{id}", method=GET, produces="application/json;charset=UTF-8" )
	public ResponseEntity<Add> findAdd(@PathVariable int id){
		Optional<Add> add = addService.find(id);
		return getResponseFromOptional(add);
	}

	@RequestMapping(value="/", method=GET, produces="application/json;charset=UTF-8" )
	public ResponseEntity<Add> findAddByName(@RequestParam String name){
		Optional<Add> add = addService.find(name);
		return getResponseFromOptional(add);
	}

	@RequestMapping(method=POST, consumes="application/json" )
	@ResponseStatus(HttpStatus.CREATED)
	public void newAdd(@RequestBody Add add){
		addService.createAdd(add.getName(), add.getContent());
	}

	@RequestMapping(value="/{id}", method=DELETE, consumes="application/json;charset=UTF-8" )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAdd(@PathVariable int id){
		addService.deleteAdd(id);
	}

	@RequestMapping(value="/{id}", method=PUT, consumes="application/json;charset=UTF-8" )
	public void updateAdd(@PathVariable int id,@RequestBody Add add){
		addService.updateAdd(id, add.getContent());
	}
	
}
