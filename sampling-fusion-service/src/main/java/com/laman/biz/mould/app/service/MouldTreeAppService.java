package com.laman.biz.mould.app.service;

import com.laman.biz.mould.app.dto.MouldTreeDto;
import com.laman.biz.mould.domain.service.MouldTreeDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
* @Title: MouldInfoAppService
* @Description:  模板信息应用层服务
* @Author: Away
* @Date: 2018/6/22 10:07
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
@Transactional
public class MouldTreeAppService extends BaseAppService<MouldTreeDomainService>{

    /**
     * @Method:  findByName
     * @Author: Away
     * @Version: v1.0
     * @See: 按照名称查找
     * @Param: name
     * @Return: java.util.List<com.laman.biz.mould.app.dto.MouldTreeDto>
     * @Date: 2018/7/4 16:47
     */
    public List<MouldTreeDto> findByName(String name) throws BusinessException{
        return this.BDS.findByTreeName(name);
    }
}
