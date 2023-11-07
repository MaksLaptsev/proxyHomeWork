package proxyhomework.entity;

import lombok.*;
import proxyhomework.psevdoSpring.annotations.Inject;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Number {
    @Inject
    private int number;
    @Inject
    private String name;
    @Inject
    private boolean isTrue;
    @Inject("ddd")
    private double doubleNumber;
}
