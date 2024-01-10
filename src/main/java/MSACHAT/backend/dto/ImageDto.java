package MSACHAT.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Integer id;
    private Integer semiId;
    private Integer postId;
    private String imageUrl;
}
