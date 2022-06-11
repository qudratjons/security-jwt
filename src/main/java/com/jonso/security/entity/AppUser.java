package com.jonso.security.entity;

import com.jonso.security.entity.enums.UserStatus;
import com.jonso.security.service.dto.AppUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 55)
    private String email;

    @Column(unique = true, nullable = false, length = 55)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 20)
    private UserStatus userStatus;

    public AppUserDTO toDTO() {
        AppUserDTO dto = new AppUserDTO();
        BeanUtils.copyProperties(this, dto);
        dto.setPassword(null);
        return dto;
    }
}
