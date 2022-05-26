package com.revature.ECommerce.beans.repositories;


import com.revature.ECommerce.entities.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMessageRepository extends JpaRepository<LogMessage, Integer> {
            /*
    Extending the CrudRepository should implement the following CRUD operations for us:
    save()
    saveAll()
    findById()
    existsById()
    findAll()
    findAllById()
    count()
    deleteById()
    delete()
    deleteAll()
     */
}
