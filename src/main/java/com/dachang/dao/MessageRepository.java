package com.dachang.dao;

import com.dachang.pojo.Comment;
import com.dachang.pojo.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author : xsh
 * @create : 2020-03-18 - 15:28
 * @describe:
 */
public interface MessageRepository  extends JpaRepository<Message,Long>, JpaSpecificationExecutor<Message> {

}
