package com.example.inboxmail;

public class MailObject {
    String[] colors = {"#22dede", "#2299de", "#22de86", "#8dde22", "#de9022", "#de2b22", "#de227d"};

    private String name;
    private String subject;
    private String content;
    private boolean favorite;
    private String time;
    private String color;

    public MailObject(String name, String subject, String content, boolean favorite, String time, String color){
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.favorite = favorite;
        this.time = time;
        this.color = color;
    }

    public String getName(){
        return name;
    }
    public String getSubject(){
        return subject;
    }
    public String getContent(){
        return content;
    }
    public String getTime(){
        return time;
    }
    public String getColor(){
        return color;
    }
    public boolean getFavorite(){
        return favorite;
    }


}
