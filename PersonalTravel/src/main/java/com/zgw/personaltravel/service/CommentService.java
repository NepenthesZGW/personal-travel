package com.zgw.personaltravel.service;

import com.zgw.personaltravel.entity.Comment;
import com.zgw.personaltravel.entity.ResultBody;

import java.util.List;

public interface CommentService {
    ResultBody<Comment> create(Comment comment);

    ResultBody<List<Comment>> findByLogId(String logId);

}
