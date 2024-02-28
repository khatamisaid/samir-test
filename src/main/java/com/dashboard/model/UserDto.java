package com.dashboard.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Long role;
    private MultipartFile foto;
}
