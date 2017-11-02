package com.alfa.web.util;

import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.vo.Menus;
import com.alfa.web.vo.TreeNode;
import com.alfa.web.vo.treedata;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

    //region 左边导航菜单

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<Menus> buildByRecursive(List<Menus> treeNodes) {
        List<Menus> trees = new ArrayList<Menus>();
        for (Menus treeNode : treeNodes) {
            if (treeNode.getParentId().equals(0l)) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Menus findChildren(Menus treeNode, List<Menus> treeNodes) {

        for (Menus it : treeNodes) {

            if(treeNode.getMenuId().equals(it.getParentId())) {
                if (treeNode.getMenuInfos()== null) {
                    treeNode.setMenuInfos(new ArrayList<Menus>());
                }

                treeNode.getMenuInfos().add(findChildren(it,treeNodes));
            }
        }

        return treeNode;
    }

    //endregion

    //region 树形导航菜单

    public static List<treedata> buildTreeByRecursive(List<treedata> treeNodes) {
        List<treedata> trees = new ArrayList<treedata>();
        for (treedata treeNode : treeNodes) {
            if (treeNode.getParentId().equals(0l)) {
                trees.add(findtreeChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    public static treedata findtreeChildren(treedata treeNode, List<treedata> treeNodes) {

        for (treedata it : treeNodes) {

            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren()== null) {
                    treeNode.setChildren(new ArrayList<treedata>());
                }

                treeNode.getChildren().add(findtreeChildren(it,treeNodes));
            }
        }

        return treeNode;
    }

    //endregion

}
