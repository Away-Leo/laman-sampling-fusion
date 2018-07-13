package com.laman.biz.task.app.service;

import com.laman.biz.task.app.dto.SampleDetectionResultDto;
import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.biz.task.domain.entity.SampleDetectionResult;
import com.laman.biz.task.domain.service.SampleDetectionResultDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: SampleDetectionResultAppService
* @Description:  样品检测结果应用服务
* @Author: Away
* @Date: 2018/7/9 11:11
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
@Transactional
public class SampleDetectionResultAppService extends BaseAppService<SampleDetectionResultDomainService> {

    /**
     * @Method:  findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照业务编号、属性编号、检测项编号、样品编号查询
     * @Param: sampleDetectionResultDto 查询条件
     * @Return: com.laman.biz.task.app.dto.SampleDetectionResultDto
     * @Date: 2018/7/9 11:10
     */
    public SampleDetectionResultDto findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode(SampleDetectionResultDto sampleDetectionResultDto) throws BusinessException {
        return this.BDS.findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode(sampleDetectionResultDto);
    }

    /**
     * @Method:  getResultAndPackageData
     * @Author: Away
     * @Version: v1.0
     * @See: 获得已经保存的检测结果并将样品数据和公共数据分开组装
     * @Param: sampleDetectionResultDto
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date: 2018/7/9 16:32
     */
    public Map<String,Object> getResultAndPackageData(SamplingItemDto samplingItemDto) throws BusinessException{
        List<SampleDetectionResultDto> sourceData=this.BDS.findByBusinessCodeAndDetectiveCode(samplingItemDto);
        if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
            //返回数据
            Map<String,Object> returnData=new HashMap<>();
            //返回数据中的公共部分
            Map<String,Object> commonData=new HashMap<>();
            //返回数据中的条数不固定区域
            List<Map<String,Object>> sampleDataList=new ArrayList<>();
            //样品编号临时字符串
            StringBuilder tempSampleCode=new StringBuilder();
            //一行临时数据
            Map<String,Map<String,Object>> tempLineData=new HashMap<>();
            for(SampleDetectionResultDto temp:sourceData){
                if(ObjectHelper.isNotEmpty(temp.getSamplingCode())){
                    //如果已经包含以样品编号为键
                    if(tempLineData.containsKey(temp.getSamplingCode())){
                        tempLineData.get(temp.getSamplingCode()).put(temp.getAttributeCode(),temp.getAttributeValue());
                    }else{
                        Map<String,Object> values=new HashMap<>();
                        values.put(temp.getAttributeCode(),temp.getAttributeValue());
                        tempLineData.put(temp.getSamplingCode(),values);
                    }
                }else{
                    commonData.put(temp.getAttributeCode(),temp.getAttributeValue());
                }
            }
            //有几个样品编号就组装几行数据
            for(String key:tempLineData.keySet()){
                tempSampleCode.append(key).append(",");
                tempLineData.get(key).put("samplingCode",key);
                sampleDataList.add(tempLineData.get(key));
            }
            //比较在平板上选择的样品和已经保存的样品结果数据
            String[] panelArray=samplingItemDto.getIds().split(",");
            for(String temp:panelArray){
                //如果平板上所选样品未在检测结果中保存则将此样品添加到返回元数据中
                if(!tempSampleCode.toString().contains(temp)){
                    Map<String,Object> emptyLine=new HashMap<>();
                    emptyLine.put("samplingCode",temp);
                    sampleDataList.add(emptyLine);
                }
            }
            returnData.put("common",commonData);
            returnData.put("list",sampleDataList);
            return returnData;
        }else{
            return null;
        }
    }

    /**
     * @Method:  saveDataBeforeCheckExsit
     * @Author: Away
     * @Version: v1.0
     * @See: 更新属性值之前先查询是否有此属性值有的话则为更新
     * @Param: samplingCode
     * @Param: attributeCode
     * @Param: businessCode
     * @Param: detectiveCode
     * @Param: attributeValue
     * @Return: com.laman.biz.task.app.dto.SampleDetectionResultDto
     * @Date: 2018/7/10 9:54
     */
    public  SampleDetectionResultDto saveDataBeforeCheckExsit(String samplingCode,String attributeCode,String businessCode,String detectiveCode,String attributeValue) throws Exception{
        //查找检测结果并更新结果或存入结果
        SampleDetectionResultDto param=new SampleDetectionResultDto();
        param.setSamplingCode(samplingCode);
        param.setAttributeCode(attributeCode);
        param.setBusinessCode(businessCode);
        param.setDetectiveCode(detectiveCode);
        SampleDetectionResultDto sampleDetectionResultDto=null;
        if(ObjectHelper.isEmpty(samplingCode)){
            sampleDetectionResultDto=this.BDS.findByBusinessCodeAndDetectiveCodeAndAttributeCode(businessCode,detectiveCode,attributeCode);
        }else{
            sampleDetectionResultDto=this.findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode(param);
        }
        if(ObjectHelper.isEmpty(sampleDetectionResultDto)){
            sampleDetectionResultDto=new SampleDetectionResultDto();
        }
        sampleDetectionResultDto.setDetectiveCode(detectiveCode);
        sampleDetectionResultDto.setBusinessCode(businessCode);
        sampleDetectionResultDto.setAttributeCode(attributeCode);
        sampleDetectionResultDto.setSamplingCode(samplingCode);
        sampleDetectionResultDto.setAttributeValue(attributeValue);
        return this.saveOrUpdateData(sampleDetectionResultDto, SampleDetectionResult.class);
    }
    /**
     * @Method:  saveDataBeforeCheckExsit
     * @Author: Away
     * @Version: v1.0
     * @See: 更新属性值之前先查询是否有此属性值有的话则为更新
     * @Param: sampleDetectionResultDto
     * @Return: com.laman.biz.task.app.dto.SampleDetectionResultDto
     * @Date: 2018/7/10 9:54
     */
    public  SampleDetectionResultDto saveDataBeforeCheckExist(SampleDetectionResultDto sampleDetectionResultDto) throws Exception{
        //查找检测结果并更新结果或存入结果
        SampleDetectionResultDto param=new SampleDetectionResultDto();
        param.setSamplingCode(sampleDetectionResultDto.getSamplingCode());
        param.setAttributeCode(sampleDetectionResultDto.getAttributeCode());
        param.setBusinessCode(sampleDetectionResultDto.getBusinessCode());
        param.setDetectiveCode(sampleDetectionResultDto.getDetectiveCode());
        SampleDetectionResultDto sampleDetectionResultDtoOld=null;
        if(ObjectHelper.isEmpty(sampleDetectionResultDto.getSamplingCode())){
            sampleDetectionResultDtoOld=this.BDS.findByBusinessCodeAndDetectiveCodeAndAttributeCode(sampleDetectionResultDto.getBusinessCode(),sampleDetectionResultDto.getDetectiveCode(),sampleDetectionResultDto.getAttributeCode());
        }else{
            sampleDetectionResultDtoOld=this.findByBusinessCodeAndAttributeCodeAndDetectiveCodeAndSamplingCode(param);
        }
        if(ObjectHelper.isEmpty(sampleDetectionResultDtoOld)){
            sampleDetectionResultDtoOld=new SampleDetectionResultDto();
        }
        sampleDetectionResultDtoOld.setDetectiveCode(sampleDetectionResultDto.getDetectiveCode());
        sampleDetectionResultDtoOld.setBusinessCode(sampleDetectionResultDto.getBusinessCode());
        sampleDetectionResultDtoOld.setAttributeCode(sampleDetectionResultDto.getAttributeCode());
        sampleDetectionResultDtoOld.setSamplingCode(sampleDetectionResultDto.getSamplingCode());
        sampleDetectionResultDtoOld.setAttributeValue(sampleDetectionResultDto.getAttributeValue());
        return this.saveOrUpdateData(sampleDetectionResultDtoOld, SampleDetectionResult.class);
    }


}
