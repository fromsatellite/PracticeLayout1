package com.example.annotation.clazz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

/**
 * Created by leador_yang on 2018/6/7.
 */
@SupportedAnnotationTypes({"com.example.annotation.clazz.Seriable"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class JsonProcessor extends AbstractProcessor {

    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // 工具辅助类
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 1、根据自定义的注解拿到Element set集合
        Set<? extends Element> elementSet = roundEnv.getElementsAnnotatedWith(Seriable.class);

        TypeElement typeElement;
        VariableElement variableElement;
        Map<String, List<VariableElement>> map = new HashMap<>();
        List<VariableElement> fields = null;
        // 2、根据Element的类型做相应的处理，并保存到map集合
        for (Element element : elementSet){
            ElementKind elementKind = element.getKind();
            if (elementKind == ElementKind.CLASS){ // 该元素是类
                typeElement = (TypeElement) element;
                // 用类的全限定类名作为key，确保唯一
                String qualifiedName = typeElement.getQualifiedName().toString();
                fields = new ArrayList<>();
                map.put(qualifiedName, fields);
            } else if (elementKind == ElementKind.FIELD){ // 该元素是成员变量
                variableElement = (VariableElement) element;
                // 获取该元素的封装类型
                typeElement = (TypeElement) variableElement.getEnclosingElement();
                String qualifiedName = typeElement.getQualifiedName().toString();
                // 判断该封装类型的key是否已存在
                fields = map.get(qualifiedName);
                if (fields == null){
                    fields = new ArrayList<>();
                    map.put(qualifiedName, fields);
                }
                fields.add(variableElement);
            }
        }

        Set<String> set = map.keySet();
        for (String key : set){
            if (map.get(key).size() == 0){
                typeElement = elementUtils.getTypeElement(key);
                List<? extends Element> allMembers = elementUtils.getAllMembers(typeElement);
                if (allMembers.size() > 0){
                    map.get(key).addAll(ElementFilter.fieldsIn(allMembers));
                }
            }
        }
        // 3、根据map集合数据生成代码
        generateCodes(map);

        return true;
    }

    private void generateCodes(Map<String, List<VariableElement>> map){
        File dir = new File("e://Animation");
        if (!dir.exists()){
            dir.mkdirs();
        }
        for (String key : map.keySet()){
            // new file
            File file = new File(dir, key.replaceAll("\\.", "_") + ".txt");
            try {
                FileWriter writer = new FileWriter(file);
                writer.append("{").append("class:").append("\"" + key + "\"")
                        .append(",\n");
                writer.append("fields:\n {\n");
                List<VariableElement> fields = map.get(key);
                for (int i = 0;i<fields.size();i++){
                    VariableElement field = fields.get(i);
                    writer.append("      ").append(field.getSimpleName()).append(":")
                            .append("\"" + field.asType().toString() + "\"");
                    if (i < fields.size()-1){
                        writer.append(",\n");
                    }
                }
                writer.append("\n }\n");
                writer.append("}");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
