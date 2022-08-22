package com.example.bibliosystem;

import javax.persistence.*;
import java.util.Collection;

@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(int id, String name, Collection<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Role(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
