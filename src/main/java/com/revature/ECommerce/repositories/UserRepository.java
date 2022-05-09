package com.revature.ECommerce.repositories;

import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.services.StorageManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository implements HibernateRepository<User>{
    private final StorageManager storageManager;
    private boolean running = false;
    private Session session;
    private String tableName;

    @Autowired
    public UserRepository(StorageManager storageManager){
        this.storageManager = storageManager;
    }


    @Override
    public void save(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        Query query = session.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();

        List<User> userList = new LinkedList<>();
        for(Object[] result : results) {
            User user = new User();
            user.setUserId((Integer)result[0]);
            user.setFirstName((String)result[1]);
            user.setLastName((String)result[2]);
            user.setPassword((String)result[3]);
            user.setUsername((String)result[4]);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User getById(Integer id) {
        String hql = "FROM User Where id = :id";
        TypedQuery<User> query = session.createQuery(hql, User.class);

        query.setParameter("id",id);

        User user = query.getSingleResult();

        return user;
    }

    public User getByUsername(String username){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

        Root<User> userTable = query.from(User.class);
        query.select(userTable).where(criteriaBuilder.equal(userTable.get("username"),username));

        return session.createQuery(query).getSingleResult();
    }

    public void start() {
        this.session = storageManager.getSession();
        running = true;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() { return running; }

    public String getTableName() { return tableName; }

    @Value("users")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
