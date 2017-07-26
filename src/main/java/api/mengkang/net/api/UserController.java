package api.mengkang.net.api;

import api.mengkang.net.Request;
import api.mengkang.net.model.UserModel;

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

        if (!request.getParameters().containsKey("id")){
            return parameterNotFound("uid");
        }

        try{
            uid = Integer.parseInt(request.getParameters().get("id").get(0));
        }catch (NumberFormatException e){
            return parameterFormatError("uid");
        }

        return UserModel.getUserInfoById(uid);
    }
}
