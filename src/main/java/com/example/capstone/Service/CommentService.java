// CommentService.java
package com.example.capstone.Service;

import com.example.capstone.Model.Comment;
import com.example.capstone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CommentService {
    private final ProductService productService;
    private final List<Comment> comments = new ArrayList<>();

    public Comment addComment(int productId, Comment comment) {

        Product product = productService.getProduct(productId);
        System.out.println(product);
        if (product != null) {
            comments.add(comment);
            product.setComments(comments);

            return comment;
        }
        return null; // Product not found
    }
    public List<Comment> getCommentsByProductId(int productId) {
        Product product = productService.getProduct(productId);
        if (product != null) {
            return product.getComments();
        }
        return new ArrayList<>(); // Product not found
    }

    public Comment updateComment(int commentId, String content) {
        for (Comment comment : comments) {
            if (comment.getId() == commentId) {
                comment.setComment(content);
                return comment;
            }
        }
        return null; // Comment not found
    }

    public boolean deleteComment(int commentId) {
        for (Comment comment : comments) {
            if (comment.getId() == commentId) {
                comments.remove(comment);
                return true;
            }
        }
        return false;
    }


    private Product getProductById(int productId) {
        System.out.println(productId);
        for (Product product : productService.products) {
            if (product.getId() == productId) {

                return product;
            }
            else {
                return null;
            }
        }
        return null; // Product not found
    }
}
