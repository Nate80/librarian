package org.launchcode.librarian.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.librarian.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    public User findByUid(int uid);
    
    public List<User> findAll();
    
    
    public User findByUsername(String username);

}
