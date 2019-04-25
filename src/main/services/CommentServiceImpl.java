package main.services;

import main.dao.CommentDBHandler;
import main.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDBHandler commentDBHandler;

    public Comment create(Comment comment) {
        return commentDBHandler.save(comment);
    }
}
