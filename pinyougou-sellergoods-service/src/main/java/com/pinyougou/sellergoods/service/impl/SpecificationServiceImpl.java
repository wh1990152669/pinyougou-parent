package com.pinyougou.sellergoods.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by DELL on 2018/9/5.
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        Page<TbSpecification> p = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(p.getTotal(), p.getResult());

    }
    /**
     * 增加
     */
    @Override
    public void add(Specification specification) {
        specificationMapper.insert(specification.getSpecification());//插入规格
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());//设置规格ID
            	specificationOptionMapper.insert(specificationOption);
        }
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            specificationMapper.deleteByPrimaryKey(id);
            //删除原有的规格选项
            TbSpecificationOptionExample example=new TbSpecificationOptionExample();
            com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);//指定规格ID为条件
            specificationOptionMapper.deleteByExample(example);//删除
        }
    }

    @Override
    public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        TbSpecificationExample example=new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
            if(specification!=null){
                if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
                    criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
                }

            }
            Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);
            return new PageResult(page.getTotal(), page.getResult());
    }
    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id){
        //查询规格
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        //查询规格选项列表
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);//根据规格ID查询
        List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
        //构建组合实体类返回结果
        Specification spec=new Specification();
        spec.setSpecification(tbSpecification);
        spec.setSpecificationOptionList(optionList);
        return spec;
    }
    /**
     * 修改
     */
    @Override
    public void update(Specification specification){
        //保存修改的规格
        specificationMapper.updateByPrimaryKey(specification.getSpecification());//保存规格
        //删除原有的规格选项
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());//指定规格ID为条件
        specificationOptionMapper.deleteByExample(example);//删除
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }

}
