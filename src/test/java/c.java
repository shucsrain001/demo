import lombok.Data;

public class c {

    @Data
    static class A {
        String aVar = "A";

    }
    @Data
    static class B extends A {
        String bVar = "B";
    }

    public static void main(String[] args) {

        System.out.println(new c.B()); // 输出：A(aVar=A)
    }

}
