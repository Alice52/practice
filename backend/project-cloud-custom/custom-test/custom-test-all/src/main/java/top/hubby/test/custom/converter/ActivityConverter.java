package top.hubby.test.custom.converter;

import top.hubby.test.custom.model.dto.ActivityDTO;
import top.hubby.test.custom.model.entity.Activity;
import top.hubby.test.custom.model.vo.ActivityVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.annotation.Nullable;

/**
 * @author zack <br>
 * @create 2021-06-03 10:43 <br>
 * @project custom-test <br>
 */
@Mapper
public interface ActivityConverter {

    ActivityConverter CONVERTER = Mappers.getMapper(ActivityConverter.class);

    /**
     * Convert dto to po.
     *
     * @param dto
     * @return
     */
    @Nullable
    Activity dto2po(@Nullable ActivityDTO dto);

    /**
     * Convert dto to vo.
     *
     * @param dto
     * @return
     */
    @Nullable
    ActivityVO dto2vo(@Nullable ActivityDTO dto);

    /**
     * Convert dto to vo.
     *
     * @param po
     * @return
     */
    @Nullable
    ActivityVO po2vo(@Nullable Activity po);
}
