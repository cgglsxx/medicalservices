package com.api.util;

import com.api.result.GlobalErrorInfoException;
import com.api.result.messageenum.RegisteredErrorInfoEnum;
import com.api.selfannotation.ToMapAnno;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * javabean与map转换
 * Created by cjh on 2018/6/1.
 */
public class ReflectMapUtil {
    /**
     * 将Map转化为Object返回泛型类型
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T mapToT(Map<String, Object> map, Class<T> beanClass) throws GlobalErrorInfoException {
        Object obj = mapToObject(map, beanClass);
        return obj == null ? null : ((T)obj);
    }

    /**
     * 将Map转化为Object
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws GlobalErrorInfoException {
        if (map == null)
            return null;
        Object obj = null;
        try {
            obj = beanClass.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_TRANS_ERROR);
        }
        return obj;
    }

    /**
     * 将Object转化为Map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws GlobalErrorInfoException {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_TRANS_ERROR);
        }
        return map;
    }

    /**
     * 将Object转化为Map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object obj) throws GlobalErrorInfoException {
        if(obj == null){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data_map = new HashMap<String, Object>();
        Class clazz = obj.getClass();
        Class clazz_sup = obj.getClass().getSuperclass();
        try {
            Field[] declaredFields = clazz.getDeclaredFields();
            Field[] declaredFields_sup = clazz_sup.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                data_map.put(field.getName(), field.get(obj));
                if (field.isAnnotationPresent(ToMapAnno.class)) {
                    ToMapAnno anno = field.getAnnotation(ToMapAnno.class);
                    String name = anno.name();
                    if (name != null && !"".equals(name)) {
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                        Method method = pd.getReadMethod();
                        Object value = method.invoke(obj);
                        data_map.put(name, value);
                    }
                }
            }
            for (Field field_sup : declaredFields_sup) {
                field_sup.setAccessible(true);
                map.put(field_sup.getName(), field_sup.get(obj));
                if (field_sup.isAnnotationPresent(ToMapAnno.class)) {
                    ToMapAnno anno = field_sup.getAnnotation(ToMapAnno.class);
                    String name = anno.name();
                    if (name != null && !"".equals(name)) {
                        PropertyDescriptor pd = new PropertyDescriptor(field_sup.getName(), clazz_sup);
                        Method method = pd.getReadMethod();
                        Object value = method.invoke(obj);
                        map.put(name, value);
                    }
                }
            }
            map.put("data",data_map);
            if(map.containsKey("serialVersionUID")){
                map.remove("serialVersionUID");
            }
        } catch (Exception e) {
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_TRANS_ERROR);
        }
        return map;
    }
}
