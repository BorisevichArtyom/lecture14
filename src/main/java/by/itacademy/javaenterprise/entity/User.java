package by.itacademy.javaenterprise.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class User  {

	private static final long serialVersionUID = 1L;

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private BigDecimal balanceAmount;
	private Role role;


}