package com.zgw.personaltravel.mapper;

import com.zgw.personaltravel.entity.Comment;
import com.zgw.personaltravel.entity.CommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * CommentDAO继承基类
 */
@Repository
@Mapper
public interface CommentDAO extends MyBatisBaseDao<Comment, Integer, CommentExample> {
}