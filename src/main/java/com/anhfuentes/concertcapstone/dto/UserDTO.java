package com.anhfuentes.concertcapstone.dto;

import com.anhfuentes.concertcapstone.validation.FieldMatch;
import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})

public class UserDTO {

    private Long id;

    @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed")
    private String firstName;

    @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed")
    private String lastName;

    @Column(unique = true)
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Required")
    private String password;

    @NotEmpty(message = "Required")
    private String confirmPassword;

    @AssertTrue
    private Boolean terms;
    public UserDTO(@NotEmpty @Pattern(regexp = "[A-Za-z]+$",
            message = "Only alphabetic allowed") String firstName, @Pattern(regexp =
            "[A-Za-z]+$", message = "Only alphabetic allowed") String lastName, @Email String
                           email, @NotEmpty(message = "Required") String password,
            @NotEmpty(message = "Required") String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}