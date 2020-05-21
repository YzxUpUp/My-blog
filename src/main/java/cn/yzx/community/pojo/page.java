package cn.yzx.community.pojo;

import lombok.Data;

import java.util.List;

@Data
public class page {
    private int currentPage; //当前页码
    private int totalPage; //总页数
    private int totalCount; //总数据量
    private List<blog> counts; //每个分页所含数据量
    private boolean firstPage; //是否为第一页
    private boolean lastpage; //是否为最后一页
    private List<String> allTag;
}
