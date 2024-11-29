package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
public class Registration {
    @Id
    private String registrationId;
    private Date date;
    private double upfrontPayment;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Payment> payments;

    public Registration(String registrationId, Date date, double upfrontPayment, Student student, Course course){
        this.registrationId = registrationId;
        this.date = date;
        this.upfrontPayment = upfrontPayment;
        this.student = student;
        this.course = course;
    }
}
