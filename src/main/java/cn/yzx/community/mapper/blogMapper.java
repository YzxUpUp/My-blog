package cn.yzx.community.mapper;

import cn.yzx.community.pojo.blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface blogMapper {
    void addBlog(blog b);

    List<blog> getAllBolg();

    List<blog> getByPage(@Param("start") int start,
                         @Param("rows") int rows);

    void updateBlog(blog b);

    void deleteBlog(@Param("id") int id);

    blog getBlog(@Param("id") int id);
}
