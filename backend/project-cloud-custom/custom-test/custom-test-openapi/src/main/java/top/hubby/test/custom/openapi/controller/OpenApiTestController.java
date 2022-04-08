package top.hubby.test.custom.openapi.controller;

/**
 * @author zack <br/>
 * @create 2022-04-08 20:39 <br/>
 * @project project-cloud-custom <br/>
 */
@RestController
@Slf4j
@RequestMapping("/openapi")
@Api(value = "第三方接口模块", tags = "第三方接口模块")
public class OpenApiTestController {

    @OpenApiLog
    @OpenApiSignature
    @LimitRequest(count = 200)
    @ApiOperation(value = "新增帖子/文章")
    @PostMapping("/posts")
    public R<PostVO> save(
            @RequestBody @Valid OpenApiPostDTO dto, @RequestHeader(value = APPID) String appId) {

        dto.setAppId(appId);
        Post postModel = openApiPostService.post(dto);

        PostVO postVo = new PostVO();
        postVo.setId(postModel.getId());
        postVo.setPublishStatus(postModel.getPublishStatus());
        return new R<>(postVo);
    }
}
