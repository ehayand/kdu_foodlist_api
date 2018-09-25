package kdu.foodlist.api.service;

import kdu.foodlist.api.model.MenuData;
import kdu.foodlist.api.parsing.Parser;
import kdu.foodlist.api.repository.MenuDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-09-24
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class DataAcessService {

    @Autowired
    MenuDataRepository menuDataRepository;

    public void saveAll() {
        Parser parser = new Parser();

        List<MenuData> menuDataList = parser.read();

        for (MenuData menuData : menuDataList) {
            menuDataRepository.save(menuData);
        }
    }

    public MenuData saveOne(MenuData menuData){
         return menuDataRepository.save(menuData);
    }

    public Iterable<MenuData> findByDate(String month, String date) {
        return menuDataRepository.findByDate(month, date);
    }

    public Iterable<MenuData> findAll() {
        return menuDataRepository.findAll();
    }

}
