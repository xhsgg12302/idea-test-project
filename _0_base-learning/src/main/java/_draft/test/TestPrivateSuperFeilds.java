package _draft.test;

public class TestPrivateSuperFeilds {

    public static void main(String[] args) {
        Son son = new Son();


        System.out.println();

    }
}


class Father{
    private String name;
    public  Integer age;

    public Father(){
        this.name="1";
    }

}

class Son extends Father{

    public void te(){
    }

}