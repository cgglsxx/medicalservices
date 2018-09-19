package com.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 * @类的名称: TreeUtil
 * @类的说明: 树的处理类
 * @创建时间: 2014-5-2 下午8:24:42
 * @version:
 */
public class TreeUtil {
    /**
     * @param list
     * @param id   一个id
     * @return
     * @方法说明: 根据id获得所有子集 不包括本身
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List getMeChildList(List list, String id, List result) {
        if (result == null) {
            result = new ArrayList();
        }
        for (int i = 0, size = list.size(); i < size; i++) {
            if (!ValidateUtil.isEmpty(list.get(i))) {
                Map map = (Map) list.get(i);
                String parentId = map.get("parentId").toString();
                String mId = map.get("cateId").toString();
                if (id.equals(parentId)) {
                    result.add(map);
                    getMeChildList(list, mId, result);
                }
            }
        }
        return result;
    }

    /**
     * @param list     菜单list
     * @param parentId 父级id
     * @param parent   HashMap
     * @param children 子集key
     * @return 树形list
     * @方法说明: 根据父级ID获得树形菜单的list
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List getTreeList(List list, String parentId, Map parent, String children) {
        List child = (List) parent.get(children);
        if (child == null) {
            child = new ArrayList();
            parent.put(children, child);
        }
        for (int i = 0, size = list.size(); i < size; i++) {
            if (!ValidateUtil.isEmpty(list.get(i))) {
                Map map = (Map) list.get(i);
                if (parentId.equals((map.get("parentId").toString()))) {
                    child.add(map);
                    getTreeList(list, map.get("cateId").toString(), map, children);
                }
            }
        }
        return (List) parent.get(children);
    }

}
