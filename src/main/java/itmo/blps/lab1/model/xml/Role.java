package itmo.blps.lab1.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "role")
public class Role {
    private String name;
    private List<String> privileges;

    @XmlElement
    public void setName(String name){
        this.name = name;
    }

    @XmlElementWrapper(name = "privilegeList")
    @XmlElement(name = "privilege")
    public void setPrivileges(List<String> privileges){
        this.privileges = privileges;
    }
}
