package api.mengkang.net.dao;

import api.mengkang.net.entity.User;
import api.mengkang.net.utils.mysql.MySelect;
import api.mengkang.net.utils.mysql.Mysql;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class UserDao {
    public static User getById(int id){
        String sql = "select id,age,sex,username from `user` where id=?";
        MySelect<User> mySelect = new MySelect<>(new User());
        return mySelect.get(sql,id);
    }

    public static int add(User user){
        return Mysql.insert("insert into user (age,sex,username) values (?,?,?)",
            user.getAge(),user.getSex(),user.getUsername()
        );
    }
}
