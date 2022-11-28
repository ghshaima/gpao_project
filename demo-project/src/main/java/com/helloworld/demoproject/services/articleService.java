package com.helloworld.demoproject.services;
import com.helloworld.demoproject.entities.article;

public interface articleService {

   

        public Article save(Article entity);
    
        public Article update(Article entity);
    
        public List<Article> selectAll();
    
        public List<Article> selectAll(String sortField, String sort);
    
        public void remove(Long id);
    
        public Article getById(Long id);
    
        public Article findOne(String paramName, Object paramValue);
    
        public Article findOne(String[] paramNames, Object[] paramValues);
    
        public int findCountBy(String paramName, String paramValue);
    
    
}
