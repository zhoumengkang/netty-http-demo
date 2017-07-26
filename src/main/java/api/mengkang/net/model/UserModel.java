package api.mengkang.net.model;

import api.mengkang.net.entity.UserDO;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class UserModel {
    public static UserDO getUserInfoById(int uid){
        return new UserDO(uid,18,1,"mengkang.zhou");
    }
}
