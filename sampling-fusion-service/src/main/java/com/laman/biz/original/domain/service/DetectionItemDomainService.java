package com.laman.biz.original.domain.service;

import com.laman.biz.original.app.dto.DetectionItemDto;
import com.laman.biz.original.domain.component.QueryComponent;
import com.laman.biz.original.domain.entity.DetectionItem;
import com.laman.biz.original.domain.repository.DetectionItemRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: DetectionItemDomainService
* @Description:  检测项
* @Author: Away
* @Date: 2018/7/5 18:45
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Slf4j
@Service
public class DetectionItemDomainService extends BaseDomainService<DetectionItemRepository,DetectionItem,DetectionItemDto>{


    private final QueryComponent queryComponent;

    @Autowired
    public DetectionItemDomainService(QueryComponent queryComponent) {
        this.queryComponent = queryComponent;
    }

    /**
     * @Method:  findByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询
     * @Param: pageRequest
     * @Param: detectionItemDto
     * @Return: org.springframework.data.domain.Page<com.laman.biz.original.app.dto.DetectionItemDto>
     * @Date: 2018/7/5 17:15
     */
    public Page<DetectionItemDto> findByConditions(PageRequest pageRequest,DetectionItemDto detectionItemDto) throws BusinessException{
        if(ObjectHelper.isNotEmpty(detectionItemDto)&&ObjectHelper.isNotEmpty(pageRequest)){
            return toDtoPage(this.CT.findByConditions(pageRequest, detectionItemDto),DetectionItemDto.class,pageRequest);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  batchSaveItemBeforDleteAll
     * @Author: Away
     * @Version: v1.0
     * @See: 批量保存检测项
     * @Param:
     * @Return: void
     * @Date: 2018/7/5 18:45
     */
    public void batchSaveItemBeforDleteAll(){
        try{
            String sql = "SELECT F_ITEM_CODE as \"itemCode\" " +
                    "      ,F_ITEM_NAME as \"itemName\" " +
                    "      ,F_ITEM_TYPE as \"itemType\" " +
                    "      ,F_STATE as \"state\" " +
                    "      ,F_LETTER as \"letter\" " +
                    "      ,F_NODE as \"node\" " +
                    "      ,F_PARENT_NODE as \"parentNode\" " +
                    "      ,f_item_old as \"itemOld\" " +
                    "      ,f_old_code as \"oldCode\" " +
                    "      ,f_re_code as \"reCode\" " +
                    "FROM PBT_ITEM ";

            List<DetectionItemDto> sourceDatas=this.queryComponent.findList(sql,DetectionItemDto.class);

            if(ObjectHelper.isNotEmpty(sourceDatas)){
                this.CT.deleteAll();
                this.batchSave(toEntityList(sourceDatas,DetectionItem.class),DetectionItemDto.class);
                log.info("保存检测项原始记录成功");
            }else{
                log.info("获取检测项原始数据为空");
            }
        }catch (Exception e){
            log.error("保存检测项原始记录出错",e);
        }
    }
}
