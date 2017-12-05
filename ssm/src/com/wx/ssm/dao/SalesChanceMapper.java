package com.wx.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wx.ssm.entity.SalesChance;

public interface SalesChanceMapper {

	@Select("SELECT * FROM (SELECT rownum rn,ID,CUST_NAME,TITLE,CONTACT,CONTACT_TEL,CREATE_DATE FROM sales_chances) t"
			+ " WHERE t.rn >= ${startIndex} AND t.rn<=${endIndex}")
	List<SalesChance> getContent(Map<String, Object> map);
	
	@Select("select count(id) from sales_chances")
	int getTotalNum();
	
	@Insert("insert into sales_chances "
			+ "(id,source,cust_name,rate,title,contact,contact_tel,description,created_user_id,create_date,status) "
			+ "values (sales_seq.nextval,#{source},#{custName},#{rate},#{title},#{contact},"
			+ "#{contactTel},#{description},#{createBy.id},#{createDate},#{status})")
	void save(SalesChance salesChance);
	
	@Delete("delete from sales_chances where id = #{id}")
	void delete(@Param("id") int id);
	
	@Select("select s.id,source,cust_name,rate,title,contact,contact_tel,description,"
			+ "u.name as \"createBy.name\",r.name as \"createBy.role.name\" from sales_chances s "
			+ "left outer join users u on s.created_user_id = u.id "
			+ "left outer join roles r on u.role_id = r.id "
			+ "where s.id = #{id}")
	SalesChance getById(@Param("id") int id);
	
	@Update("update sales_chances set source = #{source},cust_name = #{custName},rate=#{rate},title=#{title},contact=#{contact},"
			+ "contact_tel=#{contactTel},description=#{description} where id=#{id}")
	void update(SalesChance salesChance);
}
