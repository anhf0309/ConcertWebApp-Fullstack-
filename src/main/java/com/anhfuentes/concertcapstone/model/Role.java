package com.anhfuentes.concertcapstone.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String Name;
    @ManyToMany(mappedBy = "roles")
    List<User> users = new ArrayList<>();
    public Role(Long id, String Name, List<User> users) {
        this.id = id;
        this.Name = Name;
        this.users = users;
    }
}