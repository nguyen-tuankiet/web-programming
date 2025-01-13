package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.OptionDAO;
import com.example.backend.model.Options;
import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;

public class OptionService {
    private final OptionDAO optionDao;

    public OptionService(Jdbi jdbi) {
        this.optionDao = jdbi.onDemand(OptionDAO.class);
    }

    public int createOptions(Integer productId, Integer price, Integer stock) {
        return optionDao.createOption(productId, price, stock);
    }


    public Options getOptionById(Integer id) {
        return optionDao.getOptionById(id);
    }


    public Boolean updateStock(Integer id, Integer stock) {
        return optionDao.updateStock(id, stock);
    }


    public List<Options> getVariantByOptionId(List<Integer> optionIds) {
        return optionDao.getVariantByOptionId(optionIds);
    }


    public List<Options> getOptionsByProductId(Integer productId) {
        return optionDao.getOptionsByProductId(productId);
    }



    public static void main(String[] args) {
        OptionService  optionService = new OptionService(DBConnection.getJdbi());

        System.out.println(optionService.getOptionsByProductId(1));



    }




}
