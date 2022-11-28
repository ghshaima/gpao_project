package com.helloworld.demoproject.services;
package com.helloworld.demoproject.entities.machine;

public interface machineService {
    
    public machine save(ilot entity);
    
    public machine update(ilot entity);

    public List<machine> selectAll();

    public List<machine> selectAll(String sortField, String sort);

    public void remove(Long id);

    public machine getById(Long id);

    public machine findOne(String paramName, Object paramValue);

    public machine findOne(String[] paramNames, Object[] paramValues);

    public int findCountBy(String paramName, String paramValue);
}



}
