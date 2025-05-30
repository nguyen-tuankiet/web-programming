package com.example.backend.service;

import com.example.backend.model.DAO.OptionVariantValueDAO;
import com.example.backend.model.OptionVariantValue;
import com.example.backend.model.Options;
import org.jdbi.v3.core.Jdbi;

public class OptionVariantValueService {

    private final OptionVariantValueDAO optionVariantValueDAO;

    public OptionVariantValueService(Jdbi jdbi) {
        this.optionVariantValueDAO = jdbi.onDemand(OptionVariantValueDAO.class);
    }

    public int addOptionVariantValue(Integer optionId, Integer variantId) {
        return optionVariantValueDAO.addOptionVariantValue(optionId, variantId);
    }


    public OptionVariantValue getOptionById(Integer id) {
        return optionVariantValueDAO.getOptionVariantValueId(id);
    }
}
