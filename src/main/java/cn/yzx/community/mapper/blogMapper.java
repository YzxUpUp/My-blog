package cn.yzx.community.mapper;

import cn.yzx.community.pojo.blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface blogMapper {
    void addBlog(blog b);

    List<blog> getAllBolg();
}
