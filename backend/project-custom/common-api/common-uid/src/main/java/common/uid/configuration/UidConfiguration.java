package common.uid.configuration;

import common.uid.impl.CachedUidGenerator;
import common.uid.impl.DefaultUidGenerator;
import common.uid.worker.DisposableWorkerIdAssigner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author zack <br>
 * @create 2021-06-23<br>
 * @project project-custom <br>
 */
@Slf4j
@Configuration
public class UidConfiguration {

    // TODO: extra this config to properties
    @Value("${common.uid.time-bit:29}")
    private Integer timeBits;

    @Value("${common.uid.work-bit:21}")
    private Integer workerBits;

    @Value("${common.uid.seq-bit:13}")
    private Integer seqBits;

    @Value("${common.uid.epoch-str:2016-09-20}")
    private String epochStr;

    @Bean
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    @Lazy(value = false)
    @Bean
    public DefaultUidGenerator defaultUidGenerator() {

        DefaultUidGenerator uidGenerator = new DefaultUidGenerator();
        uidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
        uidGenerator.setTimeBits(timeBits);
        uidGenerator.setEpochStr(epochStr);
        uidGenerator.setSeqBits(seqBits);
        uidGenerator.setWorkerBits(workerBits);

        return uidGenerator;
    }

    @Bean
    public CachedUidGenerator cachedUidGenerator() {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
        cachedUidGenerator.setBoostPower(3);
        cachedUidGenerator.setScheduleInterval(60);

        return cachedUidGenerator;
    }
}
