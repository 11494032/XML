package com.example.administrator.xml_gen_par;

/**
 * Created by Administrator on 2016/8/29.
 */
public class Sms {
    private String address;
    private String body;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "address='" + address + '\'' +
                ", body='" + body + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
