package project.plants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String pcondition;
    public String getCondition(){ return pcondition; }
    public void setCondition(String pcondition) {
        this.pcondition = pcondition;
    }

}
