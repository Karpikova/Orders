package main.dao;

import main.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDBHandler extends CrudRepository<Comment, Long> {
}
