package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    private String studentId;
    private String name;
    private String contact;
    private String address;
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Registration> registrations;

    @ManyToOne
    private User user;

    public Student(String studentId, String name, String contact, String address, String email) {
        this.studentId = studentId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.email = email;
    }
}
