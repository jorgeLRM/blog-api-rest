package com.system.blog.service;

import com.system.blog.dto.PostDTO;
import com.system.blog.dto.PostResponse;

public interface PostService {

    public PostDTO createPost(PostDTO publicationDTO);
    public PostResponse findAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
    public PostDTO findById(Long id);
    public PostDTO updatePost(PostDTO publicationDTO, Long id);
    public void deletePost(Long id);
}
