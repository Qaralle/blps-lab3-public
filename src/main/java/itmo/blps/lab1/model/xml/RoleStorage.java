package itmo.blps.lab1.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name="data")
public class RoleStorage {
    private List<Role> storage;

    public List<Role> getStorage(){
        return storage;
    }

    @XmlElementWrapper(name = "roleList")
    @XmlElementRef(name = "role")
    public void setStorage(List<Role> storage) {
        this.storage = storage;
    }
}
