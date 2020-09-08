package vladyslav.shuhai.psyhology.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    @Override
    public String toString() {
        return "\nІм'я: " + firstName + " - " +
                 phoneNumber;
    }

    private String secondName;
    private String email;
    private String phoneNumber;

    @ManyToMany
    private List<Event> events = new ArrayList<>();
}
