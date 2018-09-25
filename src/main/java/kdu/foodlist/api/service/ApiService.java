package kdu.foodlist.api.service;

import kdu.foodlist.api.model.Keyboard;
import kdu.foodlist.api.model.MenuData;
import kdu.foodlist.api.model.Message;
import kdu.foodlist.api.parsing.Parser;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ehay@naver.com on 2018-09-24
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@RestController
@RequestMapping(value = "/kdu/foodlist")
public class ApiService {

    private Keyboard keyboard;

    @Autowired
    private DataAcessService dataAcessService;

    @GetMapping(value = "/keyboard")
    public Keyboard keyboard() {
        if (this.keyboard == null) {
            String[] buttons = {"오늘", "내일"};
            this.keyboard = new Keyboard(buttons);
        }

        return this.keyboard;
    }

    @PostMapping(value = "/message")
    public Map<String, Object> message(@RequestBody Map<String, String> body) {
        // user_key 값을 저장, 사용하지 않음
        String user_key = body.get("user_key");
        String type = body.get("type");
        String content = body.get("content");

        Message message;
        Keyboard keyboard;

        // 로직
        DateTime theDay = new DateTime();

        StringBuilder text = new StringBuilder();
        String month;
        String date;

        if ("내일".equals(content)) {
            DateTime nextDay = theDay.plusDays(1);

            month = String.valueOf(nextDay.getMonthOfYear());
            date = String.valueOf(nextDay.getDayOfMonth());

            text.append("내일 학식정보 입니다.\n");
        } else {
            month = String.valueOf(theDay.getMonthOfYear());
            date = String.valueOf(theDay.getDayOfMonth());

            text.append("오늘 학식정보 입니다.\n");
        }

        for (MenuData menuData : dataAcessService.findByDate(month, date)) {
            text.append(menuData.toString()).append("\n\n");
        }

        message = new Message(text.toString());
        keyboard = keyboard();
        //

        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("keyboard", keyboard);

        return map;
    }

    @PostMapping(value = "/friend")
    public HttpStatus insertUser(@RequestBody String user_key) {

        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/friend")
    public HttpStatus deleteUser(@RequestBody String user_key) {

        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/chat_room/{user_key}")
    public HttpStatus checkout(@PathVariable String user_key) {

        return HttpStatus.OK;
    }

}
