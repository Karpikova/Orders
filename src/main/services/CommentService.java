package main.services;

import main.entity.Comment;

public interface CommentService {

    /**
     * Create a new Order
     *
     * @param comment
     * @return
     */
    Comment create(Comment comment);
}
