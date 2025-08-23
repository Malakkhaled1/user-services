package com.Ejada.SpringTask.application.scheduling;

import com.Ejada.SpringTask.application.models.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpdateDate {

    @Scheduled(cron = "0 */5 * * * *")
    public void updateUserStatus() {
        System.out.println("Date now is: " + new Date());
    }
}
