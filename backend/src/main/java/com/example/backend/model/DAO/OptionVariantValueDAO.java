package com.example.backend.model.DAO;


import com.example.backend.model.OptionVariantValue;
import com.example.backend.model.Options;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;

@RegisterConstructorMapper(OptionVariantValue.class)
public interface OptionVariantValueDAO {

    @SqlUpdate("INSERT INTO option_variant_value (optionId, variantValueId) VALUES (:optionId, :variantValueId)")
    @GetGeneratedKeys
    int addOptionVariantValue(@Bind("optionId") Integer optionId, @Bind("variantValueId") Integer variantValueId);


    @SqlQuery(value = "select *\n" +
            "from option_variant_value\n" +
            "where id = :id;")
    OptionVariantValue getOptionVariantValueId(@Bind("id") Integer id);

}