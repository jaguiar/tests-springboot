/**
 * 
 */
package org.yaourtcorp.testsangularjs.service;

import static com.google.common.base.Optional.fromNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaourtcorp.testsangularjs.bo.Add;
import org.yaourtcorp.testsangularjs.repository.AddRepository;

import com.google.common.base.Optional;

/**
 * @author jaguiar
 *
 */
@Service
public class AddServiceImpl implements AddService {

	@Autowired
	private AddRepository addRepository;
	
	@Override
	public Iterable<Add> listAdds() {
		return addRepository.findAll();
	}

	@Override
	public void updateAdd(int id, String content) {
		Optional<Add> optionalAdd = find(id);
		if(optionalAdd.isPresent()){
			Add add = optionalAdd.get();
			add.setContent(content);
			addRepository.save(add);
		} // FIXME else ?
	}

	@Override
	public Add createAdd(String name, String content) {
		Add add = new Add();
		add.setName(name);
		add.setContent(content);
		return addRepository.save(add);
	}

	@Override
	public void deleteAdd(int id) {
		addRepository.delete(id);
	}

	@Override
	public Optional<Add> find(int id) {
		return fromNullable(addRepository.findOne(id));
	}

	@Override
	public Optional<Add> find(String name) {
		return fromNullable(addRepository.findByName(name));
	}

}
