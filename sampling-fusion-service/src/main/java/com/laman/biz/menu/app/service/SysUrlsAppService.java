package com.laman.biz.menu.app.service;


import com.laman.biz.menu.app.dto.SysUrlsDto;
import com.laman.biz.menu.domain.service.SysUrlsDomainService;
import com.laman.fusion.base.base.BaseAppService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**   
* @Title: SysUrlsAppService.java
* @Description:  
* @Author: Away
* @Date: 2018/2/7 18:27
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Transactional
@Service
public class SysUrlsAppService extends BaseAppService<SysUrlsDomainService>{

    /**
     * @Author: Away
     * @Description: 批量保存（先清空数据）
     * @Param: sysUrlsDtos
     * @Return: int
     * @Date: 2018/2/7 18:56
     * @Copyright: 重庆拉曼科技有限公司
     */
    public int batchSaveSysUrlsBeforeDeleteAll(List<SysUrlsDto> sysUrlsDtos) throws Exception{
        return this.BDS.batchSaveSysUrlsBeforeDeleteAll(sysUrlsDtos);
    }

    /**
     * @Author: Away
     * @Description: 根据参数查询分页
     * @Param: pageable
     * @Param: params
     * @Return: org.springframework.data.domain.Page<com.laman.biz.menu.app.dto.SysUrlsDto>
     * @Date: 2018/3/5 15:50
     * @Copyright: 重庆拉曼科技有限公司
     */
    public Page<SysUrlsDto> findByConditions(Pageable pageable, SysUrlsDto params) throws Exception{
        return this.BDS.findByConditions(pageable, params);
    }
}
