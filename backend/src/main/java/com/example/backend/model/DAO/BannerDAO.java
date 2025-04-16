package com.example.backend.model.DAO;

import com.example.backend.model.Banner;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(Banner.class)
public interface BannerDAO {
    @SqlQuery("SELECT * FROM banners")
    List<Banner> getAllBanners();

    @SqlQuery("SELECT * FROM banners WHERE id = :id")
    Banner getBannerById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO banners (status, imageId, startDate, endDate, isActive, description) " +
            "VALUES (:status, :imageId, :startDate, :endDate, :isActive, :description)")
    @GetGeneratedKeys("id")
    int createBanner(@Bind("status") String status,
                     @Bind("imageId") String imageId,
                     @Bind("startDate") LocalDate startDate,
                     @Bind("endDate") LocalDate endDate,
                     @Bind("isActive") boolean isActive,
                     @Bind("description") String description);


    @SqlUpdate("UPDATE banners SET status = :status, imageId = :imageId, startDate = :startDate, endDate = :endDate, description = :description WHERE id = :id")
    void updateBanner(@Bind("id") Integer id,
                      @Bind("status") String status,
                      @Bind("imageId") String imageId,
                      @Bind("startDate") LocalDate startDate,
                      @Bind("endDate") LocalDate endDate,
                      @Bind("description") String description);


    @SqlUpdate("DELETE FROM banners WHERE id = :id")
    void deleteBanner(@Bind("id") Integer id);

    @SqlUpdate("UPDATE banners SET isActive = :isActive WHERE id = :id")
    void updateBannerStatus(@Bind("id") Integer id, @Bind("isActive") boolean isActive);

}
