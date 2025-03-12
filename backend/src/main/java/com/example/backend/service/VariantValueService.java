package com.example.backend.service;

import com.example.backend.model.DAO.VariantValuesDAO;
import com.example.backend.model.VariantValue;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class VariantValueService {

    private VariantValuesDAO variantValuesDAO;


    public VariantValueService(Jdbi jdbi) {
        this.variantValuesDAO = jdbi.onDemand(VariantValuesDAO.class);
    }


    public List<VariantValue> getVariantValuesByVariantId(Integer variantId) {
        return variantValuesDAO.getByVariantId(variantId);
    }
}
