package org.launchcode.librarian.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.librarian.models.Entry;
import org.launchcode.librarian.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EntryDao extends CrudRepository<Entry, Integer> {
    
    public List<Entry> findByAuthor(int authorId);
    //public List<Entry> findByGenre(String genre);
    
    // allows you to search by various parameters
    public Entry findByUid(int uid);
    public Entry findByTitle(String title);
    
    public Entry findByCreator(String creator);
    
    int deleteByUid(int uid);
 }
	

