package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.remoting.p2p.Peer;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeService;
import entity.PageResult;
import entity.Result;
import org.apache.zookeeper.data.Id;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by MYLOVE on 2018/9/6.
 */
@RestController
@RequestMapping("type")
public class TypeController {
    @Reference
    private TypeService typeService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbTypeTemplate typeTemplate, int page, int rows){
        return typeService.findPage(typeTemplate, page, rows);
    }
   //下拉列表-->
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return typeService.selectOptionList();
    }

    @RequestMapping("/selectTypeList")
    public List<Map> selectTypeList(){
        return typeService.selectTypeList();
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbTypeTemplate typeTemplate){
        try {
            typeService.add(typeTemplate);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }

    }

    @RequestMapping("findOne")
    public TbTypeTemplate findOne(Long id){
        return typeService.findOne(id);
    }

    /**
     * 修改
     * @param
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbTypeTemplate tbTypeTemplate){
        try {
            typeService.update(tbTypeTemplate);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            typeService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

}

