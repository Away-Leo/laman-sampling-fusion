package com.laman.biz.original.domain.component;

import com.alibaba.druid.pool.DruidDataSource;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.laman.fusion.base.util.ObjectProperUtil;
import com.laman.fusion.base.util.StringUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Title: QueryComponent
 * @Description: 数据查询组件
 * @Author: Away
 * @Date: 2018/6/27 14:35
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
public class QueryComponent {

    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.secondary.url}")
    private String url;
    @Value("${spring.datasource.secondary.username}")
    private String username;
    @Value("${spring.datasource.secondary.password}")
    private String password;
    @Value("${spring.datasource.secondary.driver-class-name}")
    private String driverClassName;

    /**
     * @Method:  getInstance
     * @Author: Away
     * @Version: v1.0
     * @See: 获取实例
     * @Param:
     * @Return: void
     * @Date: 2018/6/27 18:57
     */
    public JdbcTemplate getInstance() {
        if(ObjectHelper.isEmpty(jdbcTemplate)){
            DruidDataSource datasource = new DruidDataSource();
            datasource.setDriverClassName(driverClassName);
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            jdbcTemplate=new JdbcTemplate(datasource);
        }
        return jdbcTemplate;
    }


    /**
     * @Method:  findList
     * @Author: Away
     * @Version: v1.0
     * @See: 通过sql查询返回相应集合
     * @Param: sql
     * @Param: tClass
     * @Return: java.util.List<org.apache.poi.ss.formula.functions.T>
     * @Date: 2018/7/3 9:19
     */
    public <T> List<T> findList(String sql,Class<T> tClass) throws Exception{
        if(ObjectHelper.isNotEmpty(sql)&&ObjectHelper.isNotEmpty(tClass)){
            return doQuery(sql, tClass);
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  doQuery
     * @Author: Away
     * @Version: v1.0
     * @See: 执行查询
     * @Param: sql
     * @Param: tClass
     * @Return: java.util.List<org.apache.poi.ss.formula.functions.T>
     * @Date: 2018/7/3 9:19
     */
    private <T> List doQuery(String sql, Class<T> tClass) throws Exception{
        List<Map<String,Object>> fieldsInfo= ObjectProperUtil.getFieldsInfo(tClass);
        List returnData=this.getInstance().query(sql,(RowMapper) (ResultSet rs, int rowNum) -> {
            List result=new ArrayList<>();
            while (rs.next()) {
                try {
                    T tInstance=tClass.newInstance();
                    for(Map<String,Object> temp:fieldsInfo){
                        try {
                            Method method=tClass.getMethod("set"+ StringUtil.firstToUpercase(temp.get("name").toString()), Class.forName(temp.get("type").toString().replaceAll("class ","")));
                            method.invoke(tInstance,rs.getObject(temp.get("name").toString()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    result.add(tInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        });
        return ObjectHelper.isNotEmpty(returnData)&&returnData.size()>0?(List) returnData.get(0):null;
    }
}
