package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.TagEntity;
import com.cybersoft.cozastore.payload.response.TagResponse;
import com.cybersoft.cozastore.repository.TagRepository;
import com.cybersoft.cozastore.service.imp.TagServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService implements TagServiceImp {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<TagResponse> getAllTag() {
        List<TagEntity> tagEntities = tagRepository.findAll();
        List<TagResponse> tagResponses = new ArrayList<>();
        for (TagEntity tagEntity : tagEntities){
            TagResponse tagResponse = new TagResponse();
            tagResponse.setIdTag(tagEntity.getId());
            tagResponse.setName(tagEntity.getName());
            tagResponse.setCreateDate(tagEntity.getCreateDate());

            tagResponses.add(tagResponse);

        }
        return tagResponses;
    }


}
