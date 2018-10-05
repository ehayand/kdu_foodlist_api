package kdu.foodlist.api.model;

import java.util.Arrays;

/**
 * Created by ehay@naver.com on 2018-09-24
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

public class Keyboard {

    private String type;
    private String[] buttons;

    public Keyboard() {
        this.type = "text";
    }

    public Keyboard(String[] buttons) {
        this.type = "buttons";
        this.buttons = buttons;
    }

    public String getType() {
        return type;
    }

    public String[] getButtons() {
        return buttons;
    }

    public void setButtons(String[] buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "type='" + type + '\'' +
                ", buttons=" + Arrays.toString(buttons) +
                '}';
    }
}
