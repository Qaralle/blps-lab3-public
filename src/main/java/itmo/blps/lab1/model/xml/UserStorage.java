package itmo.blps.lab1.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name="data")
public class UserStorage {
    private List<User> storage;
    private Long lastId;

    public List<User> getStorage(){
        return storage;
    }

    @XmlAttribute(name = "lastId")
    public void setLastId(Long id){
        this.lastId = id;
    }

    @XmlElementWrapper(name = "userList")
    @XmlElementRef(name = "user")
    public void setStorage(List<User> storage) {
        this.storage = storage;
    }
}

