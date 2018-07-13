package com.laman.biz.mould.app.service;

import com.laman.biz.mould.app.dto.MouldDetectionItemRelationDto;
import com.laman.biz.mould.domain.entity.MouldDetectionItemRelation;
import com.laman.biz.mould.domain.service.MouldDetectionItemRelationDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: MouldItemRelationDomainService
* @Description:  模板与检测项关系数据服务
* @Author: Away
* @Date: 2018/7/6 10:27
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class MouldDetectionItemRelationAppService extends BaseAppService<MouldDetectionItemRelationDomainService> {


    /**
     * @Method:  saveRelationBeforeCheck
     * @Author: Away
     * @Version: v1.0
     * @See: 保存之前先判断数据库中是否有相同记录
     * @Param: toSaveData
     * @Return: void
     * @Date: 2018/7/6 10:40
     */
    public void saveRelationBeforeCheck(List<MouldDetectionItemRelationDto> toSaveData) throws Exception{
        if(ObjectHelper.isNotEmpty(toSaveData)){
            for(MouldDetectionItemRelationDto temp:toSaveData){
                if(ObjectHelper.isEmpty(this.BDS.findByItemCodeAndMouldTreeId(temp.getDetectiveItemCode(),temp.getMouldTreeId()))){
                    this.BDS.saveData(temp, MouldDetectionItemRelation.class);
                }
            }
        }
    }


    /**
     * @Method:  findPageByItemCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照检测项编号查找
     * @Param: pageRequest
     * @Param: itemCode
     * @Return: org.springframework.data.domain.Page<com.laman.biz.mould.app.dto.MouldDetectionItemRelationDto>
     * @Date: 2018/7/6 10:47
     */
    public Page<MouldDetectionItemRelationDto> findPageByItemCode(PageRequest pageRequest,MouldDetectionItemRelationDto mouldDetectionItemRelationDto){
        return this.BDS.findPageByItemCode(pageRequest, mouldDetectionItemRelationDto);
    }

    /**
     * @Method:  findByDetectiveItemCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据检测项查询模板
     * @Param: detectiveItemCode
     * @Return: java.util.List<com.laman.biz.mould.domain.entity.MouldItemRelation>
     * @Date: 2018/7/9 11:29
     */
    public List<MouldDetectionItemRelationDto> findByDetectiveItemCode(String detectiveItemCode) throws BusinessException {
        return this.BDS.findByDetectiveItemCode(detectiveItemCode);
    }

}
