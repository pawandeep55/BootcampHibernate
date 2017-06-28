import javax.persistence.Entity;

@Entity
public class PersonFemale extends Person {
    String sex = "Female";

    @Override
    public String toString() {
        return "PersonFemale{" +
                "sex='" + sex + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
