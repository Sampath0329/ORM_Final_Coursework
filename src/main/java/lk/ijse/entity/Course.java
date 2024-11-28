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
@ToString@Entity
public class Course {
    @Id
    private String CourseId;
    private String CourseName;
    private String Duration;
    private double programFee;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Registration> registrations;


    public Course(String CourseId, String CourseName, String Duration, double programFee) {
        this.CourseId = CourseId;
        this.CourseName = CourseName;
        this.Duration = Duration;
        this.programFee = programFee;
    }


}
