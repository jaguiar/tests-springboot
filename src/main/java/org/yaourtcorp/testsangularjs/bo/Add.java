/**
 * 
 */
package org.yaourtcorp.testsangularjs.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author jaguiar
 *
 */
@Entity
public class Add {

	public Add() {
	}

	public Add(int id, String name, String content) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
	}

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private String content;
	
	
	public int getId() {
		return id;
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
