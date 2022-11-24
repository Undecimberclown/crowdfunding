package com.team2.crowdfunding.service;

import com.team2.crowdfunding.model.UserDTO;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {
    @Autowired
    private SqlSession session;

    private final String NAMESPACE = "UserMapper";

    public boolean validateUsername(UserDTO userDTO){
        return session.selectOne(NAMESPACE + ".validateUsername", userDTO) == null;
    }

    public void register(UserDTO userDTO){session.insert(NAMESPACE + ".register", userDTO);}

    public UserDTO auth(UserDTO userDTO) {return session.selectOne(NAMESPACE + ".auth", userDTO);}

    public void update(UserDTO userDTO){
        session.update(NAMESPACE + ".update", userDTO);
    }
    public UserDTO selectByUsername(String username){
        return session.selectOne(NAMESPACE + ".validateUsername");
    }
}
