package com.burgerbuilder.backend.Controller;

import com.burgerbuilder.backend.DTO.Request.EmailValidationRequest;
import com.burgerbuilder.backend.DTO.Request.ResetPasswordRequest;
import com.burgerbuilder.backend.DTO.Request.ResetPasswordValidationRequest;
import com.burgerbuilder.backend.DTO.Request.UpdatePasswordRequest;
import com.burgerbuilder.backend.DTO.Response.UserResponse;
import com.burgerbuilder.backend.Model.User;
import com.burgerbuilder.backend.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/current","/current/"})
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal User principal){
        return new ResponseEntity<>(new UserResponse(principal), HttpStatus.OK);
    }


    @PutMapping({"/password","/password"})
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordRequest request,
                                            @AuthenticationPrincipal User user){
        return userService.updatePassword(request,user);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest request){
        userService.resetPassword(request);
    }

    @PostMapping("/reset-password-validation")
    public ResponseEntity<?> validatePasswordReset(@Valid @RequestBody ResetPasswordValidationRequest request){
        return userService.validatePasswordReset(UUID.fromString(request.getToken()),request.getNewPassword());
    }

    @GetMapping("/email-verification")
    public ResponseEntity<?> sendEmailVerificationToken(@AuthenticationPrincipal User user){
        return userService.sendEmailVerificationToken(user);
    }

    @PostMapping("/email-token-verification")
    public ResponseEntity<?> verifyEmailAddressToken(@Valid @RequestBody EmailValidationRequest request){
        return userService.verifyEmailAddressToken(request);
    }

}
