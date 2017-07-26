package api.mengkang.net.model;

import api.mengkang.net.entity.User;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class UserModel {
    public static User getUserInfoById(int uid){
        return new User(uid,18,1,"mengkang.zhou");
    }
}
