package common.core.constant;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
public interface SecurityConstants {
    /** 匿名用户 */
    String ANONYMOUSUSER = "anonymousUser";

    /** 角色前缀 */
    String ROLE = "ROLE_";
    /** 前缀 */
    String PROJECT_PREFIX = "custom_cloud";

    /** 项目的license */
    String PROJECT_LICENSE = "made by mc";

    /** 内部 */
    String FROM_IN = "Y";

    /** 标志 */
    String FROM = "from";

    /** grant_type */
    String REFRESH_TOKEN = "refresh_token";

    String GRANT_TYPE_PASSWORD = "password";

    /** {bcrypt} 加密的特征码 */
    String BCRYPT = "{bcrypt}";
    /** sys_oauth_client_details 表的字段，不包括client_id、client_secret */
    String CLIENT_FIELDS =
            "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
                    + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
                    + "refresh_token_validity, additional_information, autoapprove";

    /** JdbcClientDetailsService 查询语句 */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

    /** 默认的查询语句 */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /** 按条件client_id 查询 */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    /***
     * 资源服务器默认bean名称
     */
    String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";
}
