package api.mengkang.net.api;

import java.util.HashMap;

import api.mengkang.net.Request;

/**
 * @author zhoumengkang
 * @date 17/7/25.
 */
public class UserController extends BaseController {

    public UserController(Request request) {
        super(request);
    }

    public Object get(){

        int uid;

        HashMap userInfo = new HashMap<>(2);

        if (!request.getParameters().containsKey("id")) {
            return userInfo;
        }

        try{
            uid = Integer.parseInt(request.getParameters().get("id").get(0));
        }catch (NumberFormatException e){
            return userInfo;
        }

        userInfo.put("id",uid);
        userInfo.put("name","mengkang");
        return userInfo;
    }
}
