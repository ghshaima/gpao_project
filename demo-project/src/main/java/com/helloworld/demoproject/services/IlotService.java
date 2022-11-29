package com.helloworld.demoproject.services;

import java.util.List;

import com.helloworld.demoproject.entities.Ilot;

public interface IlotService {
    

    public Ilot save(Ilot entity);
    
    public Ilot update(Ilot entity);

    public List<Ilot> selectAll();

    public List<Ilot> selectAll(String sortField, String sort);

    public void remove(Long id);

    public Ilot getById(Long id);

    public Ilot findOne(String paramName, Object paramValue);

    public Ilot findOne(String[] paramNames, Object[] paramValues);

    public int findCountBy(String paramName, String paramValue);
}
