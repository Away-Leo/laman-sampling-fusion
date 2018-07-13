package com.laman.biz.task.app.service;

import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.biz.task.domain.service.SamplingItemDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
* @Title: SamplingItemAppService
* @Description:  样品应用服务
* @Author: Away
* @Date: 2018/7/6 20:19
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
@Transactional
public class SamplingItemAppService extends BaseAppService<SamplingItemDomainService> {

    /**
     * @Method:  findByBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照业务编号查找
     * @Param: businessCode
     * @Return: java.util.List<com.laman.biz.task.app.dto.SamplingItemDto>
     * @Date: 2018/7/7 11:57
     */
    public List<SamplingItemDto> findByBusinessCode(String businessCode) throws BusinessException{
        return this.BDS.findByBusinessCode(businessCode);
    }

    /**
     * @Method:  findByIds
     * @Author: Away
     * @Version: v1.0
     * @See: 根据多个ID查询样品集合
     * @Param: ids
     * @Return: java.util.List<com.laman.biz.task.app.dto.SamplingItemDto>
     * @Date: 2018/7/9 9:51
     */
    public List<SamplingItemDto> findByIds(String ids) throws BusinessException{
        if(ObjectHelper.isNotEmpty(ids)){
            String[] idsArray=ids.split(",");
            List<Long> actualParam=new ArrayList<>();
            for(String temp:idsArray){
                actualParam.add(Long.valueOf(temp));
            }
            return this.BDS.findByIdIn(actualParam);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
