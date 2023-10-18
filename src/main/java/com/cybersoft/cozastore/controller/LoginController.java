package com.cybersoft.cozaStore.controller;

import com.cybersoft.cozaStore.payload.BaseResponse;
import com.cybersoft.cozaStore.payload.request.SignUpRequest;
import com.cybersoft.cozaStore.service.imp.LoginServiceImp;
import com.cybersoft.cozaStore.util.JwtHelper;
import com.google.gson.Gson;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();

    @Autowired
    private LoginServiceImp loginServiceImp;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password){
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        System.out.printf("kiemtra " + secretString); // kiểm tra key

        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authen);

        // Lấy danh sách role dã lưu từ Security Context Holder khi AuthenManager chứng thực thành công
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

        String jsonRole = gson.toJson(roles);

        String token = jwtHelper.generateToken(jsonRole);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Đăng nhập thành công");
        baseResponse.setData(token);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest){
        boolean isSuccess = loginServiceImp.insertUser(signUpRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Đăng kí thành công");
        baseResponse.setData(isSuccess);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}