package com.jonso.security.entity;

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

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    public AppUserDTO toDTO() {
        AppUserDTO dto = new AppUserDTO();
        BeanUtils.copyProperties(this, dto);
        dto.setPassword(null);
        return dto;
    }
}
