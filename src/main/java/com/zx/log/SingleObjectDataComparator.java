package com.zx.log;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 单对象比较器
 *
 * @author : zhangxuan
 * @date : 2022/12/08 19:05
 */
@Component
public class SingleObjectDataComparator implements DataComparator {

    @Override
    public List<OperationResult> comparator() {
        List<OperationModel> models = OperationContext.getModels().getModels();

        List<OperationResult> results = new ArrayList<>();

        for (OperationModel model : models) {

            OperationResult result = new OperationResult();

            for (Map.Entry<Class<?>, Object> before : model.getBefore().entrySet()) {

                for (Map.Entry<Class<?>, Object> after : model.getAfter().entrySet()) {

                    if (before.getKey().isAssignableFrom(after.getKey())) {

                        List<FieldMetadata> metadata = buildMetadata(before);

                        for (FieldMetadata field : metadata) {
                            Object beforeValue = getValue(field, before.getValue());
                            Object afterValue = getValue(field, after.getValue());

                            if (field.getStrategy() == CompareStrategyEnum.SINGLE_COMPARE) {
                                if (field.getField().getType().isAssignableFrom(BigDecimal.class) && beforeValue != null && afterValue != null) {

                                    if (((BigDecimal) beforeValue).compareTo((BigDecimal) afterValue) != 0) {
                                        result.addDifference(field.getField().getName(), field.getDisplayName(), beforeValue, afterValue);
                                    }

                                } else if (!ObjectUtil.equal(beforeValue, afterValue)) {

                                    result.addDifference(field.getField().getName(), field.getDisplayName(), beforeValue, afterValue);
                                }
                            } else if (field.getStrategy() == CompareStrategyEnum.PICTURE_LIST) {

                                result.addPicture(field.getField().getName(), field.getDisplayName(), beforeValue, afterValue);
                            }

                        }
                    }
                }
            }
            compareList(model, result);

            result.addIdentifier(model.getIdentifier());
            result.addExts(model.getExt1(), model.getExt2(), model.getExt3());
            result.setOperation(model.getTypeName());

            results.add(result);
        }
        return results;
    }

    private Object getValue(FieldMetadata field, Object o) {
        Object value = ReflectUtil.getFieldValue(o, field.getField());

        IEnum[] iEnum = parseEnum(field);

        return convert(getValue(iEnum, value));
    }

    private void compareList(OperationModel model, OperationResult result) {

        Set<String> bizKeys = new HashSet<>();

        if (MapUtil.isNotEmpty(model.getBeforeList())) {
            bizKeys.addAll(model.getBeforeList().keySet());
        }
        if (MapUtil.isNotEmpty(model.getAfterList())) {
            bizKeys.addAll(model.getAfterList().keySet());
        }

        for (String bizKey : bizKeys) {
            Collection<?> beforeList = model.getBeforeList().get(bizKey);

            List<String> filterFields = model.getFilterFields().get(bizKey);

            Collection<?> afterList = model.getAfterList().get(bizKey);

            result.addDifference(null, bizKey,
                    JSONUtil.toJsonStr(compareFieldList(beforeList, filterFields)),
                    JSONUtil.toJsonStr(compareFieldList(afterList, filterFields))
            );
        }
    }

    private List<FieldListContent> compareFieldList(Collection<?> fieldList, List<String> filterFields) {

        boolean filterField = CollUtil.isNotEmpty(filterFields);

        List<FieldListContent> contents = new ArrayList<>();

        for (Object o : fieldList) {
            List<FieldMetadata> fieldMetadatas = getAllFields(o.getClass());

            for (FieldMetadata field : fieldMetadatas) {

                if (filterField && !filterFields.contains(field.field.getName())) {
                    continue;
                }
                Object value = getValue(field, o);

                contents.add(new FieldListContent(field.field.getName(), field.getDisplayName(), value));

            }
        }
        return contents;
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Object convert(Object value) {
        if (value instanceof LocalDateTime) {
            return formatter.format((LocalDateTime) value);
        }
        return value;
    }

    private Object getValue(IEnum[] iEnum, Object code) {

        if (iEnum != null && iEnum.length > 0) {

            for (IEnum anEnum : iEnum) {

                if (anEnum.getCode().equals(code)) {
                    return anEnum.getName();
                }
            }
        }
        return code;
    }

    private IEnum[] parseEnum(FieldMetadata field) {
        return field.ienum.getEnumConstants();
    }

    private List<FieldMetadata> buildMetadata(Map.Entry<Class<?>, Object> o) {

        return getAllFields(o.getKey());
    }


    /**
     * 获取该类的所有属性列表
     *
     * @param clazz 对象类
     * @return 属性集合
     */
    public static List<FieldMetadata> getAllFields(Class<?> clazz) {

        List<FieldMetadata> metadata = new ArrayList<>();

        List<Field> fieldList = ReflectionKit.getFieldList(ClassUtils.getUserClass(clazz));
        if (CollectionUtils.isNotEmpty(fieldList)) {

            for (Field field : fieldList) {

                OperationField f = field.getAnnotation(OperationField.class);

                if (f != null) {
                    metadata.add(new FieldMetadata(f, field));
                }
            }
        }
        return metadata;
    }

    @Getter
    @Setter
    private static class FieldListContent {

        private final String fieldName;

        private final String displayName;

        private final Object value;

        private FieldListContent(String fieldName,
                                 String displayName,
                                 Object value) {
            this.fieldName = fieldName;
            this.displayName = displayName;
            this.value = value;
        }
    }

    @Getter
    private static class FieldMetadata {

        private final Field field;

        private final String displayName;

        private final Class<? extends IEnum> ienum;

        private final CompareStrategyEnum strategy;

        public FieldMetadata(OperationField anno, Field field) {
            ienum = anno.enumType();
            this.displayName = anno.displayName();
            this.strategy = anno.compare();
            this.field = field;
        }
    }
}
