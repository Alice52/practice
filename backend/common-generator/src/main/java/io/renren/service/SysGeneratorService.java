/**
 * https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package io.renren.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.renren.config.MongoManager;
import io.renren.factory.MongoDBCollectionFactory;
import io.renren.repository.GeneratorRepository;
import io.renren.repository.MongoDBGeneratorRepository;
import io.renren.utils.GenUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysGeneratorService {
  @Autowired private GeneratorRepository generatorRepository;

  public PageUtils queryList(Query query) {
    Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
    List<Map<String, Object>> list = generatorRepository.queryList(query);
    int total = (int) page.getTotal();
    if (generatorRepository instanceof MongoDBGeneratorRepository) {
      total = MongoDBCollectionFactory.getCollectionTotal(query);
    }
    return new PageUtils(list, total, query.getLimit(), query.getPage());
  }

  public Map<String, String> queryTable(String tableName) {
    return generatorRepository.queryTable(tableName);
  }

  public List<Map<String, String>> queryColumns(String tableName) {
    return generatorRepository.queryColumns(tableName);
  }

  public byte[] generatorCode(String[] tableNames) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ZipOutputStream zip = new ZipOutputStream(outputStream);
    for (String tableName : tableNames) {
      // 查询表信息
      Map<String, String> table = queryTable(tableName);
      // 查询列信息
      List<Map<String, String>> columns = queryColumns(tableName);
      // 生成代码
      GenUtils.generatorCode(table, columns, zip);
    }
    if (MongoManager.isMongo()) {
      GenUtils.generatorMongoCode(tableNames, zip);
    }

    IOUtils.closeQuietly(zip);
    return outputStream.toByteArray();
  }
}
