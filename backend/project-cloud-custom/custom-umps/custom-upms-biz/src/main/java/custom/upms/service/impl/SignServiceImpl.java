package custom.upms.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.api.utils.CommonQuery;
import common.api.utils.PageUtils;
import common.redis.utils.RedisUtil;
import custom.basic.api.entity.SignEntity;
import custom.upms.mapper.SignMapper;
import custom.upms.service.SignService;

import org.springframework.stereotype.Service;

import static custom.upms.redis.RedisSignEnum.SIGN_KEY;

/**
 * @author zack.zhang <br>
 * @create 2021-08-15 09:03:18 <br>
 * @project ware <br>
 */
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, SignEntity> implements SignService {

    @Resource private RedisUtil redisUtil;

    private static String buildSignKey(Long memberId, LocalDate date) {

        LocalDateTime sign = LocalDateTime.of(date, LocalTime.now());
        return String.format("member:sign:%d:%s", memberId, DateUtil.format(sign, "yyyyMM"));
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SignEntity> page =
                this.page(
                        new CommonQuery<SignEntity>().getPage(params),
                        new QueryWrapper<SignEntity>());

        return new PageUtils(page);
    }

    @Override
    public Map<String, Boolean> getSignInfo(Long memberId, LocalDate date) {
        // 构建 Key
        String signKey = buildSignKey(memberId, date);
        // 构建一个自动排序的 Map
        Map<String, Boolean> signInfo = new TreeMap<>();
        int dayOfMonth =
                DateUtil.lengthOfMonth(date.getMonthValue(), DateUtil.isLeapYear(date.getYear()));
        // bitfield user:sign:5:202011 u30 0
        long v = redisUtil.bitField(SIGN_KEY, 0, dayOfMonth, signKey).longValue();
        for (int i = dayOfMonth; i > 0; i--) {
            // 签到：  yyyy-MM-01 true
            // 未签到：yyyy-MM-01 false
            LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.now()).withDayOfMonth(i);
            signInfo.put(DateUtil.format(localDateTime, "yyyy-MM-dd"), (v & 1) == 1);
            v >>= 1;
        }
        return signInfo;
    }

    @Override
    public Long getSignCount(Long memberId, LocalDate date) {
        // 构建 Key
        String signKey = buildSignKey(memberId, date);

        return redisUtil.bitCount(SIGN_KEY, signKey);
    }

    @Override
    public Integer doSign(Long memberId, LocalDate date) {

        // 获取日期对应的天数，多少号
        int offset = date.getDayOfMonth() - 1;
        // 构建 Key user:sign:5:yyyyMM
        String signKey = buildSignKey(memberId, date);
        // 查看是否已签到
        boolean isSigned = redisUtil.getBit(SIGN_KEY, offset, signKey);
        if (isSigned) {
            return getContinuousSignCount(memberId, date);
        }

        // 签到
        redisUtil.setBit(SIGN_KEY, offset, signKey);
        return getContinuousSignCount(memberId, date);
    }

    private int getContinuousSignCount(Long memberId, LocalDate date) {

        // 获取日期对应的天数，多少号，假设是 30
        int offset = date.getDayOfMonth();
        // 构建 Key
        String signKey = buildSignKey(memberId, date);
        long value = redisUtil.bitField(SIGN_KEY, 0, offset, signKey).longValue();

        int signCount = 1;
        for (int i = offset; i > 0; i--) {
            if ((value & 1) == 0) {
                if (i != offset) {
                    break;
                }
            } else {
                signCount++;
            }
            offset >>= 1;
        }

        return signCount;
    }
}
