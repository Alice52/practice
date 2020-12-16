package cn.edu.ntu.common.api.test.service.impl;

import cn.edu.ntu.common.api.test.common.QueryData;
import cn.edu.ntu.common.api.test.common.QueryPage;
import cn.edu.ntu.common.api.test.enums.BusinessResponseEnum;
import cn.edu.ntu.common.api.test.mapper.LicenceMapper;
import cn.edu.ntu.common.api.test.model.Licence;
import cn.edu.ntu.common.api.test.model.SimpleLicenceDTO;
import cn.edu.ntu.common.api.test.model.param.LicenceParam;
import cn.edu.ntu.common.api.test.service.ILicenceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Service
public class LicenceService extends ServiceImpl<LicenceMapper, Licence> implements ILicenceService {

  @Override
  public SimpleLicenceDTO queryDetail(Long licenceId) {
    Licence licence = this.getById(licenceId);
    checkNotNull(licence);
    return toSimpleLicenceDTO(licence);
  }

  /**
   * 分页获取
   *
   * @param licenceParam 分页查询参数
   * @return
   */
  public QueryData<SimpleLicenceDTO> getLicences(LicenceParam licenceParam) {
    String licenceType = licenceParam.getLicenceType();
    BusinessResponseEnum.BAD_LICENCE_TYPE.assertNotNull(licenceType);

    LambdaQueryWrapper<Licence> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Licence::getLicenceType, licenceType);
    IPage<Licence> page = this.page(new QueryPage<>(licenceParam), wrapper);
    return new QueryData<>(page, this::toSimpleLicenceDTO);
  }

  private SimpleLicenceDTO toSimpleLicenceDTO(Licence licence) {
    SimpleLicenceDTO simpleLicenceDTO = new SimpleLicenceDTO();
    simpleLicenceDTO.setLicenceId(String.valueOf(licence.getLicenceId()));
    simpleLicenceDTO.setOrganizationId(String.valueOf(licence.getOrganizationId()));
    simpleLicenceDTO.setLicenceType(licence.getLicenceType());
    simpleLicenceDTO.setProductName(licence.getProductName());
    simpleLicenceDTO.setLicenceMax(licence.getLicenceMax());
    simpleLicenceDTO.setLicenceAllocated(licence.getLicenceAllocated());
    simpleLicenceDTO.setComment(licence.getComment());
    return simpleLicenceDTO;
  }

  /**
   * 校验{@link Licence}存在
   *
   * @param licence
   */
  private void checkNotNull(Licence licence) {
    BusinessResponseEnum.LICENCE_NOT_FOUND.assertNotNull(licence);
  }
}
