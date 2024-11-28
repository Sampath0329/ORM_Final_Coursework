package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    private String userName;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String contact;
    private String mail;
    private String pw;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Student> students;


    public User(String userName, String firstName, String lastName, String nic, String address, String contact, String mail, String pw) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.address = address;
        this.contact = contact;
        this.mail = mail;
        this.pw = pw;
    }
}
