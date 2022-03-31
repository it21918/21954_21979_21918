package com.springboot.app.Repository;

import com.springboot.app.Entity.Request;
import com.springboot.app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r WHERE r.sender.id = ?1")
    public List<Request> getStudentRequests( Long id) ;

    @Query("SELECT r FROM Request r WHERE r.id = ?1")
    public Request getRequestById( Long id) ;

    @Query("SELECT r FROM Request r WHERE r.receiver.id = ?1")
    public List<Request> getTeachersRequests( Long id) ;

}
