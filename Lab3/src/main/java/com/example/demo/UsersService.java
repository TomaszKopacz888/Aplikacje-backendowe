package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private final List<UsersEntity> users;
    private final PageData pd;
    private int id;

    public UsersService() {
        users = new ArrayList<>();
        users.add(0, new UsersEntity(0, "Mark", 21));
        users.add(1, new UsersEntity(1, "Mathew", 27));
        users.add(2, new UsersEntity(2, "Thomas", 33));
        this.pd = new PageData();
        this.id=3;
    }

    public void setPageNumber(int pn) {
        this.pd.setPageNumber(pn);
    }

    public void setPageSize(int ps) {
        this.pd.setPageSize(ps);
    }

    public Object getPageData() {
        this.pd.setUsers(this.users);
        return this.pd;
    }
    //public void createUser()

    public Object getUserById(int id) {
        if (this.users.get(id)!=null) {
            return this.users.get(id);
        } else return "There is no user id " + id;

    }
}
