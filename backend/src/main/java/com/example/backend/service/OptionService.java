package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.OptionDAO;
import com.example.backend.model.Options;
import org.jdbi.v3.core.Jdbi;

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


    public static void main(String[] args) {
        OptionService  optionService = new OptionService(DBConnection.getJdbi());
        System.out.println(optionService.getOptionById(1));
        System.out.println(optionService.updateStock(1, 200));
    }




}
