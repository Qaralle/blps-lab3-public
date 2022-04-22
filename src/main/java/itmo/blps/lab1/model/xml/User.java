package itmo.blps.lab1.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private List<String> roles;


    @XmlAttribute
    public void setId(Long id){
        this.id = id;
    }

    @XmlElement
    public void setUsername(String username){
        this.username = username;
    }

    @XmlElement
    public void setPassword(String password){
        this.password = password;
    }

    @XmlElementWrapper(name = "roleList")
    @XmlElement(name = "role")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
