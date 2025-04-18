import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
//@NoArgsConstructor
@Slf4j
@ToString(callSuper = true)
public class Son extends Father  {

     String sonName;

    public Son(String fatherName,String sonName)
    {
        super(fatherName);
        this.sonName = "this is son name:"+sonName;
    }

    public static void main(String[] args) {
        Son son = new Son("xxy","xiazixin");
        System.out.println(son.toString());
    }
}
