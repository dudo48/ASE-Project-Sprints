package com.notification.rest.model;

import java.util.Random;

public class MailGateWay implements IGateWay {
    @Override
    public void send(Notification n) {

        Random rand = new Random();
        boolean status= rand.nextBoolean();
        n.setStatus(status);

        System.out.println("\n \nmail visited "+status+ " \n");
    }
}
