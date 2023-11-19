package spring.crud.restful.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public class MemberDTO {

    private Long id;
    private String memberName;
    private BigDecimal salary;
    private Date birthday;
    private Long postCode;
    private String email;
    private String gender;
    private String address;
    private LocalDateTime createAt;
}
