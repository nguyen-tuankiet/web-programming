package com.example.backend.controller.admin.tag;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Tag;
import com.example.backend.service.TagService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TagController", urlPatterns = {"/admin/api/tag/*"})
public class TagController extends HttpServlet {
    private final TagService tagService = new TagService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Tag> tags = tagService.getAllTags();
            writeResponse(response, new ResponseWrapper<>(200, "success", "Fetched all tags", tags));
        } else {
            try {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                Tag tag = tagService.getTagById(id);
                if (tag != null) {
                    writeResponse(response, new ResponseWrapper<>(200, "success", "Tag found", tag));
                } else {
                    writeResponse(response, new ResponseWrapper<>(404, "error", "Tag not found", null));
                }
            } catch (Exception e) {
                writeResponse(response, new ResponseWrapper<>(400, "error", "Invalid ID", null));
            }
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Map<String, String> data = parseJson(request);
//        String name = data.get("name");
//        if (name == null || name.isEmpty()) {
//            writeResponse(response, new ResponseWrapper<>(400, "error", "Name is required", null));
//            return;
//        }
//
//        Tag tag = tagService.createTag(name);
//        writeResponse(response, new ResponseWrapper<>(201, "success", "Tag created", tag));
//    }
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> data = parseJson(request);
    String name = data.get("name");
    boolean isActive = Boolean.parseBoolean(data.getOrDefault("isActive", "true")); // mới thêm

    if (name == null || name.isEmpty()) {
        writeResponse(response, new ResponseWrapper<>(400, "error", "Name is required", null));
        return;
    }

    Tag tag = tagService.createTag(name, isActive); // thêm tham số isActive
    writeResponse(response, new ResponseWrapper<>(201, "success", "Tag created", tag));
}


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getPathInfo().split("/")[1]);

            StringBuilder sb = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(sb.toString(), new TypeReference<>() {});

            if (data.containsKey("isActive")) {
                boolean isActive = (Boolean) data.get("isActive");
                tagService.updateTagStatus(id, isActive);
                writeResponse(response, new ResponseWrapper<>(200, "success", "Status updated", null));
            } else {
                String name = (String) data.get("name");
                tagService.updateTag(id, name);
                Tag updated = tagService.getTagById(id);
                writeResponse(response, new ResponseWrapper<>(200, "success", "Tag updated", updated));
            }
        } catch (Exception e) {
            writeResponse(response, new ResponseWrapper<>(400, "error", e.getMessage(), null));
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getPathInfo().split("/")[1]);
            Tag old = tagService.getTagById(id);
            if (old == null) throw new IllegalArgumentException("Tag not found");

            tagService.deleteTag(id);
            writeResponse(response, new ResponseWrapper<>(200, "success", "Tag deleted", old));
        } catch (Exception e) {
            writeResponse(response, new ResponseWrapper<>(400, "error", e.getMessage(), null));
        }
    }

    private Map<String, String> parseJson(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        return new ObjectMapper().readValue(sb.toString(), new TypeReference<>() {});
    }

    private void writeResponse(HttpServletResponse response, ResponseWrapper<?> data) throws IOException {
        response.setContentType("application/json");
        response.setStatus(data.getStatusCode());
        new ObjectMapper().writeValue(response.getWriter(), data);
    }
}

