package itmo.blps.lab1.repository.xml;

import itmo.blps.lab1.model.xml.Role;
import itmo.blps.lab1.model.xml.RoleStorage;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Component
public class RoleRepository implements XmlRepository<Role>{
    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;
    private Marshaller jaxbMarshaller;

    private File data;

    private RoleStorage storage;
    private Role role;

    public RoleRepository(){
        try {
            jaxbContext = JAXBContext.newInstance(RoleStorage.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();;
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }
        ClassLoader classLoader = getClass().getClassLoader();

        data = new File("E:\\projects\\java\\lab1_blps\\src\\main\\resources\\roles_data.xml");
        load();
    }

    private void load(){
        try {
            storage = (RoleStorage) jaxbUnmarshaller.unmarshal(data);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> findAll() {
        return storage.getStorage();
    }

    @Override
    public Optional<Role> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> getByUsername(String username) {
        return storage.getStorage().stream().filter(i -> i.getName().equals(username))
                .findFirst();
    }

    private void flush(){
        try {
            jaxbMarshaller.marshal(storage,data);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void save(Role role) {
        if (getByUsername(role.getName()).isPresent()) {
            throw new IllegalStateException("ROLE with such name is already exist");
        }

        storage.getStorage().add(role);
        flush();
    }
}
