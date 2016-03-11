package com.anicloud.sunny.application.json;

import com.anicloud.sunny.application.service.app.AniServiceManager;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceEntranceDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
public class JacksonTest {

    private String filePath = "classpath:properties/StubMeta.json";


    @Test
    public void testFormatJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);

        Person person = new Person("zhang", 12, 1234.0f);
        String result = objectMapper.writeValueAsString(person);
        System.out.println(result);
        //System.out.println(ResourceUtils.isUrl("classpath:person.json"));
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(filePath);
        //Resource resource = new ClassPathResource("person.json");

        // write
        System.out.println(resource.exists());
        File file = resource.getFile();
        objectMapper.writeValue(file, person);

        // read
        Person person1 = objectMapper.readValue(file, Person.class);
        System.out.println(person1.toString());
    }


}

class Person {
    public String name;
    public int age;
    public float income;

    public Person() {
    }

    public Person(String name, int age, float income) {
        this.name = name;
        this.age = age;
        this.income = income;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", income=" + income +
                '}';
    }
}
