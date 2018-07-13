package com.laman.biz.task.domain.service;

import com.laman.biz.task.app.dto.SampleDetectionResultDto;
import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.biz.task.domain.entity.SampleDetectionResult;
import com.laman.biz.task.domain.repository.SampleDetectionResultRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: SampleDetectionResultDomainService
* @Description:  样品检测结果数据服务
* @Author: Away
* @Date: 2018/7/9 11:06
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class SampleDetectionResultDomainService extends BaseDomainService<SampleDetectionResultRepository,SampleDetectionResult,SampleDetectionResultDto>{

    /**
     * @Method:  findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照业务编号、属性编号、检测项编号、样品编号查询
     * @Param: sampleDetectionResultDto 查询条件
     * @Return: com.laman.biz.task.app.dto.SampleDetectionResultDto
     * @Date: 2018/7/9 11:10
     */
    public SampleDetectionResultDto findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode(SampleDetectionResultDto sampleDetectionResultDto) throws BusinessException{
        if(ObjectHelper.isNotEmpty(sampleDetectionResultDto)){
            return toDto(this.CT.findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode(sampleDetectionResultDto.getBusinessCode(),sampleDetectionResultDto.getAttributeCode(),sampleDetectionResultDto.getDetectiveCode(),sampleDetectionResultDto.getSamplingCode()),SampleDetectionResultDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByBusinessCodeAndDetectiveCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据业务编号和检测项编号查找样品检测结果
     * @Param: sampleDetectionResultDto
     * @Return: java.util.List<com.laman.biz.task.app.dto.SampleDetectionResultDto>
     * @Date: 2018/7/9 16:24
     */
    public List<SampleDetectionResultDto> findByBusinessCodeAndDetectiveCode(SamplingItemDto samplingItemDto) throws BusinessException{
        if(ObjectHelper.isNotEmpty(samplingItemDto)){
            return toDtoList(this.CT.findByBusinessCodeAndDetectiveCode(samplingItemDto.getBusinessCode(),samplingItemDto.getDetectiveCode()),SampleDetectionResultDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

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
    public SampleDetectionResultDto findByBusinessCodeAndDetectiveCodeAndAttributeCode(String businessCode,String detectiveCode,String attributeCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(businessCode)&&ObjectHelper.isNotEmpty(detectiveCode)&&ObjectHelper.isNotEmpty(attributeCode)){
            return toDto(this.CT.findByBusinessCodeAndDetectiveCodeAndAttributeCode(businessCode, detectiveCode, attributeCode),SampleDetectionResultDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
