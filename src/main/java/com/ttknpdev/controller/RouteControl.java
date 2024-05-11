package com.ttknpdev.controller;

import com.ttknpdev.logging.Logback;
import com.ttknpdev.repository.LineNotifyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
/*
digit time

17:59 -> 18:00 -> เที่ยงคืน 00:00:47 real time follow pc

ED433732392TH
*/
@RestController
public class RouteControl {

    private final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private LineNotifyRepo lineNotifyRepo;
    private Logback logback;

    @Autowired
    public RouteControl(LineNotifyRepo lineNotifyRepo) {
        logback = new Logback(RouteControl.class);
        this.lineNotifyRepo = lineNotifyRepo;
    }


    @GetMapping(value = "/server")
    public ResponseEntity<String> testServer() {
        return ResponseEntity.ok("ok");
    }


    @GetMapping(value = "/initial-reminder")
    private ResponseEntity<String> initial() throws Exception {

        logback.log.debug("user requested /initial-reminder");
        // for using while loop
        boolean condition = true;
        // prepare variables
        /*
          คีย์เวิร์ด var ของ Java ช่วยให้นักพัฒนาสามารถประกาศตัวแปรในเครื่องได้โดยไม่ต้องระบุประเภทข้อมูล เช่น int, long, String หรือ char
        */
        int day, hour,stickerPackageId = 11537 ,stickerId = 52002734;
        String currentTime, message;

        try {
            while (condition) {

                // any rounds will get current datetime
                currentTime = getSetOfCurrentDateTime()[0].toString();
                day = (int) getSetOfCurrentDateTime()[1];
                hour = (int) getSetOfCurrentDateTime()[2];

                // 10-05-2024 16:29:05
                logback.log.debug("currentTime : {}", currentTime);

                if (day != 15) {
                    // 11 , 12 , ... , 14
                    logback.log.debug("day : {}",day);
                    // if hour == 7 AM Do ...

                    if (hour == 7) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "**** 7 AM , Time to wake up **\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // stop all threads 1 hour
                        // ***** it closes before timing (18 minutes after request)
                        // TimeUnit.HOURS.sleep(1);
                        logback.log.debug("after sent message,sticker to line (About 7 AM)");
                    }
                    // if hour == 8 AM And < 11 AM Do ...
                    else if (hour >= 8 && hour < 11) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******* 8 AM - 11 AM *********\n" +
                                "****** Time to practice *******\n" +
                                "*********** Maths ************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // stop all threads 3 hours
                        // TimeUnit.HOURS.sleep(3);
                        logback.log.debug("after sent message,sticker to line (About 8 AM to 11 AM)");
                    }
                    // if hour >= 11 AM And < 13 PM Do ...
                    else if (hour >= 11 && hour < 13) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 11 AM - 13 PM *******\n" +
                                "********** Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // stop all threads 2 hours
                        // TimeUnit.HOURS.sleep(2);
                        logback.log.debug("after sent message,sticker to line (About 11 AM to 13 PM)");
                    }
                    // if hour == 13 PM Do ...
                    else if (hour == 13) {
                        message = "**********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 13 PM - 14 PM *******\n" +
                                "******* Time to reading *******\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // stop all threads 1 hour
                        // TimeUnit.HOURS.sleep(1);
                        logback.log.debug("after sent message,sticker to line (About 13 PM)");
                    }
                    // if hour >= 14 PM And < 18 PM Do ...
                    else if (hour >= 14 && hour < 18) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 14 PM - 18 PM *******\n" +
                                "****** Time to practice *******\n" +
                                "*********** Coding ***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // stop all threads 4 hours
                        // TimeUnit.HOURS.sleep(4);
                        logback.log.debug("after sent message to line (About 14 PM to 18 PM)");
                    }
                    // if hour == 18 PM Do ...
                    else if (hour == 18) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 18 PM - 19 PM *******\n" +
                                "********** Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // stop all threads 1 hour
                        // TimeUnit.HOURS.sleep(1);
                        logback.log.debug("after sent message,sticker to line (About 18 PM)");
                    }
                    // if hour >= 19 PM And < 21 PM Do ...
                    else if (hour >= 19 && hour < 21) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "****** 19 PM - 21 PM *********\n" +
                                "****** Time to practice *******\n" +
                                "************ Maths ***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // stop all threads 2 hours
                        // TimeUnit.HOURS.sleep(2);
                        logback.log.debug("after sent message,sticker to line (At 19 PM to 21 PM)");
                    }
                    //
                    else {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******* 21 PM - 7 AM *********\n" +
                                "********** Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId); // send message to line
                        // stop all threads 10 hours
                        // TimeUnit.HOURS.sleep(10);
                        // then it's new day
                        logback.log.debug("after sent message to line (At 21 PM to 7 AM)");
                    }
                    // 2 minutes work fine
                    TimeUnit.MINUTES.sleep(2);
                } // 11 , 12 , ... , 14
                else {
                    // 15
                    logback.log.debug("day : {} , Application is closing",day);
                    condition = false;
                }
            } // ended while loop
        } catch (InterruptedException interruptedException) {
            logback.log.debug("interrupted : {}",interruptedException);
        }
        return ResponseEntity.ok("ok");
    }

    /*
        // ******* Test Logic *********
        @GetMapping(value = "/initial-reminder2")
        private ResponseEntity<String> initial2() throws Exception {
            logback.log.info("requested /initial-reminder2 successfully");

            boolean condition = true;
            int day, hour,minute;
            String currentTime = "", message = "";

            while (condition) {

                // get datetime
                currentTime = getSetOfCurrentDateTime()[0].toString(); // 10-05-2024 16:29:05
                logback.log.debug("currentTime : {}", currentTime);
                day = (int) getSetOfCurrentDateTime()[1];
                hour = (int) getSetOfCurrentDateTime()[2];
                minute = (int) getSetOfCurrentDateTime()[3];

                if (minute != 46) {
                    // *********************************
                    logback.log.debug("today is {}",day);
                    if (minute == 30) { // >= 7 AM && <= 8 AM
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "** 7 AM , Time to wake up **\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(1); // stop all threads 1 hour
                        logback.log.debug("1 minute , after sent message to line (At 7 AM)");
                    }
                    else if (minute >= 31 && minute < 33) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "****** 8 AM - 11 AM *********\n" +
                                "****** Time to practice *******\n" +
                                "********* Maths **********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(2); // stop all threads 3 hours
                        logback.log.debug("2 minutes , after sent message to line (At 8 AM)");
                    }
                    else if (minute >= 33 && minute < 36) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******* 11 AM - 13 PM *******\n" +
                                "********** Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(3); // stop all threads 4 hours
                        logback.log.debug("3 minutes , after sent message to line (At 11 AM)");
                    }
                    else if (minute == 36) {
                        message = "**********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******* 13 PM - 14 PM *******\n" +
                                "****** Time to reading *******\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(1); // stop all threads 1 hour
                        logback.log.debug("1 minute , after sent message to line (At 13 PM)");
                    }
                    else if (minute >= 37 && minute < 39) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******* 14 PM - 18 PM *******\n" +
                                "****** Time to practice *******\n" +
                                "****** Coding *******\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(2); // stop all threads 4 hours
                        logback.log.debug("2 minutes , after sent message to line (At 14 PM)");
                    }
                    else if (minute == 39) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******* 18 PM - 19 PM *******\n" +
                                "********** Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(1); // stop all threads 1 hour
                        logback.log.debug("1 minutes , after sent message to line (At 18 PM)");
                    }
                    else if (minute >= 40 && minute < 43) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "****** 19 PM - 21 PM *********\n" +
                                "****** Time to practice *******\n" +
                                "********* Maths **********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(3); // stop all threads 1 hour
                        logback.log.debug("3 minutes , after sent message to line (At 19 PM)");
                    }
                    else { // >= 54 / >= 00:00 / >= 5

                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "****** 21 PM - 7 AM *********\n" +
                                "********** Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, 11539, 52114117); // send message to line
                        TimeUnit.MINUTES.sleep(3); // stop all threads 10 hours
                        logback.log.debug("3 minutes , after sent message to line (At 21 PM)");

                    }

                    logback.log.debug("time {}:{}",hour,minute);

                }
                else {
                    logback.log.debug("today is {} and closes application",day);
                    condition = false;
                }
            } // ended while loop
            return ResponseEntity.ok("ok");
        }
        */


    private Object[] getSetOfCurrentDateTime() {
        // have to set Zone id
        // cause in container it does not know time zone
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
        return new Object[]{
                DATETIME_FORMAT.format(currentTime),
                currentTime.getDayOfMonth(),
                currentTime.getHour(),
                currentTime.getMinute()
        };
    }

}
