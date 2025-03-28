package com.example.backend.model.DAO;


import com.example.backend.model.Review;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterConstructorMapper(Review.class)
public interface ReivewDAO {

    @SqlUpdate(value =
            "INSERT INTO review (userId, productId, orderid, rating, description) " +
                    "VALUES (:userId, :productId, :orderId, :rating, :description)")

    Boolean addReview(@Bind("userId") Integer userId,
                      @Bind("productId") Integer productId,
                      @Bind("orderId") Integer orderId,
                      @Bind("rating") Integer rating,
                      @Bind("description") String description

                      );

}
