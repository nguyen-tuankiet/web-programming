package com.example.backend.service;

import com.example.backend.model.DAO.OptionVariantValueDAO;
import com.example.backend.model.OptionVariantValue;
import org.jdbi.v3.core.Jdbi;

public class OptionVariantValueService {

    private final OptionVariantValueDAO optionVariantValueDAO;

    public OptionVariantValueService(Jdbi jdbi) {
        this.optionVariantValueDAO = jdbi.onDemand(OptionVariantValueDAO.class);
    }

    public int addOptionVariantValue(OptionVariantValue optionVariantValue) {
        return optionVariantValueDAO.addOptionVariantValue(optionVariantValue.getOptionId(), optionVariantValue.getVariantValueId());
    }
}
