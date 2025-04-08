package com.example.backend.service;

import com.example.backend.model.Banner;
import com.example.backend.model.DAO.BannerDAO;
import org.jdbi.v3.core.Jdbi;

import java.sql.Date;
import java.util.List;

import java.time.LocalDate;

public class BannerService {
    private final BannerDAO bannerDAO;

    public BannerService(Jdbi jdbi) {
        this.bannerDAO = jdbi.onDemand(BannerDAO.class);
    }

    public List<Banner> getAllBanners() {
        return bannerDAO.getAllBanners();
    }

    public Banner getBannerById(int id) {
        return bannerDAO.getBannerById(id);
    }

    public Banner createBanner(String status, String imageId, LocalDate startDate, LocalDate endDate) {
        int id = bannerDAO.createBanner(status, imageId, startDate, endDate);
        return getBannerById(id);
    }

    public void updateBanner(int id, String status, String imageId, LocalDate startDate, LocalDate endDate) {
        bannerDAO.updateBanner(id, status, imageId, startDate, endDate);
    }

    public void deleteBanner(int id) {
        bannerDAO.deleteBanner(id);
    }
}
