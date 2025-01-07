package com.example.backend.service;

import com.example.backend.model.Variant;
import com.example.backend.model.DAO.VariantDAO;
import com.example.backend.model.VariantValue;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;

import java.util.List;

public class VariantService {
    private final VariantDAO variantDAO;

    public VariantService(Jdbi jdbi) {
        this.variantDAO = jdbi.onDemand(VariantDAO.class);
        jdbi.registerRowMapper(ConstructorMapper.factory(VariantValue.class));
    }

    public List<Variant> getAllVariants() {
        return variantDAO.getAllVariants();
    }

    public Variant getVariantById(Integer id) {
        return variantDAO.getVariantById(id);
    }

    public Variant createVariant(String name, Integer categoryId) {
        int id = variantDAO.createVariant(name, categoryId);
        return variantDAO.getVariantById(id);
    }

    public List<VariantValue> getVariantValuesByVariantId(Integer variantId) {
        return variantDAO.getVariantValuesByVariantId(variantId);
    }

    public List<Variant> getVariantsByCategory(Integer categoryId) {
        if (categoryId == null) {
            // Trả về toàn bộ danh sách nếu không có categoryId
            return variantDAO.getAllVariants();
        }
        return variantDAO.getVariantsByCategoryId(categoryId);
    }
//    public void updateVariant(Integer id, String name, Integer categoryId) {
//        variantDAO.updateVariant(id, name, categoryId);
//    }
//
//    public void deleteVariant(Integer id) {
//        variantDAO.deleteVariant(id);
//    }
}
