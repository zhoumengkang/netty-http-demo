package api.mengkang.net.api;

import java.util.HashMap;

/**
 * @author zhoumengkang
 * @date 17/7/25.
 */
public class UserController {

    public Object get(){
        HashMap userInfo = new HashMap<>(2);
        userInfo.put("id",1);
        userInfo.put("name","mengkang");
        return userInfo;
    }
}
