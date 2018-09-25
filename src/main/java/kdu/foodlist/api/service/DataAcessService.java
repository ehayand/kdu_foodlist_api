package kdu.foodlist.api.service;

import kdu.foodlist.api.model.MenuData;
import kdu.foodlist.api.parsing.Parser;
import kdu.foodlist.api.repository.MenuDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    public Iterable<MenuData> findByDate(String month, String date) {
        return menuDataRepository.findByDate(month, date);
    }

    public void deleteAll() {
        menuDataRepository.deleteAll();
    }

    /**
     * cron = "초 분 시 일 월 주(년)"
     * 초 0-59 , - * /
     * 분 0-59 , - * /
     * 시 0-23 , - * /
     * 일 1-31 , - * ? / L W
     * 월 1-12 or JAN-DEC , - * /
     * 요일 1-7 or SUN-SAT , - * ? / L #
     * 년(옵션) 1970-2099 , - * /
     * : 모든 값
     * ? : 특정 값 없음
     * - : 범위 지정에 사용
     * , : 여러 값 지정 구분에 사용
     * / : 초기값과 증가치 설정에 사용
     * L : 지정할 수 있는 범위의 마지막 값
     * W : 월~금요일 또는 가장 가까운 월/금요일
     * # : 몇 번째 무슨 요일 2#1 => 첫 번째 월요일
     */
    // db 초기화 및 데이터 리셋
    @PostConstruct // setup  실행시 데이터 파싱 및 db 초기화
    @Scheduled(cron = "0 0 5 * * *")
    public void reset() {
        try {
            deleteAll();
            try {
                saveAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
