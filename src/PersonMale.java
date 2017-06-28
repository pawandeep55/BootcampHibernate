import javax.persistence.Entity;

@Entity
public class PersonMale extends Person {
    String sex = "Male";

    @Override
    public String toString() {
        return "PersonMale{" +
                "sex='" + sex + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
