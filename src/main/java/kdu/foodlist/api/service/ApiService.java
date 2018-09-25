package kdu.foodlist.api.service;

import kdu.foodlist.api.model.Keyboard;
import kdu.foodlist.api.model.MenuData;
import kdu.foodlist.api.model.Message;
import kdu.foodlist.api.parsing.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    // 삭제 예정
    @GetMapping(value = "testdata")
    public void insertTestData() {
        dataAcessService.saveAll();
    }

    // 삭제 예정
    @PostMapping(value = "/testOne")
    public MenuData insertTestOne(@RequestBody Map<String, String> body){
        List<String> raw = new ArrayList<>();
        raw.add(body.get("date"));
        raw.add(body.get("title"));
        raw.add(body.get("content"));

        Parser parser = new Parser();

        return parser.process(raw);
    }

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
        String user_key = body.get("user_key");
        String type = body.get("type");
        String content = body.get("content");

        Message message;
        Keyboard keyboard;

        // 로직
        Date day;
        String text = "";

        if ("내일".equals(content)) {
            Date today = new Date();
            day = new Date(today.getTime() + (long) (1000 * 60 * 60 * 24));

            text += "내일 학식정보 입니다.\n";
        } else {
            day = new Date();

            text += "오늘 학식정보 입니다.\n";
        }

        String month = String.valueOf(day.getMonth());
        String date = String.valueOf(day.getDate());


        for (MenuData menuData : dataAcessService.findByDate(month, date)) {
            text += menuData.toString();
            text += "\n\n";
        }

//        for (MenuData menuData : dataAcessService.findByDate(month, date)){
//            text += menuData.toString();
//            text += "\n";
//        }


        message = new Message(text);
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
