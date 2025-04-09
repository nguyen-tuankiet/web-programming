package com.example.backend.service;

import com.example.backend.model.DAO.TagDAO;
import com.example.backend.model.Tag;
import org.jdbi.v3.core.Jdbi;
import com.example.backend.model.TagWithCount;


import java.util.List;

public class TagService {
    private final TagDAO tagDAO;

    public TagService(Jdbi jdbi) {
        this.tagDAO = jdbi.onDemand(TagDAO.class);
    }

    public List<Tag> getAllTags() {
        return tagDAO.getAllTags();
    }

    public Tag getTagById(int id) {
        return tagDAO.getTagById(id);
    }

//    public Tag createTag(String name) {
//        int newId = tagDAO.createTag(name);
//        return tagDAO.getTagById(newId);
//    }
    public Tag createTag(String name, boolean isActive) {
        int newId = tagDAO.createTag(name, isActive);
        return tagDAO.getTagById(newId);
    }


    public void updateTag(int id, String name) {
        tagDAO.updateTag(id, name);
    }

    public void updateTagStatus(int id, boolean isActive) {
        tagDAO.updateTagStatus(id, isActive);
    }


    public void deleteTag(int id) {
        tagDAO.deleteTag(id);
    }
    public List<TagWithCount> getTagsWithProductCount() {
        return tagDAO.getTagsWithProductCount();
    }

}
