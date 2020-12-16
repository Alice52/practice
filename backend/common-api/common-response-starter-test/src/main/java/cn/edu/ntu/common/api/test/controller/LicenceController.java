package cn.edu.ntu.common.api.test.controller;

import cn.edu.ntu.common.api.test.common.QueryData;
import cn.edu.ntu.common.api.test.model.SimpleLicenceDTO;
import cn.edu.ntu.common.api.test.model.param.LicenceParam;
import cn.edu.ntu.common.api.test.service.impl.LicenceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@RestController
@RequestMapping(value = "/licence")
public class LicenceController {

  @Resource private LicenceService licenceService;

  @GetMapping(value = "/{licenceId}")
  public SimpleLicenceDTO getLicence(@PathVariable("licenceId") Long licenceId) {
    return licenceService.queryDetail(licenceId);
  }

  /**
   * // TODO: @Validation not work.
   *
   * @param licenceParam
   * @return
   */
  @GetMapping(value = "/list")
  public QueryData<SimpleLicenceDTO> getLicences(@Validated @NotNull LicenceParam licenceParam) {
    return licenceService.getLicences(licenceParam);
  }
}
