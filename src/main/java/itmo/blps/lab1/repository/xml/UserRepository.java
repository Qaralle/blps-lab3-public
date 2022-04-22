package itmo.blps.lab1.repository.xml;

import itmo.blps.lab1.model.xml.User;
import itmo.blps.lab1.model.xml.UserStorage;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements XmlRepository<User>{
    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;
    private Marshaller jaxbMarshaller;

    private File data;
    private UserStorage storage;
    private User user;

    public UserRepository(){
        try {
            jaxbContext = JAXBContext.newInstance(UserStorage.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();;
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }


        data = new File("E:\\projects\\java\\lab1_blps\\src\\main\\resources\\user_cred.xml");
        load();
    }

    @SuppressWarnings("unchecked")
    private void load(){
        try {
            storage = (UserStorage) jaxbUnmarshaller.unmarshal(data);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        return storage.getStorage();
    }

    public Optional<User> getById(Long id){
        return storage.getStorage().stream().filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Optional<User> getByUsername(String username){
        return storage.getStorage().stream().filter(i -> i.getUsername().equals(username))
                .findFirst();
    }

    private void flush(){
        try {
            jaxbMarshaller.marshal(storage,data);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void save(User user){
        if (user.getId() == null){
            user.setId(storage.getLastId()+1);
            storage.setLastId(user.getId());
        }

        if (getById(user.getId()).isPresent() || getByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("User with such id or username is already exist");
        }
        storage.getStorage().add(user);
        flush();
    }
}
