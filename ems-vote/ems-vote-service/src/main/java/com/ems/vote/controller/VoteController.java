package com.ems.vote.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Survey;
import com.ems.api.domain.po.Vote;
import com.ems.vote.service.IVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final IVoteService voteService;

    //分页查询
    @GetMapping("/find")
    public IPage<Vote> find(Integer pageNum, Integer pageSize) {
        IPage<Vote> ip = new Page<>(pageNum, pageSize);
        return voteService.page(ip);
    }


    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Vote vote) {
        return voteService.save(vote);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Vote vote) {
        return voteService.updateById(vote);
    }

    //删除
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return voteService.removeById(id);
    }
}
