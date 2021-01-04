package cn.edu.ntu.common.api.test.service;

import cn.edu.ntu.common.api.test.model.SimpleLicenceDTO;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public interface ILicenceService {
  SimpleLicenceDTO queryDetail(Long licenceId);
}
