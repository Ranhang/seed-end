package cn.chenyh.springbootseed.controller.user;

import cn.chenyh.common.base.Response;
import cn.chenyh.common.base.ResponseResult;
import cn.chenyh.springbootseed.model.user.Resource;
import cn.chenyh.springbootseed.model.user.User;
import cn.chenyh.springbootseed.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/4/17 18:25
 * @Description:
 */

@Slf4j
@Api("资源")
@RestController
@RequestMapping(value = "/resource")
@RequiredArgsConstructor
public class ResourceController {


    private final IResourceService resourceService;

    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResponseResult add(@RequestBody Resource resource) throws ParseException {
        return Response.success(resourceService.add(resource));
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation("删除")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        return Response.success(resourceService.delete(ids));
    }

    @PutMapping(value = "/update")
    @ApiOperation("更新")
    public ResponseResult update(@RequestBody Resource resourc) {
        return Response.success(resourceService.update(resourc));
    }

    @GetMapping(value = "/query")
    public ResponseResult queryByIdOrName(Long id, String name) {
        Resource resource = resourceService.queryByIdOrName(id, name);
        return Response.success(resource);
    }
}
