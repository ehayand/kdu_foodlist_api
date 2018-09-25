package kdu.foodlist.api.repository;

import kdu.foodlist.api.model.MenuData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ehay@naver.com on 2018-09-24
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

public interface MenuDataRepository extends CrudRepository<MenuData, Long> {

    @Query("SELECT data FROM MenuData data WHERE data.month = :month OR data.date = :date")
    Iterable<MenuData> findByDate(@Param("month") String month, @Param("date") String date);

}
