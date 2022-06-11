package com.jonso.security.service;

import com.jonso.security.entity.AppUser;
import com.jonso.security.entity.enums.UserStatus;
import com.jonso.security.error.AppErrors;
import com.jonso.security.error.AppException;
import com.jonso.security.repository.AppUserRepository;
import com.jonso.security.service.dto.AppUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserDTO saveUser(AppUserDTO appUserDTO) {
        if (!appUserRepository.existsUser(appUserDTO.getUsername(), appUserDTO.getEmail())){
            AppUser appUser = appUserDTO.toEntity();
            appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
            appUser.setUserStatus(UserStatus.NEED_TO_CONFIRM);
            return appUserRepository.save(appUser).toDTO();
        }

        AppUser appUser = appUserRepository.findByUsername(appUserDTO.getUsername()).get();
        if (appUser.getUserStatus() == UserStatus.NEED_TO_CONFIRM)
            throw new AppException(AppErrors.NEED_TO_CONFIRM);

        throw new AppException(AppErrors.USER_BLOCKED);
    }
}
