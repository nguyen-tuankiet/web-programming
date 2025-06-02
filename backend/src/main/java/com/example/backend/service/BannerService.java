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

    public Banner createBanner(String title, String imageId, LocalDate startDate, LocalDate endDate, boolean isActive, String description) {
        int id = bannerDAO.createBanner(title, imageId, startDate, endDate, isActive, description);
        return getBannerById(id);
    }

    public void updateBanner(int id, String title, String imageId, LocalDate startDate, LocalDate endDate, String description) {
        bannerDAO.updateBanner(id, title, imageId, startDate, endDate, description);
    }

    public void toggleBannerStatus(int id, boolean isActive) {
        bannerDAO.updateBannerTitle(id, isActive);
    }


    public void deleteBanner(int id) {
        bannerDAO.deleteBanner(id);
    }
}
