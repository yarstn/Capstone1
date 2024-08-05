// CommentController.java
package com.example.capstone.Controller;

import com.example.capstone.Model.Comment;
import com.example.capstone.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
private final CommentService commentService;
    @PostMapping("/add/{productId}")
    public ResponseEntity addComment(@PathVariable int productId, @RequestBody Comment comment) {
        System.out.println("Coommeenntt"+comment);
        Comment addedComment = commentService.addComment(productId, comment);
        if (addedComment != null) {
            return ResponseEntity.status(201).body("comment added successfully");
        }
        return ResponseEntity.status(404).body("product not found"); // Product not found
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity getCommentsByProductId(@PathVariable int productId) {
        List<Comment> comments = commentService.getCommentsByProductId(productId);
        if (!comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        }
        return ResponseEntity.status(404).body(null); // Product not found or no comments
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable int commentId, @RequestBody String content) {
        Comment updatedComment = commentService.updateComment(commentId, content);
        if (updatedComment != null) {
            return ResponseEntity.ok("comment updated successfully");
        }
        return ResponseEntity.status(404).body("comment not found"); // Comment not found
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        boolean success = commentService.deleteComment(commentId);
        if (success) {
            return ResponseEntity.status(200).body("Comment deleted successfully");
        }
        return ResponseEntity.status(404).body("Comment not found");
    }
}
