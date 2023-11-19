package spring.crud.restful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.text.ParseException;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @NotNull(message = "name is required")
    @Column(name = "name", nullable = false)
    private String memberName;
    @Column(name = "salary")
    private BigDecimal salary;
    @NotNull(message = "birthday is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @NotNull(message = "Post code is required")
    @Min(value = 10000, message = "Post code should be at least 5 digits")
    @Max(value = 99999, message = "Post code should be at most 5 digits")
    @Column(name = "post_code", nullable = false)
    private Long postCode;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false)
    private String email;
    @NotNull(message = "gender is required")
    @Column(name = "gender", nullable = false)
    private String gender;
    @NotNull(message = "address is required")
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
    @Column(name = "block")
    private boolean block;
    @Column(name = "createAt")
    private LocalDateTime createAt;

}
