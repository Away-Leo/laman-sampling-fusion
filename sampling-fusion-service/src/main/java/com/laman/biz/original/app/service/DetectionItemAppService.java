package com.laman.biz.original.app.service;

import com.laman.biz.original.app.dto.DetectionItemDto;
import com.laman.biz.original.domain.service.DetectionItemDomainService;
import com.laman.fusion.base.base.BaseAppService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
* @Title: DetectionItemAppService
* @Description:  检测项编号应用服务
* @Author: Away
* @Date: 2018/7/5 17:16
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class DetectionItemAppService extends BaseAppService<DetectionItemDomainService>{

    /**
     * @Method:  findByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询
     * @Param: pageRequest
     * @Param: detectionItemDto
     * @Return: org.springframework.data.domain.Page<com.laman.biz.original.app.dto.DetectionItemDto>
     * @Date: 2018/7/5 17:18
     */
    public Page<DetectionItemDto> findByConditions(PageRequest pageRequest,DetectionItemDto detectionItemDto){
        return this.BDS.findByConditions(pageRequest, detectionItemDto);
    }

    /**
     * @Method:  deleteAndBatchSaveFromSqlServer
     * @Author: Away
     * @Version: v1.0
     * @See: 批量保存检测项
     * @Param:
     * @Return: void
     * @Date: 2018/7/5 18:46
     */
    public void deleteAndBatchSaveFromSqlServer(){
        this.BDS.batchSaveItemBeforDleteAll();
    }
}
