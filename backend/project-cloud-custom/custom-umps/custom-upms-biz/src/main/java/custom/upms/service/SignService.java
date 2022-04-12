package custom.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import common.api.utils.PageUtils;
import custom.basic.api.entity.SignEntity;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author zack.zhang <br>
 * @create 2021-08-15 09:03:18 <br>
 * @project ware <br>
 */
public interface SignService extends IService<SignEntity> {

    /**
     * Common query.
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * Get sign info monthly.
     *
     * @param memberId
     * @param monthDate
     * @return
     */
    Map<String, Boolean> getSignInfo(Long memberId, LocalDate monthDate);

    /**
     * Get sign count of specified month.
     *
     * @param memberId
     * @param monthDate
     * @return
     */
    Long getSignCount(Long memberId, LocalDate monthDate);

    /**
     * Do sign, and can sign previous date.
     *
     * @param memberId
     * @param date
     * @return
     */
    Integer doSign(Long memberId, LocalDate date);
}
