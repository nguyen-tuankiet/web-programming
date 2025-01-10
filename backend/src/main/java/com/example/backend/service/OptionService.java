package com.example.backend.service;

import com.example.backend.model.DAO.OptionDAO;
import org.jdbi.v3.core.Jdbi;

public class OptionService {
    private final OptionDAO optionDao;

    public OptionService(Jdbi jdbi) {
        this.optionDao = jdbi.onDemand(OptionDAO.class);
    }

    public int createOptions(Integer productId, Integer price, Integer stock) {
//        return optionDao.createOption(productId, price, stock);
        return 0;

    }

}
