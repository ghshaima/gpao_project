package com.helloworld.demoproject.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

public class  Category implements Serializable  {

    
	private Long idCategory;
	
	private String codeCategorie;
	
	private String Designation;
	
	@OneToMany (mappedBy="category")
	
	private List<Article> article;
	
	public Category() {
		
	}

	public Long getIdArticle() {
		return idCategory;
	}

	public void setIdArticle(Long idCategory) {
		this.idCategory = idCategory;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}
    
}
