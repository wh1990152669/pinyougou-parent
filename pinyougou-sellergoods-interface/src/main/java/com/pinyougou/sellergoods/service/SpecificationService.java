package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojogroup.Specification;
import entity.PageResult;

import java.util.List;

/**
 * Created by DELL on 2018/9/5.
 */
public interface SpecificationService {

    public List<TbSpecification> findAll();

    public PageResult findPage(int page, int rows);
    /**
     * 增加
     */
    public void add(Specification specification);

    void delete(Long[] ids);
    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(TbSpecification specification, int pageNum,int pageSize);

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public Specification findOne(Long id);
    /**
     * 修改
     */
    public void update(Specification specification);
}
