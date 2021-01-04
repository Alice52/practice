package cn.edu.ntu.common.api.response.model;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public class QueryParam {
  private static final int DEFAULT_PAGE_NO = 1;
  private static final int DEFAULT_PAGE_SIZE = 10;
  protected Integer pageNo = DEFAULT_PAGE_NO;
  protected Integer pageSize = DEFAULT_PAGE_SIZE;

  public Integer getPageNo() {
    if (this.pageNo == null) {
      return DEFAULT_PAGE_NO;
    }

    return this.pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getPageSize() {
    if (this.pageSize == null) {
      return DEFAULT_PAGE_SIZE;
    }
    return this.pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
