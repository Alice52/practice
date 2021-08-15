package custom.upms.converter;

import custom.basic.api.entity.MemberEntity;
import custom.basic.api.vo.MemberVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Mapper
public interface MemberConverter {
    MemberConverter INSTANCE = Mappers.getMapper(MemberConverter.class);

    /**
     * Convert vo to po.
     *
     * @param vo
     * @return
     */
    MemberEntity vo2po(MemberVO vo);

    /**
     * Convert po to vo.
     *
     * @param po
     * @return
     */
    MemberVO po2vo(MemberEntity po);

    /**
     * Convert pos to vos.
     *
     * @param pos
     * @return
     */
    List<MemberVO> pos2vos(List<MemberEntity> pos);

    /**
     * Convert vos to pos.
     *
     * @param vos
     * @return
     */
    List<MemberEntity> vos2pos(List<MemberVO> vos);
}
