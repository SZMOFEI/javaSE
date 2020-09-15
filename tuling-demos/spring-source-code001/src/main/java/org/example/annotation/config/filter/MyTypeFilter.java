package org.example.annotation.config.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

/**
 * @author mofei
 * @date 2020/9/15 23:42
 */
public class MyTypeFilter implements TypeFilter {
    /**
     * 当方法返回true 的时候， 接入配置， 否则不加入
     * @param metadataReader 元信息读取
     * @param metadataReaderFactory 元信息工厂
     * @return boolean
     * @throws IOException io
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //如果是UserService的类型， 那么就会被排除掉 。 因为包扫描的时候会启用这个
        if (metadataReader.getClassMetadata().getClassName().contains("UserService")){
            Set<String> annotationTypes = metadataReader.getAnnotationMetadata().getAnnotationTypes();
            for (String annotationType : annotationTypes) {
                System.out.println("当前正在被扫描的类注解类型 = " + annotationType);
            }
            //获取当前正在扫描类的信息
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            System.out.println("当前正在被扫描的类的类名" + classMetadata.getClassName());
            //获取当前类的资源信息（类存放的路径...）
            Resource resource = metadataReader.getResource();
            System.out.println("当前正在被扫描的类存放的地址" + resource.getURL());
            return true;
        }
        return false;
    }
}
