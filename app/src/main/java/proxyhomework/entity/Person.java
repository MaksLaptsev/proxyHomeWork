package proxyhomework.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class Person {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;

}
