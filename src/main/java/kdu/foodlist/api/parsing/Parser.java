package kdu.foodlist.api.parsing;

import kdu.foodlist.api.model.MenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehay@naver.com on 2018-09-24
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

public class Parser {

    public Parser() {
    }

    public List<MenuData> read() {

        final String ROOT_URL = "http://dorm.kdu.ac.kr";
        final String TARGET_URL = "/CmsHome/open_02.aspx";
        final String DEV_PROP = "C:/selenium/chromedriver.exe";
        final String REAL_PROP = "/usr/bin/chromedriver";

        List<MenuData> processed = new ArrayList<>();

        try {
//            System.setProperty("webdriver.chrome.driver", DEV_PROP);
//            WebDriver driver = new ChromeDriver();
            System.setProperty("webdriver.chrome.driver", REAL_PROP);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--no-sandbox");

            WebDriver driver = new ChromeDriver(chromeOptions);

            driver.get(ROOT_URL + TARGET_URL);

            Thread.sleep(2000);

            WebElement tbody = driver.findElement(By.xpath("//*[@id=\"Board_LISTD\"]"));

            List<WebElement> articles = tbody.findElements(By.tagName("tr"));
            String date = null;
            for (WebElement tr : articles) {
                List<String> raw = new ArrayList<>();
                List<WebElement> tds = tr.findElements(By.tagName("td"));
                if (tds.size() < 3) raw.add(date);

                for (WebElement td : tds) {
                    List<WebElement> attributes = td.findElements(By.tagName("span"));
                    raw.add(attributes.get(0).getText());
                }

                date = raw.get(0);

                MenuData menuData = process(raw);
                processed.add(menuData);
            }

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return processed;
    }

    public MenuData process(List<String> rawData) {
        MenuData menuData = new MenuData();

        //month + date + day  ex) 9.24 월
        String[] date = rawData.get(0).replace(".", " ").split(" ");
        menuData.setMonth(date[0]);
        menuData.setDate(date[1]);
        menuData.setDay(date[2]);

        //time + campus  ex) 중식1_국제학사 or null
        String[] title;
        if (rawData.get(1).indexOf("_") != -1) {
            title = rawData.get(1).split("_");
        } else {
            title = "- -".split(" ");
        }
        menuData.setTime(title[0]);
        menuData.setCampus(title[1]);

        //menu
        String content;
        if (rawData.get(2).length() > 1) {
            content = rawData.get(2);
        } else {
            content = "-";
        }
        menuData.setMenu(content);

        return menuData;
    }
}
