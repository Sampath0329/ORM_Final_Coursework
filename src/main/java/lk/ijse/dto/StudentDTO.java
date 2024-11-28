package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String StudentId;
    private String name;
    private String contact;
    private String address;
    private String email;

}
