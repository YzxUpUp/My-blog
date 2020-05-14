package cn.yzx.community.mapper;

import cn.yzx.community.pojo.comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface commentMapper {
    void addComment(comment comm);

    List<comment> getComment(@Param("parentId") Long parentId);

    void delComm(@Param("type")String type);
}
