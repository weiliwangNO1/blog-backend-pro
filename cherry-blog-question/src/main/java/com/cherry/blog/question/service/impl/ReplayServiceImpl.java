package com.cherry.blog.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherry.blog.entities.Question;
import com.cherry.blog.entities.Replay;
import com.cherry.blog.question.mapper.QuestionMapper;
import com.cherry.blog.question.mapper.ReplayMapper;
import com.cherry.blog.question.service.IReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cherry.blog.util.base.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 回答信息表 服务实现类
 * </p>
 *
 * @author 维利博客-weiliwang.top
 * @since 2022-01-23
 */
@Service
public class ReplayServiceImpl extends ServiceImpl<ReplayMapper, Replay> implements IReplayService {

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 通过问题id级联查询所有回答
     * @param questionId 问题id
     * @return
     */
    public Result findByQuestionId(String questionId){
        if(StringUtils.isBlank(questionId)) {
            return Result.error("问题ID不能为空");
        }
        List<Replay> list = baseMapper.findByQuestionId(questionId);
        return Result.ok(list);
    }

    /**
     * 通过回答评论id递归删除
     * @param id 回答id
     * @return
     */
    @Transactional // 进行事务管理
    @Override
    public Result deleteById(String id) {
        if (StringUtils.isBlank(id)) {
            return Result.error("回答评论ID不能为空");
        }
        // 要删除的回答评论ID
        ArrayList<String> ids = new ArrayList<>();
        //先把要删除的一级回答id放入到集合中
        ids.add(id);
        //递归的子评论 id 加入到集合中
        this.getIds(ids, id);
        // 删除回答评论后，还要更新问题表中的回答数量，则先查问题id
        Replay replay = baseMapper.selectById(id);
        //批量删除集合中的id
        int size = baseMapper.deleteBatchIds(ids);
        if (size > 0) {
        // 更新问题表中的回答数量
            Question question = questionMapper.selectById(replay.getQuestionId());
            question.setReply(question.getReply() - size);
            questionMapper.updateById(question);
        }
        return Result.ok();
    }

    /**
     * 递归方法
     * @param ids 要删除的所有评论id
     * @param parentId
     */
    private void getIds(List<String> ids, String parentId) {
        //查询子评论的对象
        QueryWrapper<Replay> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Replay> replayList = baseMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(replayList)) {
            for(Replay replay: replayList ) {
                String id = replay.getId();
                ids.add(id);
                // 递归继续查询子评论id
                this.getIds(ids, id);
            }
        }
    }

    /**
     * 新增回答并更新问题表中的回答数量
     * @param replay
     * @return
     */
    @Transactional // 进行事务管理
    @Override
    public Result add(Replay replay) {
        // 新增到回答表
        boolean ok = this.save(replay);
        // 更新问题表中的回答数量
        if(ok) {
            // 更新问题表中的回答数量
            Question question = questionMapper.selectById(replay.getQuestionId());
            question.setReply(question.getReply() + 1);
            questionMapper.updateById(question);
        }
        return Result.ok();
    }


}
