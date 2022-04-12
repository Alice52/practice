package top.hubby.serialize.api;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author asd <br>
 * @create 2021-10-29 3:52 PM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Serialize")
@Slf4j
@RestController
@RequestMapping("/serialize/api")
public class EnumUsedInAPIController {
    @Autowired private RestTemplate restTemplate;

    @GetMapping("/getOrderStatusClient")
    public void getOrderStatusClient() {
        StatusEnumClient result =
                restTemplate.getForObject(
                        "http://localhost:8080/serialize/api/getOrderStatus",
                        StatusEnumClient.class);
        log.info("result {}", result);
    }

    @GetMapping("/queryOrdersByStatusListClient")
    public void queryOrdersByStatusListClient() {
        List<StatusEnumClient> request =
                Arrays.asList(StatusEnumClient.CREATED, StatusEnumClient.PAID);
        HttpEntity<List<StatusEnumClient>> entity = new HttpEntity<>(request, new HttpHeaders());
        List<StatusEnumClient> response =
                restTemplate
                        .exchange(
                                "http://localhost:8080/serialize/api/queryOrdersByStatusList",
                                HttpMethod.POST,
                                entity,
                                new ParameterizedTypeReference<List<StatusEnumClient>>() {})
                        .getBody();
        log.info("result {}", response);
    }

    @GetMapping("/getOrderStatus")
    public StatusEnumServer getOrderStatus() {
        return StatusEnumServer.CANCELED;
    }

    @PostMapping("/queryOrdersByStatusList")
    public List<StatusEnumServer> queryOrdersByStatus(
            @RequestBody List<StatusEnumServer> enumServers) {
        enumServers.add(StatusEnumServer.CANCELED);
        return enumServers;
    }
}
