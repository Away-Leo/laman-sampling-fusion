package com.laman.biz.task.domain.repository;

import com.laman.biz.task.domain.entity.SampleDetectionResult;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: SampleDetectionResultRepository
* @Description:  样品检测结果自定义操作库
* @Author: Away
* @Date: 2018/7/9 10:54
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface SampleDetectionResultRepository extends BaseRepository<SampleDetectionResult,Long>{

    /**
     * @Method:  findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照业务编号、属性编号、检测项编号、样品编号查询
     * @Param: businessCode 业务编号
     * @Param: attributeCode 属性编号
     * @Param: detectiveCode 检测编号
     * @Param: samplingCode 样品编号
     * @Return: com.laman.biz.task.domain.entity.SampleDetectionResult
     * @Date: 2018/7/9 11:03
     */
    SampleDetectionResult findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode(String businessCode,String attributeCode,String detectiveCode,String samplingCode);

    /**
     * @Method:  findByBusinessCodeAndAndDetectiveCode
     * @Author: Away
     * @Version: v1.0
     * @See:
     * @Param: businessCode
     * @Param: detectiveCode
     * @Return: java.util.List<com.laman.biz.task.domain.entity.SampleDetectionResult>
     * @Date: 2018/7/9 15:43
     */
    List<SampleDetectionResult> findByBusinessCodeAndDetectiveCode(String businessCode,String detectiveCode);

    /**
     * @Method:  findByBusinessCodeAndDetectiveCodeAndAttributeCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照业务编号、属性编号、检测项编号查找
     * @Param: businessCode
     * @Param: detectiveCode
     * @Param: attributeCode
     * @Return: com.laman.biz.task.domain.entity.SampleDetectionResult
     * @Date: 2018/7/10 10:24
     */
    SampleDetectionResult findByBusinessCodeAndDetectiveCodeAndAttributeCode(String businessCode,String detectiveCode,String attributeCode);
}
