package com.laman.biz.tableproperty.domain.service;

import com.laman.biz.tableproperty.app.dto.TablePropertyDto;
import com.laman.biz.tableproperty.domain.entity.TableProperty;
import com.laman.biz.tableproperty.domain.repository.TablePropertyRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: TablePropertyDomainService
* @Description:  表格属性数据服务
* @Author: Away
* @Date: 2018/7/11 11:57
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class TablePropertyDomainService extends BaseDomainService<TablePropertyRepository,TableProperty,TablePropertyDto>{

    /**
     * @Method:  findAllProperty
     * @Author: Away
     * @Version: v1.0
     * @See: 查询所有
     * @Param:
     * @Return: java.util.List<com.laman.biz.tableproperty.app.dto.TablePropertyDto>
     * @Date: 2018/7/11 12:00
     */
    public List<TablePropertyDto> findAllProperty(){
        return toDtoList(this.CT.findByConditions(new TablePropertyDto()),TablePropertyDto.class);
    }

    /**
     * @Method:  deleteByPropertyTreeId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照属性树ID查找
     * @Param: propertyTreeId
     * @Return: void
     * @Date: 2018/7/12 14:13
     */
    public void deleteByPropertyTreeId(Long propertyTreeId) throws BusinessException{
        if(ObjectHelper.isNotEmpty(propertyTreeId)){
            this.CT.deleteByPropertyTreeId(propertyTreeId);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByPropertyCategoryCodeGroup
     * @Author: Away
     * @Version: v1.0
     * @See: 查找所有大类
     * @Param: categoryCode
     * @Return: java.util.List<com.laman.biz.tableproperty.app.dto.TablePropertyDto>
     * @Date: 2018/7/11 14:53
     */
    public List<TablePropertyDto> findAllPropertyTreeId(){
        return toDtoList(this.CT.findAllPropertyTreeId(),TablePropertyDto.class);
    }

    /**
     * @Method:  findByPropertyCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照属性编号查找
     * @Param: propertyCode
     * @Return: com.laman.biz.tableproperty.domain.entity.TableProperty
     * @Date: 2018/7/11 14:16
     */
    public TablePropertyDto findByPropertyCode(String propertyCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(propertyCode)){
            return toDto(this.CT.findByPropertyCode(propertyCode),TablePropertyDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByCondition
     * @Author: Away
     * @Version: v1.0
     * @See: 按照查询条件查询
     * @Param: condition
     * @Return: java.util.List<com.laman.biz.tableproperty.app.dto.TablePropertyDto>
     * @Date: 2018/7/11 14:57
     */
    public List<TablePropertyDto> findByCondition(TablePropertyDto condition){
        return toDtoList(this.CT.findByConditions(condition),TablePropertyDto.class);
    }

}
