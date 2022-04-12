package custom.upms.converter;

import custom.basic.api.entity.SignEntity;
import custom.basic.api.vo.SignVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Mapper
public interface SignConverter {
    SignConverter INSTANCE = Mappers.getMapper(SignConverter.class);

    /**
     * Convert vo to po.
     *
     * @param vo
     * @return
     */
    SignEntity vo2po(SignVO vo);

    /**
     * Convert po to vo.
     *
     * @param po
     * @return
     */
    SignVO po2vo(SignEntity po);

    /**
     * Convert pos to vos.
     *
     * @param pos
     * @return
     */
    List<SignVO> pos2vos(List<SignEntity> pos);

    /**
     * Convert vos to pos.
     *
     * @param vos
     * @return
     */
    List<SignEntity> vos2pos(List<SignVO> vos);
}
