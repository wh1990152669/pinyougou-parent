package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 *
 * <pre>项目名称：pinyougou-sellergoods-interface
 * 类名称：BrandService
 * 类描述：    品牌
 * 创建人：杨志超 yangzhichao150@126.com
 * 创建时间：2018年9月4日 下午3:49:11
 * 修改人：杨志超 yangzhichao150@126.com
 * 修改时间：2018年9月4日 下午3:49:11
 * 修改备注：
 * @version </pre>
 */
public interface BrandService {

	/**
	 * <pre>findAll(这里用一句话描述这个方法的作用)
	 * 创建人：杨志超 yangzhichao150@126.com
	 * 创建时间：2018年9月4日 下午3:50:10
	 * 修改人：杨志超 yangzhichao150@126.com
	 * 修改时间：2018年9月4日 下午3:50:10
	 * 修改备注： 品牌列表查询
	 * @return</pre>
	 */
	public List<TbBrand> findAll();

	/**
	 * <pre>findPage(品牌分页查询)
	 * 创建人：杨志超 yangzhichao150@126.com
	 * 创建时间：2018年9月4日 下午5:22:26
	 * 修改人：杨志超 yangzhichao150@126.com
	 * 修改时间：2018年9月4日 下午5:22:26
	 * 修改备注：
	 * @param page
	 * @param rows
	 * @return</pre>
	 */
	public PageResult findPage(int page, int rows);

	void add(TbBrand brand);

	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 修改
	 */
	public void update(TbBrand brand);
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbBrand findOne(Long id);




	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbBrand brand, int pageNum,int pageSize);


}
