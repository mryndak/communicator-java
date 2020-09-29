package com.communicator.service.repository;

import com.communicator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from _users where firstname LIKE CONCAT(?1, '%') AND lastname LIKE CONCAT(?2, '%')",
            nativeQuery = true)
    List<User> findUsingNamePatternTypeA(String a, String b);
    @Query(value = "select * from _users where firstname LIKE CONCAT(?2, '%') AND lastname LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    List<User> findUsingNamePatternTypeB(String a, String b);
    @Query(value = "select * from _users where firstname LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    List<User> findUsingSingleNamePatternTypeA(String a);
    @Query(value = "select * from _users where lastname LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    List<User> findUsingSingleNamePatternTypeB(String a);
    @Query(value = "select * from _users where email LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    List<User> findUsingMailPattern(String a);
    User getByFirstnameAndLastnameAndEmail(String a, String b, String c);
    boolean existsByFirstnameAndLastnameAndEmail(String a, String b, String c);

}