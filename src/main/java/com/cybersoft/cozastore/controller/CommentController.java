package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.CommentRequest;
import com.cybersoft.cozastore.payload.response.CommentResponse;
import com.cybersoft.cozastore.service.imp.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImp commentServiceImp;

    @PostMapping("")
    public ResponseEntity<?> insertComment(@RequestParam String name, @RequestParam String email, @RequestParam String content, @RequestParam int idBlog){
        CommentRequest commentRequest1 = new CommentRequest();
        commentRequest1.setName(name);
        commentRequest1.setEmail(email);
        commentRequest1.setContent(content);
        commentRequest1.setIdBlog(idBlog);

        boolean isSuccess = commentServiceImp.insertComment(commentRequest1);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);

        if (isSuccess) {
            baseResponse.setMessage("Add new comment successfully!");
            baseResponse.setData(commentRequest1);
        } else {
            baseResponse.setMessage("Failed to comment color");
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @GetMapping("/{idBlog}")
    public ResponseEntity<?> getCommentByIdBlog(@PathVariable int idBlog){
        List<CommentResponse> commentResponses = commentServiceImp.getCommentByIdBlog(idBlog);

        if (commentResponses != null) {

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Get comment details by ID blog");
            baseResponse.setData(commentResponses);
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setMessage("Comment not found");
            errorResponse.setStatusCode(404);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

}
