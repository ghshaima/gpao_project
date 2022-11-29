package com.helloworld.demoproject.services;
import java.util.List;

import com.helloworld.demoproject.entities.Ilot;
import com.helloworld.demoproject.entities.Machine;

public interface MachineService {
    
    public Machine save(Ilot entity);
    
    public Machine update(Ilot entity);

    public List<Machine> selectAll();

    public List<Machine> selectAll(String sortField, String sort);

    public void remove(Long id);

    public Machine getById(Long id);

    public Machine findOne(String paramName, Object paramValue);

    public Machine findOne(String[] paramNames, Object[] paramValues);

    public int findCountBy(String paramName, String paramValue);
}




