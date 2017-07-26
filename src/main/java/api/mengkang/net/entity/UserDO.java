package api.mengkang.net.entity;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class UserDO {
    private Integer id;
    private Integer age;
    private Integer sex;
    private String username;

    public UserDO() {
    }

    public UserDO(Integer id, Integer age, Integer sex, String username) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDO{" +
            "id=" + id +
            ", age=" + age +
            ", sex=" + sex +
            ", username='" + username + '\'' +
            '}';
    }
}
