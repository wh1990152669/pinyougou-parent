package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.sellergoods.service.TypeService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by MYLOVE on 2018/9/6.
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public PageResult findPage(TbTypeTemplate typeTemplate, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if(typeTemplate!=null){
            if(typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
                criteria.andNameLike("%"+typeTemplate.getName()+"%");
            }
        }
        Page<TbTypeTemplate> p= (Page<TbTypeTemplate>)tbTypeTemplateMapper.selectByExample(example);
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
    }

    @Override
    public List<Map> selectTypeList() {
        return brandMapper.selectTypeList();
    }

    @Override
    public void add(TbTypeTemplate typeTemplate) {
        tbTypeTemplateMapper.insert(typeTemplate);
    }

    @Override
    public TbTypeTemplate findOne(Long id) {
        return tbTypeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            tbTypeTemplateMapper.deleteByPrimaryKey(id);
        }
    }

}
