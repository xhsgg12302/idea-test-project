package _framework.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {


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
            //OgnlContext context1 = new OgnlContext(null,null,new DefaultMemberAccess(true));
            OgnlContext context = new OgnlContext();
            System.out.println(Ognl.getValue("users.{? #this.id >1}" ,context, people));

        } catch (OgnlException e) {
            e.printStackTrace();
        }


    }
}
