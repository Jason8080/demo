//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.gm.demo.nacos.server.common.mod;

import com.github.pagehelper.PageInfo;

public class PageResultVo extends ResultVo {
    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private Integer pages;
    private Long total;
    private Boolean isFirstPage = false;
    private Boolean isLastPage = false;
    private Boolean hasPreviousPage = false;
    private Boolean hasNextPage = false;

    public PageResultVo() {
    }

    public PageResultVo(int code, String msg, Object data) {
        super.setCode(code);
        super.setMsg(msg);
        if (data instanceof PageInfo) {
            PageInfo pageInfo = (PageInfo)data;
            super.setData(pageInfo.getList());
            this.pageNum = pageInfo.getPageNum();
            this.pageSize = pageInfo.getPageSize();
            this.size = pageInfo.getSize();
            this.pages = pageInfo.getPages();
            this.total = pageInfo.getTotal();
            this.isFirstPage = pageInfo.isIsFirstPage();
            this.isLastPage = pageInfo.isIsLastPage();
            this.hasPreviousPage = pageInfo.isHasPreviousPage();
            this.hasNextPage = pageInfo.isHasNextPage();
        } else {
            super.setData(data);
        }

    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return this.pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Boolean getFirstPage() {
        return this.isFirstPage;
    }

    public void setFirstPage(Boolean firstPage) {
        this.isFirstPage = firstPage;
    }

    public Boolean getLastPage() {
        return this.isLastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.isLastPage = lastPage;
    }

    public Boolean getHasPreviousPage() {
        return this.hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Boolean getHasNextPage() {
        return this.hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
