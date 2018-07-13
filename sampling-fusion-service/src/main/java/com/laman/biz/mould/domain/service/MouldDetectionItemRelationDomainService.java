package com.laman.biz.mould.domain.service;

import com.laman.biz.mould.app.dto.MouldDetectionItemRelationDto;
import com.laman.biz.mould.domain.entity.MouldDetectionItemRelation;
import com.laman.biz.mould.domain.repository.MouldDetectionItemRelationRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
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
public class MouldDetectionItemRelationDomainService extends BaseDomainService<MouldDetectionItemRelationRepository,MouldDetectionItemRelation,MouldDetectionItemRelationDto>{

    /**
     * @Method:  findByItemCodeAndMouldTreeId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照检测项编号和所属模板树节点ID查找
     * @Param: itemCode
     * @Param: mouldTreeId
     * @Return: com.laman.biz.mould.app.dto.MouldDetectionItemRelationDto
     * @Date: 2018/7/6 10:32
     */
    public MouldDetectionItemRelationDto findByItemCodeAndMouldTreeId(String itemCode,Long mouldTreeId) throws BusinessException{
        if(ObjectHelper.isNotEmpty(itemCode)&&ObjectHelper.isNotEmpty(mouldTreeId)){
            return toDto(this.CT.findByDetectiveItemCodeAndMouldTreeId(itemCode, mouldTreeId),MouldDetectionItemRelationDto.class);
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findPageByItemCodeAndMouldTreeId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据检测项查找关系
     * @Param: pageRequest
     * @Param: itemCode
     * @Return: org.springframework.data.domain.Page<com.laman.biz.mould.app.dto.MouldDetectionItemRelationDto>
     * @Date: 2018/7/6 10:43
     */
    public Page<MouldDetectionItemRelationDto> findPageByItemCode(PageRequest pageRequest,MouldDetectionItemRelationDto mouldDetectionItemRelationDto){
        return toDtoPage(this.CT.findPageByConditions(pageRequest,mouldDetectionItemRelationDto),MouldDetectionItemRelationDto.class,pageRequest);
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
    public List<MouldDetectionItemRelationDto> findByDetectiveItemCode(String detectiveItemCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(detectiveItemCode)){
            return toDtoList(this.CT.findByDetectiveItemCode(detectiveItemCode),MouldDetectionItemRelationDto.class);
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
