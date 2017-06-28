import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue
    Integer id;
    String address;

//    @ManyToMany(mappedBy = "addressList")
//    List<Person> personList = new ArrayList<>();

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
