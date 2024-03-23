package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findRoleByName(String role);
    @Query(value = "select * from roles where roles.id= (select role_id from users_roles where user_id = :id)", nativeQuery = true)
    public List<Role> findRoleByUser(@Param("id") long id);
}