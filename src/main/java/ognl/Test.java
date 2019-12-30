package ognl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        /*User user1 = new User();
        user1.setId(1);
        user1.setName("firer");
        // 如上例创建一些 User
        List users = new ArrayList();
        users.add(user1);
        // 将创建的 User 添加到 List 中
        Department dep = new Department();
        dep.setUsers(users);
        List names = (List)Ognl.getValue("users.{name}", dep);
        List ids = (List)Ognl.getValue("users.{? #this.id > 1}", dep);*/



        User user1 = new User(1,"elizabeth",19);
        User user2 = new User(2,"hello",13);
        User user3 = new User(3,"world",40);
        User user4 = new User(4,"graph",21);
        List<User> ls = new ArrayList();
        ls.add(user1);
        ls.add(user2);
        ls.add(user3);
        ls.add(user4);

        People people = new People();
        people.setUsers(ls);

        try {
            OgnlContext context = new OgnlContext(null,null,new DefaultMemberAccess(true));

            System.out.println(Ognl.getValue("users.{? #this.id >1}" ,context, people));

        } catch (OgnlException e) {
            e.printStackTrace();
        }


    }
}
