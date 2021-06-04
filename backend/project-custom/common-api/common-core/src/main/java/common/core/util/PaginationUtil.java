package common.core.util;

import cn.hutool.core.util.PageUtil;
import common.core.model.Pagination;

import java.util.Optional;

/**
 * @author zack <br>
 * @create 2020-08-16 22:36 <br>
 * @project common-core <br>
 */
public final class PaginationUtil {
    public static Pagination buildPagination(Integer total, Integer pageSize, Integer currentPage) {
        pageSize = Optional.ofNullable(pageSize).orElse(20);
        currentPage = Optional.ofNullable(currentPage).orElse(1);
        Integer pageCount = PageUtil.totalPage(total, pageSize);

        if (currentPage > pageCount) {
            currentPage = 1;
        }

        return new Pagination(total, pageCount, currentPage, pageSize);
    }
}
