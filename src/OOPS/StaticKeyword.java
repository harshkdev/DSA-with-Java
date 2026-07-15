package OOPS;

public class StaticKeyword {
    public static void main(String[] args) {
        Students s1 = new Students();
        s1.schoolName = "JMV";



        Students s3 = new Students();
        s3.schoolName = "ABC";

        Students s2 = new Students();
        System.out.println(s2.schoolName);
    }
}

class Students{
    String name;
    int roll;

    static String schoolName;

    //Setter
    void setName(String name){
        this.name = name;
    }

    //Getter
    String getName(){
        return this.name;
    }
}
