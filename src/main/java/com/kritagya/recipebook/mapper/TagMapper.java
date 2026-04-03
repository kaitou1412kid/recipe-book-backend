package com.kritagya.recipebook.mapper;

import com.kritagya.recipebook.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public Tag toCreate(String name){
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }
}
