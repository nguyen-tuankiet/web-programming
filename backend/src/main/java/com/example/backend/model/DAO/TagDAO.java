package com.example.backend.model.DAO;

import com.example.backend.model.Tag;
import com.example.backend.model.TagWithCount;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Tag.class)
public interface TagDAO {

    @SqlQuery("SELECT * FROM tags")
    List<Tag> getAllTags();

    @SqlQuery("SELECT * FROM tags WHERE id = :id")
    Tag getTagById(@Bind("id") int id);

    @SqlUpdate("INSERT INTO tags (name) VALUES (:name)")
    @GetGeneratedKeys
    int createTag(@Bind("name") String name);

    @SqlUpdate("UPDATE tags SET name = :name WHERE id = :id")
    void updateTag(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("UPDATE tags SET isActive = :isActive WHERE id = :id")
    void updateTagStatus(@Bind("id") int id, @Bind("isActive") boolean isActive);

    @SqlUpdate("INSERT INTO tags (name, isActive) VALUES (:name, :isActive)")
    @GetGeneratedKeys
    int createTag(@Bind("name") String name, @Bind("isActive") boolean isActive);


    @SqlUpdate("DELETE FROM tags WHERE id = :id")
    void deleteTag(@Bind("id") int id);
    @SqlQuery("""
    SELECT 
        t.id,
        t.name,
        t.isActive,
        COUNT(pt.productId) AS totalProducts
    FROM tags t
    LEFT JOIN product_tag pt ON t.id = pt.tagId
    GROUP BY t.id, t.name, t.isActive
""")
    @RegisterConstructorMapper(TagWithCount.class)
    List<TagWithCount> getTagsWithProductCount();
}
