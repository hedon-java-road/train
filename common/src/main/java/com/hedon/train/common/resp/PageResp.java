package com.hedon.train.common.resp;

import java.io.Serializable;
import java.util.List;

public class PageResp<T> implements Serializable {
    // 总条数
    private Long total;

    // 当前页
    private List<T> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageResp() {
    }

    public PageResp(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

}
