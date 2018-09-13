package com.sham.demo.server;

import com.sham.common.annotation.Initialize;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DemoService {
    @Initialize
    public void init2() {
        System.out.println(456);
    }

    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
    }
}
