package cn.yzx.community.mapper;

import cn.yzx.community.pojo.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface userMapper {
    user getUser(@Param("username") String username,
                 @Param("password") String password);
}
