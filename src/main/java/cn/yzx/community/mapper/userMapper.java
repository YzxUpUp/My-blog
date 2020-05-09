package cn.yzx.community.mapper;

import cn.yzx.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper {
    void saveUser(User user);
}
