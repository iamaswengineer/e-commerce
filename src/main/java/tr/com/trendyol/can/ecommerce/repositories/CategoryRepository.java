package tr.com.trendyol.can.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.trendyol.can.ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select cc from Category cc where cc.id = :id")
    Category findBy(@Param("id")Long id);

    @Query("select cc from Category cc where cc.title = :title")
    Category findByTitle(@Param("title")String title);
}
