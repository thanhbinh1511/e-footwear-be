package vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductImageDto {
    private Long id;
    private String imageURL;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private ZonedDateTime createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private ZonedDateTime updateAt;
    private EntityState state;
}
