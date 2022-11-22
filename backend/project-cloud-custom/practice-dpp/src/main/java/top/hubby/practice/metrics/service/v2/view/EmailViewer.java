package top.hubby.practice.metrics.service.v2.view;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.support.EmailSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-27 10:30 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class EmailViewer implements StatViewer {
    private EmailSender emailSender;
    private List toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender(/*省略参数*/ );
    }

    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(Map requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
