package com.cfg.idgen.util;


import org.apache.commons.lang3.StringUtils;
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
    public static <T> List<String> getChildMenu(List<T> menuList, String id,String pidField,String idField) {
        ArrayList<String> menuIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList)) {
            for (T m : menuList) {
                //获取父id
                Object pid = ReflectUtils.getFieldValue(m, pidField);
                if (String.valueOf(pid).equals(id)) {//节点pid等于id
                    //获取id
                    Object id1 = ReflectUtils.getFieldValue(m, idField);
                    menuIds.add(id1.toString());
                    //递归遍历下一级
                    List<String> childMenu = getChildMenu(menuList, id1.toString(),pidField,idField);
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
    public static <T> List<T> buildTree(List<T> depts,String pidField,String idField)  {
        ArrayList<T> sysDepts = new ArrayList<>();
        try {
            for (T sysDept : getNodeTree(depts, pidField)) {
                getChildrenNode(depts, sysDept, pidField, idField);
                sysDepts.add(sysDept);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sysDepts;
    }


    /***
     * @author chenfg
     * @date: 2024/4/25 13:30
     * @description:查询所有父节点
     */
    private static <T> List<T> getNodeTree(List<T> depts,String pidField) {
        ArrayList<T> sysDepts = new ArrayList<>();
        for (T dept : depts) {
            Object pid = ReflectUtils.getFieldValue(dept, pidField);
            if (pid == null|| StringUtils.isBlank(String.valueOf(pid))) {
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
    private static <T> T getChildrenNode(List<T> depts, T sysDept,String pidField,String idField) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<T> sysDepts = new ArrayList<>();
        for (T dept : depts) {
            Object pid = ReflectUtils.getFieldValue(dept, pidField);
            Object id = ReflectUtils.getFieldValue(sysDept, idField);
            if (id.equals(pid)) {
                sysDepts.add(getChildrenNode(depts, dept,pidField,idField));
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
