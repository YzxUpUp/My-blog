package cn.yzx.community.mapper;

import cn.yzx.community.pojo.user;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface userMapper extends BaseMapper<user> {
    user getUser(@Param("username") String username,
                 @Param("password") String password);
}
