package com.laman.biz.tableproperty.app.service;

import com.laman.biz.tableproperty.app.dto.TablePropertyDto;
import com.laman.biz.tableproperty.app.dto.TablePropertyOptionDto;
import com.laman.biz.tableproperty.app.dto.TablePropertyRequestDto;
import com.laman.biz.tableproperty.app.dto.TablePropertyReturnDto;
import com.laman.biz.tableproperty.domain.entity.TableProperty;
import com.laman.biz.tableproperty.domain.service.TablePropertyDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TablePropertyAppService
 * @Description: 表格属性应用服务
 * @Author: Away
 * @Date: 2018/7/11 11:57
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class TablePropertyAppService extends BaseAppService<TablePropertyDomainService> {

    /**
     * @Method: findAllAndPackage
     * @Author: Away
     * @Version: v1.0
     * @See: 查找所有属性值，并且按照大类组装
     * @Param:
     * @Return: java.util.List<com.laman.biz.tableproperty.app.dto.TablePropertyReturnDto>
     * @Date: 2018/7/11 15:06
     */
    public List<TablePropertyReturnDto> findAllAndPackage(TablePropertyDto condition) {
        //查找所有大类
        List<TablePropertyDto> source = this.BDS.findAllPropertyTreeId();
        if (ObjectHelper.isNotEmpty(source) && source.size() > 0) {
            List<TablePropertyReturnDto> returnData = new ArrayList<>();
            for (TablePropertyDto temp : source) {
                TablePropertyReturnDto tablePropertyReturnDto = new TablePropertyReturnDto();
                tablePropertyReturnDto.setPropertyTreeId(temp.getPropertyTreeId());
                tablePropertyReturnDto.setPropertyTreeName(temp.getPropertyTreeName());
                //组装查询条件
                condition.setPropertyTreeId(temp.getPropertyTreeId());
                tablePropertyReturnDto.setChildren(this.BDS.findByCondition(condition));
                returnData.add(tablePropertyReturnDto);
            }
            return returnData;
        } else {
            return null;
        }
    }

    /**
     * @Method:  findByCondition
     * @Author: Away
     * @Version: v1.0
     * @See: 按照条件查找属性列表
     * @Param: tablePropertyDto
     * @Return: java.util.List<com.laman.biz.tableproperty.app.dto.TablePropertyDto>
     * @Date: 2018/7/12 14:29
     */
    public List<TablePropertyDto> findByCondition(TablePropertyDto tablePropertyDto){
        return this.BDS.findByCondition(tablePropertyDto);
    }

    /**
     * @Method:  findAllPropertyCategoryCode
     * @Author: Away
     * @Version: v1.0
     * @See: 查找所有大类
     * @Param:
     * @Return: java.util.List<com.laman.biz.tableproperty.app.dto.TablePropertyDto>
     * @Date: 2018/7/11 20:35
     */
    public List<TablePropertyDto> findAllPropertyTreeId(){
        return this.BDS.findAllPropertyTreeId();
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
        this.BDS.deleteByPropertyTreeId(propertyTreeId);
    }

    /**
     * @Method:  saveOrUpdateProperty
     * @Author: Away
     * @Version: v1.0
     * @See: 更新或保存表格属性
     * @Param: tablePropertyRequestDto
     * @Return: com.laman.biz.tableproperty.app.dto.TablePropertyDto
     * @Date: 2018/7/11 18:31
     */
    public TablePropertyDto saveOrUpdateProperty(TablePropertyRequestDto tablePropertyRequestDto) throws Exception {
        if (ObjectHelper.isNotEmpty(tablePropertyRequestDto)) {
            TablePropertyDto oldData = this.BDS.findByPropertyCode(tablePropertyRequestDto.getPropertyCode());
            if (ObjectHelper.isEmpty(oldData)) {
                oldData = new TablePropertyDto();
            }
            oldData.setPropertyTreeId(tablePropertyRequestDto.getPropertyTreeId());
            oldData.setPropertyTreeName(tablePropertyRequestDto.getPropertyTreeName());
            oldData.setPropertyCode(tablePropertyRequestDto.getPropertyCode());
            oldData.setPropertyName(tablePropertyRequestDto.getPropertyName());
            oldData.setPropertyLabelType(tablePropertyRequestDto.getPropertyLabelType());
            StringBuilder html = new StringBuilder();
            switch (tablePropertyRequestDto.getPropertyLabelType()) {
                case "img":
                    html.append("<img src=\"\" aliasCode=\"").append(tablePropertyRequestDto.getPropertyCode()).append("\" />");
                    break;
                case "radio":
                    html.append(tablePropertyRequestDto.getPropertyName()).append(":");
                    for (TablePropertyOptionDto temp : tablePropertyRequestDto.getOptions()) {
                        html.append("<input  type=\"radio\" value=\"").append(temp.getOptionValue()).append("\" name=\"").append(tablePropertyRequestDto.getPropertyCode()).append("\"")
                                .append(" aliasCode=\"").append(tablePropertyRequestDto.getPropertyCode()).append("\">")
                                .append(temp.getOptionName()).append("</input>");
                    }
                    break;
                case "checkbox":
                    html.append(tablePropertyRequestDto.getPropertyName()).append(":");
                    for (TablePropertyOptionDto temp : tablePropertyRequestDto.getOptions()) {
                        html.append("<input type=\"checkbox\" name=\"").append(tablePropertyRequestDto.getPropertyCode()).append("\"").append(" value=\"").append(temp.getOptionValue())
                                .append("\"").append(" aliasCode=\"").append(tablePropertyRequestDto.getPropertyCode()).append("\">")
                                .append(temp.getOptionName()).append("</input>");
                    }
                    break;
                case "select":
                    html.append(tablePropertyRequestDto.getPropertyName()).append(": <select aliasCode=\"").append(tablePropertyRequestDto.getPropertyCode())
                    .append("\" ").append("name=\"").append(tablePropertyRequestDto.getPropertyCode()).append("\" >");
                    for(TablePropertyOptionDto temp:tablePropertyRequestDto.getOptions()){
                        html.append("<option value =\"").append(temp.getOptionValue()).append("\" >").append(temp.getOptionName()).append("</option>");
                    }
                    html.append("</select>");
                    break;

                case "span":
                    html.append("<span style=\"font-weight:bold;color:#287bd3e8\" aliasCode=\"").append(tablePropertyRequestDto.getPropertyCode()).append("\" >").append(tablePropertyRequestDto.getPropertyName()).append("</span>");
                    break;
            }
            oldData.setPropertyHtml(html.toString());
            return this.BDS.saveOrUpdateData(oldData,TableProperty.class);
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

}
