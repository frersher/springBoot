package com.shine.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeanConvertUtil {

    private MapperFacade mapperFacade = null;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public BeanConvertUtil() {
        this.init();
    }

    public <V, P> P convert(V base, Class<P> target) {
        return base == null ? null : mapperFacade.map(base, target);
    }

    public <V, P> void convert(V base, P target) {
        mapperFacade.map(base, target);
    }

    public <V, P> List<P> convertList(List<V> baseList, Class<P> target) {
        return baseList == null ? null : mapperFacade.mapAsList(baseList, target);
    }

    private void init() {
        // 不指定名称,则设为默认的转换器。若指定名称,则调用时需要显示声明。
//        mapperFactory.getConverterFactory().registerConverter(new BooleanToIntegerConverter());
//        mapperFactory.getConverterFactory().registerConverter(new DateToStringConverter(Constant.TIME_FORMAT));
//        mapperFactory.getConverterFactory().registerConverter(new BidirectionalConverter<OrgTypeEnum, Integer>() {
//            @Override
//            public Integer convertTo(OrgTypeEnum source, Type<Integer> destinationType, MappingContext mappingContext) {
//                return source == null ? null : source.getCode();
//            }
//
//            @Override
//            public OrgTypeEnum convertFrom(Integer source, Type<OrgTypeEnum> destinationType, MappingContext mappingContext) {
//                return OrgTypeEnum.ofCode(source);
//            }
//        });

//        mapperFactory.classMap(TagGroupNodeDTO.class, Option.class)
//                .field("groupNodeId", "code")
//                .field("groupName", "label")
//                .byDefault()
//                .register();

        // 初始化
        mapperFacade = mapperFactory.getMapperFacade();
    }

}