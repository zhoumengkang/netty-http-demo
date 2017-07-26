import api.mengkang.net.Request;
import api.mengkang.net.api.UserController;
import org.junit.Test;

/**
 * @author zhoumengkang
 * @date 17/7/25.
 */
public class TestUserInfo {

    @Test
    public void getUserInfo(){
        Request request = null;
        UserController userController = new UserController(request);
        Object res = userController.get();
        System.out.println(res);
    }
}
