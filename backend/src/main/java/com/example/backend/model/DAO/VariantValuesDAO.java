package com.example.backend.model.DAO;

import com.example.backend.model.Variant;
import com.example.backend.model.VariantValue;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterConstructorMapper(VariantValue.class)
public interface VariantValuesDAO {

    @SqlQuery("SELECT *  " +
            "FROM variant_value as vl  " +
            "WHERE vl.variantId = :variantId")
    List<VariantValue> getByVariantId(@Bind("variantId") Integer variantId);
}
