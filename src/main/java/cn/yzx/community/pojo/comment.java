package cn.yzx.community.pojo;

import lombok.Data;

@Data
public class comment {
    private Long id;
    private Long parentId;
    private String type;
    private String commenterName;
    private String commenterEmail;
    private String commentValue;
}
