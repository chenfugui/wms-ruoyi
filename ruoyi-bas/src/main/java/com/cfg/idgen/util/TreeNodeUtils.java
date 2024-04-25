package com.cfg.idgen.util;


import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/***
 * @author chenfg
 * @date: 2024/4/25 13:30
 * @description:
 */
public class TreeNodeUtils {


    /**
     * cannot be instantiated
     */
    private TreeNodeUtils() {
    }

    /***
     * @author chenfg
     * @date: 2024/4/25 13:30
     * @description:获取所有子节点id
     */
    public static <T> List<String> getChildMenu(List<T> menuList, String id) {
        ArrayList<String> menuIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList)) {
            for (T m : menuList) {
                //获取父id
                Object pid = ReflectUtils.getFieldValue(m, "pid");
                if (String.valueOf(pid).equals(id)) {//节点pid等于id
                    //获取id
                    Object id1 = ReflectUtils.getFieldValue(m, "id");
                    menuIds.add(id1.toString());
                    //递归遍历下一级
                    List<String> childMenu = getChildMenu(menuList, id1.toString());
                    menuIds.addAll(childMenu);
                }
            }
        }
        return menuIds;
    }

    /***
     * @author chenfg
     * @date: 2024/4/25 13:30
     * @description:建立父节点树形结构
     */
    public static <T> List<T> buildTree(List<T> depts) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<T> sysDepts = new ArrayList<>();
        for (T sysDept : getNodeTree(depts)) {
            getChildrenNode(depts, sysDept);
            sysDepts.add(sysDept);
        }
        return sysDepts;
    }


    /***
     * @author chenfg
     * @date: 2024/4/25 13:30
     * @description:查询所有父节点
     */
    private static <T> List<T> getNodeTree(List<T> depts) {
        ArrayList<T> sysDepts = new ArrayList<>();
        for (T dept : depts) {
            Object pid = ReflectUtils.getFieldValue(dept, "pid");
            if (pid == null) {
                sysDepts.add(dept);
            }
        }
        return sysDepts;
    }

    /***
     * @author chenfg
     * @date: 2024/4/25 13:30
     * @description:递归建立树形子节点，将子节点放到父节点下
     */
    private static <T> T getChildrenNode(List<T> depts, T sysDept) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<T> sysDepts = new ArrayList<>();
        for (T dept : depts) {
            Object pid = ReflectUtils.getFieldValue(dept, "pid");
            Object id = ReflectUtils.getFieldValue(sysDept, "id");
            if (id.equals(pid)) {
                sysDepts.add(getChildrenNode(depts, dept));
            }
        }
        //通过反射获取children字段
        Field childCatalog = sysDept.getClass().getDeclaredField("children");
        //--cfg修复漏洞,使用makeAccessible设置可访问性
        ReflectionUtils.makeAccessible(childCatalog);
        childCatalog.set(sysDept, sysDepts);
        return sysDept;
    }


}
