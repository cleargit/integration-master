package com.sham.email.queue;

import com.sham.common.dto.EmailData;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class EmailQueue {

    public static final BlockingDeque<EmailData> queue=new LinkedBlockingDeque<>(100);

    public static void push(EmailData data){
        if (data!=null){
            queue.push(data);
        }
    }
    public EmailData consume() throws InterruptedException {
        return queue.take();
    }

}
