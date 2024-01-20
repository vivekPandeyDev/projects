package dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EducationDto {
    private String degreeType;
    private String institution;
    private int year;
    private  int percentage;

}
