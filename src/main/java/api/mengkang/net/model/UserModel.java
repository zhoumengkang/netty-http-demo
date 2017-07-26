package api.mengkang.net.model;

import api.mengkang.net.dao.UserDao;
import api.mengkang.net.entity.User;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class UserModel {
    public static User getUserInfoById(int uid){
        return UserDao.getById(uid);
    }
}
