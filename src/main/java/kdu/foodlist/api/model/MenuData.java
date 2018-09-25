package kdu.foodlist.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ehay@naver.com on 2018-09-24
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Entity
public class MenuData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String month;
    private String date;
    private String day;

    private String time;
    private String campus;

    private String menu;

    public Long getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        if ("-".equals(menu)) {
            return month + "월 " +
                    date + "일 " +
                    day + "요일은 " +
                    "학교 식당을 운영하지 않습니다.";
        }
        return campus + " " +
                time + '\n' +
                menu;
    }
}
