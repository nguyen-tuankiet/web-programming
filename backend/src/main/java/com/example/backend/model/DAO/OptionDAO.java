package com.example.backend.model.DAO;

import com.example.backend.model.Options;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;


@RegisterConstructorMapper(Options.class)
public interface OptionDAO {

    @SqlUpdate("INSERT INTO options (productId, price, stock) VALUES (:productId, :price, :stock)")
    @GetGeneratedKeys
    int createOption(@Bind("productId") Integer productId, @Bind("price") Integer price, @Bind("stock") Integer stock);


    @SqlQuery(value = "select *\n" +
            "from options\n" +
            "where id = :id;")
    Options getOptionById(@Bind("id") Integer id);



    @SqlUpdate(value = "update options\n" +
            "set\n" +
            "    stock = :stock " +
            "where id = :id")
    Boolean updateStock(@Bind("id") Integer id, @Bind("stock") Integer stock);



    @SqlQuery(value = "select *\n" +
            "from options\n" +
            "where productId = :productId")
    List<Options> getOptionsByProductId(@Bind("productId") Integer productId);





    @SqlQuery(value = "select\n" +
            "    o.id as id, o.productId, o.price, o.stock,\n" +
            "\n" +
            "    v.id as variantId, v.name as variantName,\n" +
            "    vv.value as variantValue \n" +
            "from\n" +
            "    options as o\n" +
            "    inner join  option_variant_value as ovv\n" +
            "         on o.id = ovv.optionId\n" +
            "    inner join variant_value as vv\n" +
            "        on ovv.variantValueId = vv.id\n" +
            "    inner join variant as  v\n" +
            "        on v.id = vv.variantId\n" +
            "where o.id in (<optionIds>)\n")
    List<Options> getVariantByOptionId(@BindList("optionIds") List<Integer> optionIds);




}