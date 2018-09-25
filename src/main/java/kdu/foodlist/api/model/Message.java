package kdu.foodlist.api.model;

/**
 * Created by ehay@naver.com on 2018-09-24
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

public class Message {

    private String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                '}';
    }
}
