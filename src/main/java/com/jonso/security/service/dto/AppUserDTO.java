package com.jonso.security.service.dto;

import com.jonso.security.entity.AppUser;
import com.jonso.security.service.validator.ValidEmail;
import com.jonso.security.service.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AppUserDTO {
    private Long id;
    @ValidEmail
    private String email;

    @NotNull
    private String username;

    @ValidPassword
    private String password;

    public AppUser toEntity() {
        AppUser entity = new AppUser();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
