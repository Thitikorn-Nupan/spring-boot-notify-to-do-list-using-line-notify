package com.ttknpdev.controller;

import com.ttknpdev.logging.Logback;
import com.ttknpdev.repository.LineNotifyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
    Note , render sleeps free service server after 15 minute
*/
@RestController
@RequestMapping(value = "/app")
public class RouteControl {

    private final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final LineNotifyRepo lineNotifyRepo;
    private final Logback logback;

    /**
      my solution for active server
    */

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
        logback.log.info("user requested /initial-reminder");
        // for using while loop
        boolean condition = true;
        int day , hour , stickerPackageId = 11537 , stickerId = 52002734;
        String currentTime , message;
        while (condition) {
            try {
                // any rounds will get set of current datetime
                currentTime = getSetOfCurrentDateTime()[0].toString();
                day = (int) getSetOfCurrentDateTime()[1];
                hour = (int) getSetOfCurrentDateTime()[2];
                // Ex, 10-05-2024 16:29:05
                logback.log.debug("currentTime : {}", currentTime);
                // days 11 , 12 , ... , 14
                if (day != 15) {
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
                        // ****** new solution 1 hour
                        TimeUnit.MINUTES.sleep(30);
                        // requestRenderServiceServer.requestRenderServer();
                        logback.log.debug("after 30 minutes sent message,sticker to line (About 7 AM)");
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        TimeUnit.MINUTES.sleep(30);
                    }
                    // if hour == 8 AM And < 10 AM Do ...
                    else if (hour >= 8 && hour < 10) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******* 8 AM - 10 AM *********\n" +
                                "****** Time to practice *******\n" +
                                "*********** Coding ***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** new solution 1 hour
                        TimeUnit.HOURS.sleep(1);
                        // requestRenderServiceServer.requestRenderServer();
                        logback.log.debug("after 1 hour sent message,sticker to line (About 9 AM)");
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        TimeUnit.HOURS.sleep(1); // (10 AM)
                    }
                    // if hour >= 10 AM And < 13 PM Do ...
                    else if (hour == 10) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 10 AM - 11 AM *******\n" +
                                "******* Time to reading *******\n" +
                                "******* Whatever book *******\n" +
                                "********** You want **********\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** 1 hour
                        TimeUnit.HOURS.sleep(1);
                    }
                    // if hour == 13 PM Do ...
                    else if (hour >= 11 && hour < 13) {
                        message = "**********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 11 AM - 13 PM *******\n" +
                                "********* Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        TimeUnit.HOURS.sleep(1);
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        TimeUnit.HOURS.sleep(1);
                    }

                    // if hour >= 13 PM And < 17 PM Do ...
                    else if (hour >= 13 && hour < 17) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 13 PM - 17 PM *******\n" +
                                "****** Time to practice *******\n" +
                                "*********** Coding ***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** 1 hour
                        TimeUnit.HOURS.sleep(1);
                        // requestRenderServiceServer.requestRenderServer();
                        logback.log.debug("after 1 hour sent message,sticker to line (About 14 PM)");
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** 2 hour
                        TimeUnit.HOURS.sleep(1);
                        // requestRenderServiceServer.requestRenderServer();
                        logback.log.debug("after 2 hour sent message,sticker to line (About 15 PM)");
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** 3 hour
                        TimeUnit.HOURS.sleep(1);
                        // requestRenderServiceServer.requestRenderServer();
                        logback.log.debug("after 3 hour sent message,sticker to line (About 16 PM)");
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** 4 hour
                        TimeUnit.HOURS.sleep(1);
                        // requestRenderServiceServer.requestRenderServer();
                        logback.log.debug("after 4 hour sent message,sticker to line (About 17 PM)");
                        //lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                    }
                    // if hour == 18 PM Do ...
                    else if (hour == 17) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "******** 17 PM - 18 PM *******\n" +
                                "********** Break time *********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        TimeUnit.HOURS.sleep(1);
                    }
                    // if hour >= 19 PM And < 21 PM Do ...
                    else if (hour == 18) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "****** 18 PM - 19 PM *********\n" +
                                "****** Time to exercise *******\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** new solution 1 hour
                        TimeUnit.MINUTES.sleep(30);
                        // requestRenderServiceServer.requestRenderServer();
                        logback.log.debug("after 30 minutes sent message,sticker to line (About 18:30 PM)");
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        TimeUnit.MINUTES.sleep(30);

                    }
                    else if (hour >= 19 && hour < 21) {
                        message = "***********\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "****** 19 PM - 21 PM *********\n" +
                                "****** Coding or Reading *****\n" +
                                "********* Choose once *******\n" +
                                "*******************************\n" +
                                "*******************************\n" +
                                "*******************************";
                        // send message,sticker to line
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        // ****** new solution 1 hour
                        TimeUnit.HOURS.sleep(1);
                        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
                        TimeUnit.HOURS.sleep(1);
                    }
                    // other case 21 PM - 7 AM *** 10 hours
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
                        // ****** 1 hour
                        TimeUnit.HOURS.sleep(1);
                        logback.log.debug("after 1 hour sent message,sticker to line (About 21 PM)");
                        TimeUnit.HOURS.sleep(3);
                        logback.log.debug("after 3 hour sent message,sticker to line (About 21 PM)");
                        TimeUnit.HOURS.sleep(3);
                        logback.log.debug("after 3 hour sent message,sticker to line (About 21 PM)");
                        TimeUnit.HOURS.sleep(3);
                        logback.log.debug("after 3 hour sent message,sticker to line (About 21 PM)");
                        // day is gonna changed
                    }
                }
                // day 15 change condition to be false
                else {
                    logback.log.info("day : {} , Application is gonna close",day);
                    condition = false;
                }
            }
            catch (Exception exception) {
                // catch some error if theads got a wrong
                logback.log.debug("exception : {}",exception);
            }
        } // ended while loop
        return ResponseEntity.ok("ok");
    }


    @GetMapping(value = "/test-logic")
    private ResponseEntity<String> testLogic() throws Exception {
        int day , hour , stickerPackageId = 11537 , stickerId = 52002734;
        String message = "***********\n" +
                "*******************************\n" +
                "*******************************\n" +
                "*******************************\n" +
                "****** 19 PM - 21 PM *********\n" +
                "****** Coding or Reading *****\n" +
                "********* Choose once *******\n" +
                "*******************************\n" +
                "*******************************\n" +
                "*******************************";
        // send message,sticker to line
        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
        message = "***********\n" +
                "*******************************\n" +
                "*******************************\n" +
                "******** 10 AM - 11 AM *******\n" +
                "******* Time to reading *******\n" +
                "******* Whatever book *******\n" +
                "********** You want **********\n" +
                "*******************************\n" +
                "*******************************";
        lineNotifyRepo.sendLineNotifyMessageAndSticker(message, stickerPackageId, stickerId);
        return ResponseEntity.ok("ok");
    }

    private Object[] getSetOfCurrentDateTime() {
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
