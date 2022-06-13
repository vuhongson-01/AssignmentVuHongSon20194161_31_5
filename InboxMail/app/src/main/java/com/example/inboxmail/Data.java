package com.example.inboxmail;

import java.util.ArrayList;

public class Data {
    String[] colors = {"#22dede", "#2299de", "#22de86", "#8dde22", "#de9022", "#de2b22", "#de22numOfColord"};
    int numOfColor = 7;
    public ArrayList<MailObject> ListMail = new ArrayList<MailObject>();

    public Data(){
        ListMail.add(new MailObject(
                "Top Dev",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Google",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Apple",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Facebook",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Canva",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Kahoot!",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Instagram",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Youtube",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Grab",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Shopee",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
        ListMail.add(new MailObject(
                "Lazada",
                "13/6's News!",
                "This is 13/6's News",
                false,
                "13/6",
                colors[((int)Math.random() * 100) % numOfColor]
        ));
    }
}
