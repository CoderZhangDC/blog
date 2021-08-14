package com.dachang.service;

import com.dachang.pojo.Comment;

import java.util.List;

/**
 * @author : xsh
 * @create : 2020-02-15 - 0:21
 * @describe:
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
