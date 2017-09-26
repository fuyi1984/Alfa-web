package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/26.
 * 单位表
 */
public class SysOrg extends Entity implements Serializable {
    /**
     * 节点ID
     */
    private String CascadeId;
    /**
     * 单位全称
     */
    private String depart_fullname;
    /**
     * 单位简称
     */
    private String depart_name;
    /**
     * 单位简拼
     */
    private String depart_name_e;
    /**
     * 单位编码
     */
    private String depart_code;
    /**
     * 单位状态
     */
    private String status;
    /**
     * 单位状态名称
     */
    private String statusname;
    /**
     * 单位类型
     */
    private String types;
    /**
     * 单位类型名称
     */
    private String typesname;
    /**
     * 父节点名称
     */
    private String ParentName;
    /**
     * 父节点ID
     */
    private Long ParentId;
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
     * 排序号
     */
    private int sortno;

    public String getCascadeId() {
        return CascadeId;
    }

    public void setCascadeId(String cascadeId) {
        CascadeId = cascadeId;
    }

    public String getDepart_fullname() {
        return depart_fullname;
    }

    public void setDepart_fullname(String depart_fullname) {
        this.depart_fullname = depart_fullname;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getDepart_name_e() {
        return depart_name_e;
    }

    public void setDepart_name_e(String depart_name_e) {
        this.depart_name_e = depart_name_e;
    }

    public String getDepart_code() {
        return depart_code;
    }

    public void setDepart_code(String depart_code) {
        this.depart_code = depart_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTypesname() {
        return typesname;
    }

    public void setTypesname(String typesname) {
        this.typesname = typesname;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public Long getParentId() {
        return ParentId;
    }

    public void setParentId(Long parentId) {
        ParentId = parentId;
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

    public int getSortno() {
        return sortno;
    }

    public void setSortno(int sortno) {
        this.sortno = sortno;
    }
}
