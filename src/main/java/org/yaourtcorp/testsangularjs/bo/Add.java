/**
 * 
 */
package org.yaourtcorp.testsangularjs.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author jaguiar
 *
 */
@Entity
public class Add {

	@Id	
	private int id;
	@Column
	private String name;
	@Column
	private String content;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
