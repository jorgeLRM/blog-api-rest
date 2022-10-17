package com.system.blog.service;

import com.system.blog.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Long postId, CommentDTO commentDTO);
    public List<CommentDTO> findCommentsByPostId(Long postId);
    public CommentDTO findCommentById(Long postId, Long commentId);
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentRequest);
    public void deleteComment(Long postId, Long commentId);
}
