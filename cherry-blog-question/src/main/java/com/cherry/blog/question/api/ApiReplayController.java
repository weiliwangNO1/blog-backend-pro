package com.cherry.blog.question.api;

import com.cherry.blog.question.service.IReplayService;
import com.cherry.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问答管理前端控制器
 * @author wwl
 * @since 2022-1-23
 */
@Api(value = "回答管理API接口", description = "回答管理API接口，不需要通过身份认证就可以访问")
@RequestMapping("/api/replay")
@RestController
public class ApiReplayController {

    @Autowired
    private IReplayService replayService;

    @ApiOperation("通过问题ID递归查询所有评论")
    @ApiImplicitParam(name="questionId", value="问题ID", required=true)
    @GetMapping("/list/{questionId}")
    public Result findByQuestionId(@PathVariable("questionId") String questionId) {
        return replayService.findByQuestionId(questionId);
    }

}
