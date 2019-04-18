package com.dazzlzy.springbootseed.controller.user;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
