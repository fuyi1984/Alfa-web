package com.alfa.web.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 * 功能模块表
 */
public class SysModules extends Entity implements Serializable {

    public Long getModulesId() {
        return ModulesId;
    }

    public void setModulesId(Long modulesId) {
        ModulesId = modulesId;
    }

    /**
     * 模块ID

     */
    private Long ModulesId;
    /**
     * 节点ID
     */
    private String CascadeId;
    /**
     * 功能模块名称
     */
    private String Name;

    /**
     * 页面URL
     */
    private String Url;

    /**
     * 是否叶子节点
     */
    private String IsLeaf;

    /**
     * 是否自动展开
     */
    private String IsAutoExpand;

    /**
     * 节点图标文件名
     */
    private String IconName;

    /**
     * 当前状态
     */
    private String Status;

    /**
     * 父节点名称
     */
    private String ParentName;

    /**
     * 矢量图标
     */
    private String Vector;

    /**
     * 排序号
     */
    private int SortNo;

    /**
     * 父节点ID
     */
    private Long ParentId;

    /**
     * 模块元素列表(需要权限控制的按钮)
     */
    private List<SysModuleElement> SysModuleElementLst;

    public String getCascadeId() {
        return CascadeId;
    }

    public void setCascadeId(String cascadeId) {
        CascadeId = cascadeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getIsLeaf() {
        return IsLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        IsLeaf = isLeaf;
    }

    public String getIsAutoExpand() {
        return IsAutoExpand;
    }

    public void setIsAutoExpand(String isAutoExpand) {
        IsAutoExpand = isAutoExpand;
    }

    public String getIconName() {
        return IconName;
    }

    public void setIconName(String iconName) {
        IconName = iconName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getVector() {
        return Vector;
    }

    public void setVector(String vector) {
        Vector = vector;
    }

    public Long getParentId() {
        return ParentId;
    }

    public void setParentId(Long parentId) {
        ParentId = parentId;
    }

    public int getSortNo() {
        return SortNo;
    }

    public void setSortNo(int sortNo) {
        SortNo = sortNo;
    }

    public List<SysModuleElement> getSysModuleElementLst() {
        return SysModuleElementLst;
    }

    public void setSysModuleElementLst(List<SysModuleElement> sysModuleElementLst) {
        SysModuleElementLst = sysModuleElementLst;
    }
}
