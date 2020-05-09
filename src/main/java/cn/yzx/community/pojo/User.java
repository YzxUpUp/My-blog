package cn.yzx.community.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accounId;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;
}
