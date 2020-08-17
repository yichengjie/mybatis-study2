package com.yicj.study.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonSchemaUtil {


    public static void main(String[] args) throws IOException {
        //1. 加载json
        String jsonFileName = "classpath:data/v1.json" ;
        ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver() ;
        Resource jsonResource = resolver.getResource(jsonFileName) ;
        String jsonContent = FileUtils.readFileToString(jsonResource.getFile());
        String schemaFileName = "classpath:schema/v1.jsonSchema" ;
        //2. 加载schema
        Resource schemaResource = resolver.getResource(schemaFileName);
        String schemaContent = FileUtils.readFileToString(schemaResource.getFile());
        //3. 构建jsonNode和schemaNode
        JsonNode jsonNode = strToJsonNode(jsonContent);
        JsonNode schemaNode = schemaToJsonNode(schemaFileName);
        //4. 执行校验
        boolean flag = getProcessingReport(jsonNode, schemaNode);
        System.out.println("flag : " + flag);
    }
 
    /**
     * @param jsonStr 验证json字符串
     */
    public static JsonNode strToJsonNode(String jsonStr) {
        JsonNode jsonNode = null;
        try {
            jsonNode = JsonLoader.fromString(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }
 
 
    /**
     * @param jsonFilePath jsonSchema文件路径
     */
    public static JsonNode schemaToJsonNode(String jsonFilePath) {
        JsonNode jsonSchemaNode = null;
        try {
            jsonSchemaNode = new JsonNodeReader().fromReader(new FileReader(ResourceUtils.getFile(jsonFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonSchemaNode;
    }
 
    /**
     * @param jsonNode   json数据node
     * @param schemaNode jsonSchema约束node
     */
    private static boolean getProcessingReport(JsonNode jsonNode, JsonNode schemaNode) {
        //fge验证json数据是否符合json schema约束规则
        ProcessingReport report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schemaNode, jsonNode);
        if (report.isSuccess()) {
            // 校验成功
            return true;
        } else {
            Iterator<ProcessingMessage> it = report.iterator();
            StringBuilder ms = new StringBuilder();
            ms.append("json格式错误: ");
            while (it.hasNext()) {
                ProcessingMessage pm = it.next();
                if (!LogLevel.WARNING.equals(pm.getLogLevel())) {
                    ms.append(pm);
                }
            }
            System.err.println(ms);
            return false;
        }
    }
}