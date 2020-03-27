package com.zgw.personaltravel.service.impl;

import com.zgw.personaltravel.entity.Comment;
import com.zgw.personaltravel.entity.CommentExample;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.mapper.CommentDAO;
import com.zgw.personaltravel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Override
    public ResultBody<Comment> create(Comment comment) {
        int insert = commentDAO.insert(comment);
        return insert == 1 ? ResultBody.success : ResultBody.error;
    }

    @Override
    public ResultBody<List<Comment>> findByLogId(String logId) {
        CommentExample example=new CommentExample();
        example.createCriteria().andLogidEqualTo(Integer.parseInt(logId));
        example.setOrderByClause("commentdate");
        List<Comment> comments = commentDAO.selectByExample(example);

        return  new ResultBody<List<Comment>>().setCode(200).setBody(comments);
    }
}
