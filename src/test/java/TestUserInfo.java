import api.mengkang.net.api.UserController;
import org.junit.Test;

/**
 * @author zhoumengkang
 * @date 17/7/25.
 */
public class TestUserInfo {

    @Test
    public void getUserInfo(){
        UserController userController = new UserController();
        Object res = userController.get();
        System.out.println(res);
    }
}
