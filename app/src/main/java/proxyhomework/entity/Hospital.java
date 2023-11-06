package proxyhomework.entity;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Hospital {
    private int id;
    private String name;
    private String address;
}
