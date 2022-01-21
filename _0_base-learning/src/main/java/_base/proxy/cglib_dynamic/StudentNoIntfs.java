package _base.proxy.cglib_dynamic;


public class StudentNoIntfs {

    private String name;
    
    public StudentNoIntfs(String name) {
        this.name = name;
    }

    public StudentNoIntfs(){}

    public void sayHello(String string) {
        System.out.println(string);
    }

}
