package cn.yzx.community.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class blog {
    private int id;
    private String title;
    private String value;
    private Date createTime;
    private Date modifyTime;
    private String firstImg;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
}
