import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class Father implements Serializable {
    String fatherName;

    public Father(){
        this.fatherName = "this is father name ";
    }
    public Father(String fatherName){
        this.fatherName = "this is father name "+fatherName;
    }
}
