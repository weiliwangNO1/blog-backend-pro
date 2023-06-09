package com.cherry.blog.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cherry.blog.entities.Question;
import com.cherry.blog.feign.req.UserInfoREQ;
import com.cherry.blog.question.req.QuestionUserREQ;
import com.cherry.blog.util.base.BaseRequest;
import com.cherry.blog.util.base.Result;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 *
 * @author 维利博客-weiliwang.top
 * @since 2022-01-23
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 查询热门回答列表
     * @param req
     * @return
     */
    Result findHotList(BaseRequest<Question> req);

    /**
     * 查询最新问答列表
     * @param req
     * @return
     */
    Result findNewList(BaseRequest<Question> req);

    /**
     * 分页查询等待回答列表
     * @param req
     * @return
     */
    Result findWaitList(BaseRequest<Question> req);

    /**
     * 根据标签id分页查询问题列表
     * @param req 分页相关的对象
     * @param labelId 标签id
     * @return
     */
    Result findListByLabelId(BaseRequest<Question> req, String labelId);

    /**
     * 通过问题id查询详情
     * @param id 问题id
     * @return
     */
    Result findById(String id);

    /**
     * 更新浏览次数
     * @param id
     * @return
     */
    Result updateViewCount(String id);

    /**
     * 修改或新增问题数据
     * @param question
     * @return
     */
    Result updateOrSave(Question question);

    /**
     * 假删除，通过 问题id 修改状态为 0 ，表示已删除
     * @param id 问题id
     * @return
     */
    public Result deleteById(String id);

    /**
     * 修改状态
     * @param id 问题id
     * @param status 问题状态
     * @return
     */
    public Result updateStatus(String id, Integer status);

    /**
     * 更新点赞数
     * @param id
     * @param count
     * @return
     */
    Result updateThumhup(String id, int count);

    /**
     * 根据用户ID查询问题列表
     * @return
     */
    Result findListByUserId(QuestionUserREQ req);

    /**
     * 查询问题总记录数
     * @return
     */
    Result getQuestionTotal();

    /**
     * 更新问题与回答表中的用户信息
     * @param req
     * @return
     */
    boolean updateUserInfo(UserInfoREQ req);


}
