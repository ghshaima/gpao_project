package com.helloworld.demoproject.services;

import java.util.List;

import com.helloworld.demoproject.entities.Of;

public interface OfService {
   
    public Of save(Of entity);
    
    public Of update(Of entity);

    public List<Of> selectAll();

    public List<Of> selectAll(String sortField, String sort);

    public void remove(Long id);

    public Of getById(Long id);

    public Of findOne(String paramName, Object paramValue);

    public Of findOne(String[] paramNames, Object[] paramValues);

    public int findCountBy(String paramName, String paramValue);
    

}
