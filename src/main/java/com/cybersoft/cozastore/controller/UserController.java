package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.response.UserProfileResponse;
import com.cybersoft.cozastore.payload.response.UserResponse;
import com.cybersoft.cozastore.service.imp.UserServiceImp;
import com.cybersoft.cozastore.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("")
    public ResponseEntity<?> getALlUser() {

        List<UserResponse> userResponseList = userServiceImp.getAllUser();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all user");
        baseResponse.setData(userResponseList);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUserById(@PathVariable int idUser) {
        boolean isDeleted = userServiceImp.deleteUserById(idUser);
        if (isDeleted) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(200);
            baseResponse.setMessage("User deleted successfully");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Product not fount or or unable to delete", HttpStatus.OK);
        }
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> updateUserById(@PathVariable int idUser,
                                            @RequestParam String userName,
                                            @RequestParam int idRole) {
        boolean isUpdated = userServiceImp.updateUserById(idUser, userName, idRole);

        if (isUpdated) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(200);
            baseResponse.setMessage("User updated successfully");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(404);
            baseResponse.setMessage("User not found or unable to update");
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable int idUser){
        List<UserResponse> userResponse = userServiceImp.getUserById(idUser);

        if (userResponse != null) {

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Get user details by ID");
            baseResponse.setData(userResponse);
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setMessage("User not found");
            errorResponse.setStatusCode(404);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/decode-token")
    public ResponseEntity<?> decodeToken(@RequestParam String token) {
        try {
            int userId = jwtHelper.getUserIdFromToken(token);
            // Có thể thêm các thông tin khác cần thiết vào đây
            return new ResponseEntity<>(new UserProfileResponse(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }
    }


}
