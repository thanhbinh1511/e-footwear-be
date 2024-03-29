package vn.edu.hcmuaf.fit.efootwearspringboot.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateDto {
    @NotEmpty(message = "Không được để trống")
    @Size(min = 5, max = 20, message = "Username tối thiểu 5 ký tự, tối đa 20 ký tự")
    private String username;

    @NotEmpty(message = "Không được để trống")
    @Size(min = 8, message = "Mật khẩu tối thiểu 8 ký tự")
    private String password;

    @NotEmpty(message = "Không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;
}
