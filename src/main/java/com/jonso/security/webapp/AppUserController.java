package com.jonso.security.webapp;

import com.jonso.security.service.AppUserService;
import com.jonso.security.service.dto.AppUserDTO;
import com.jonso.security.service.dto.CommonResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public CommonResult<AppUserDTO> registerUser(@RequestBody @Valid AppUserDTO appUserDTO) {
        return new CommonResult<>(appUserService.saveUser(appUserDTO));
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }
}
