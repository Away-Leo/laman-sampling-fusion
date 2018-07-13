package com.laman.biz.mould.domain.service;

import com.laman.biz.mould.app.dto.MouldTreeDto;
import com.laman.biz.mould.domain.entity.MouldTree;
import com.laman.biz.mould.domain.repository.MouldTreeRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: MouldTreeDomainService
* @Description:  模板树数据服务
* @Author: Away
* @Date: 2018/7/4 16:38
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class MouldTreeDomainService extends BaseDomainService<MouldTreeRepository,MouldTree,MouldTreeDto>{

    /**
     * @Method:  findByTreeName
     * @Author: Away
     * @Version: v1.0
     * @See: 根据名称查找
     * @Param: name
     * @Return: java.util.List<com.laman.biz.mould.app.dto.MouldTreeDto>
     * @Date: 2018/7/4 16:41
     */
    public List<MouldTreeDto> findByTreeName(String name) throws BusinessException{
        if(ObjectHelper.isNotEmpty(name)){
            return toDtoList(this.CT.findByTreeNameLike(name),MouldTreeDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
