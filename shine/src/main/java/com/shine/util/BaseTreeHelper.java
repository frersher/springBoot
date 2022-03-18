package com.shine.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

public class BaseTreeHelper {
    private static final String KEY_CHILDREN = "children";

    public BaseTreeHelper() {
    }

    public static List<Map<String, Object>> createTree(List<Map<String, Object>> data, String key) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList();
        } else {
            //将集合中所有数据按照父Id进行分组，放入Map中，Map<parntId, List<Child>>
            Map groupByParentIdMap = data.stream().collect(Collectors.groupingBy((item) -> {
                return item.get(key) != null ? item.get(key).toString() : "nonParent";
            }));
            //将集合中所有数据以数据Id为key，放入Map中，Map<id, Map<String,Object>>
            Map dataMap = data.stream().collect(Collectors.toMap((item) -> {
                return item.get("id").toString();
            }, (t2) -> {
                return t2;
            }));
            ArrayList resp = new ArrayList();
            //遍历数据，将子节点放入对应父节点Children属性中
            groupByParentIdMap.keySet().forEach((parentId) -> {
                if (dataMap.containsKey(parentId)) {
                    Object child = (List) ((Map) dataMap.get(parentId)).get("children");
                    if (CollectionUtils.isEmpty((Collection) child)) {
                        child = new ArrayList();
                        ((Map) dataMap.get(parentId)).put("children", child);
                    }

                    ((List) child).addAll((Collection) groupByParentIdMap.get(parentId));
                } else {
                    resp.addAll((Collection) groupByParentIdMap.get(parentId));
                }

            });
            return resp;
        }
    }

    public static <T extends BaseTreeVO> List<T> createTree(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList();
        } else {
            //将集合中所有数据按照父Id进行分组，放入Map中，Map<parntId, List<Child>>
            Map groupByParentIdMap = data.stream().collect(Collectors.groupingBy((item) -> {
                return item.getParentId() != null ? item.getParentId().toString() : "nonParent";
            }));
            //将集合中所有数据以数据Id为key，放入Map中，Map<id,T>
            Map dataMap = data.stream().collect(Collectors.toMap((item) -> {
                return item.getId().toString();
            }, (t2) -> {
                return t2;
            }));
            ArrayList resp = new ArrayList();
            //遍历数据，将子节点放入对应父节点Children属性中
            groupByParentIdMap.keySet().forEach((parentId) -> {
                if (dataMap.containsKey(parentId)) {
                    Object child = ((BaseTreeVO) dataMap.get(parentId)).getChildren();
                    if (CollectionUtils.isEmpty((Collection) child)) {
                        child = new ArrayList();
                    }

                    ((List) child).addAll((Collection) groupByParentIdMap.get(parentId));
                    ((BaseTreeVO) dataMap.get(parentId)).setChildren((List) child);
                } else {
                    resp.addAll((Collection) groupByParentIdMap.get(parentId));
                }

            });
            return resp;
        }
    }
}