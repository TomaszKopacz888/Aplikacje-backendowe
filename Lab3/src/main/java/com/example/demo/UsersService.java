package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    private final List<UsersEntity> users;
    private final PageData pd;
    private int id;

    public UsersService() {
        users = new ArrayList<>();
        this.pd = new PageData();
        this.id=0;
    }

    @PostConstruct
    private void onCreate() throws IOException {

            FileInputStream fis= new FileInputStream("src/main/java/com/example/demo/users.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line=br.readLine();
            while (line!=null){
                UsersEntity user=stringToUserEntity(line);
                this.users.add(this.id, user);
                this.id++;
                line=br.readLine();
            }

    }

    @PreDestroy
    private void onDestroy() throws IOException {

        FileOutputStream fos = new FileOutputStream("src/main/java/com/example/demo/users.txt");
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
        for (int i=0; i<this.id;i++){
            UsersEntity user=this.users.get(i);
            String line=userEntityToString(user);
            bw.write(line);
            bw.newLine();
        }
        bw.close();
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

    public Object getUserById(int id) {
        if (this.users.get(id)!=null) {
            return this.users.get(id);
        } else return "There is no user id: " + id;

    }

    public Object addUser(UsersEntity user){
        user.setId(this.id);
        this.users.add(this.id, user);
        this.id++;
        return user;
    }
    public Object updateUser(int id, UsersEntity user){
        if (this.users.get(id)!=null){
            user.setId(id);
            this.users.remove(id);
            this.users.add(id, user);
            return user;
        }
        else{
            return "There is no user id: "+id;
        }
    }

    public Object removeUser(int id){
        Map<String, Boolean> result=new HashMap<>();
        if (this.users.get(id)!=null){
            this.users.remove(id);
            result.put("result", true);
            return result;
        }
        else {
            result.put("result", false);
            return result;
        }
    }

    private String userEntityToString(UsersEntity user) throws JsonProcessingException {
        ObjectMapper om= new ObjectMapper();
        return om.writeValueAsString(user);
    }

    private UsersEntity stringToUserEntity(String str) throws JsonProcessingException {
        ObjectMapper om= new ObjectMapper();
        return om.readValue(str, UsersEntity.class);
    }
}
