package com.xinyuan.assist.dao;

import com.xinyuan.assist.DBHelper;
import com.xinyuan.assist.comm.DataBaseNames;
import com.xinyuan.assist.comm.DataConstant;
import com.xinyuan.assist.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户相关
 */
@Repository
public class UserDAO {

    @Autowired
    private DBHelper dbHelper;

    /**
     * LOG
     */
    private Logger logger = LoggerFactory.getLogger(UserDAO.class);

    /**
     * 保持用户信息
     *
     * @param userDO
     * @return
     */
    public boolean insert(UserDO userDO) {
        UserDO user = query(userDO.getUserName());
        if (user == null) {
            dbHelper.saveKV(DataBaseNames.DB_USER, DataConstant.MELON_USER_KEY, user);
            return true;
        }
        return false;
    }

    public UserDO query(String userName) {
        List<UserDO> users = query();
        return
                users.stream().filter(
                        userDO ->
                                userDO.getUserName().equals(userName)

                ).findFirst().orElse(null);
    }

    public List<UserDO> query() {
        Optional<List<UserDO>> users = dbHelper.getByK(DataBaseNames.DB_USER, DataConstant.MELON_USER_KEY);
        return users.orElse(new ArrayList<>());
    }


}
