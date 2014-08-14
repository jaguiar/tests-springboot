/**
 * 
 */
package org.yaourtcorp.testsangularjs.service;

import org.yaourtcorp.testsangularjs.bo.Add;

import com.google.common.base.Optional;

/**
 * @author jaguiar
 *
 */
public interface AddService {

	public Iterable<Add> listAdds();
	public void updateAdd(int id, String content);
	public Add createAdd(String name, String content);
	public void deleteAdd(int id);
	
	public Optional<Add> find(int id);
	public Optional<Add> find(String name);
}
