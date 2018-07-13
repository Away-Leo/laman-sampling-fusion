package com.laman.web.front.controller.mould;

import com.laman.biz.mould.app.dto.MouldInfoDto;
import com.laman.biz.mould.app.dto.MouldTreeDto;
import com.laman.biz.mould.app.service.MouldInfoAppService;
import com.laman.biz.mould.app.service.MouldTreeAppService;
import com.laman.biz.mould.domain.entity.MouldInfo;
import com.laman.biz.mould.domain.entity.MouldTree;
import com.laman.biz.tableproperty.app.dto.PropertyTreeDto;
import com.laman.biz.tableproperty.app.dto.TablePropertyDto;
import com.laman.biz.tableproperty.app.dto.TablePropertyRequestDto;
import com.laman.biz.tableproperty.app.service.PropertyTreeAppService;
import com.laman.biz.tableproperty.app.service.TablePropertyAppService;
import com.laman.biz.tableproperty.domain.entity.PropertyTree;
import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MouldController extends AbstractFrontController{

    private final MouldInfoAppService mouldInfoAppService;

    private final MouldTreeAppService mouldTreeAppService;

    private final TablePropertyAppService tablePropertyAppService;

    private final PropertyTreeAppService propertyTreeAppService;

    @Autowired
    public MouldController(MouldInfoAppService mouldInfoAppService,MouldTreeAppService mouldTreeAppService,
                           TablePropertyAppService tablePropertyAppService,
                           PropertyTreeAppService propertyTreeAppService) {
        this.mouldTreeAppService=mouldTreeAppService;
        this.mouldInfoAppService = mouldInfoAppService;
        this.tablePropertyAppService=tablePropertyAppService;
        this.propertyTreeAppService=propertyTreeAppService;
    }

    /**
     * @Method:  findByMenuCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照模板编号查找模板
     * @Param: info
     * @Param: menuCode
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/22 10:24
     */
    @GetMapping(value = "/mould/findByMenuCode.json",name = "模板-按照模板唯一编号查找模板")
    public CPViewResultInfo findByMenuCode(CPViewResultInfo info,String mouldCode){
        try{
            info.newSuccess(mouldInfoAppService.findByMouldCode(mouldCode));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findByBelongId
     * @Author: Away
     * @Version: v1.0
     * @See: 模板-按照所属节点ID查找模板
     * @Param: info
     * @Param: infoDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/4 17:04
     */
    @GetMapping(value = "/mould/findByBelongId.json",name = "模板-按照所属节点ID查找模板")
    public CPViewResultInfo findByBelongId(CPViewResultInfo info,Long mouldTreeId){
        try{
            info.newSuccess(this.mouldInfoAppService.findByMouldBelongId(mouldTreeId));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateMould
     * @Author: Away
     * @Version: v1.0
     * @See: 更新或新建模板信息
     * @Param: info
     * @Param: dto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/22 10:28
     */
    @PostMapping(value = "/mould/saveOrUpdateMould.json",name = "模板-更新或新建模板信息")
    public CPViewResultInfo saveOrUpdateMould(CPViewResultInfo info, @RequestBody MouldInfoDto dto){
        try{
            info.newSuccess(this.mouldInfoAppService.saveOrUpdateData(dto, MouldInfo.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  deleteMouldById
     * @Author: Away
     * @Version: v1.0
     * @See: 根据模板ID删除
     * @Param: info
     * @Param: dto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/22 10:28
     */
    @PostMapping(value = "/mould/deleteMouldById.json",name = "模板-根据模板ID删除模板信息")
    public CPViewResultInfo deleteMouldById(CPViewResultInfo info, @RequestBody MouldInfoDto dto){
        try{
            this.mouldInfoAppService.physicalDelete(dto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findPageByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查找分页数据
     * @Param: info
     * @Param: mouldInfoDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/5 19:11
     */
    @PostMapping(value = "/mould/findPageByConditions.json",name = "模板-根据条件查找分页数据")
    public CPViewResultInfo findPageByConditions(CPViewResultInfo info,@RequestBody MouldInfoDto mouldInfoDto){
        try{
            info.newSuccess(this.mouldInfoAppService.findByConditions(new PageRequest(mouldInfoDto.getPage(),mouldInfoDto.getSize()),mouldInfoDto));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  deleteByMouldBelongId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据模板所属节点删除模板
     * @Param: info
     * @Param: dto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/22 10:28
     */
    @PostMapping(value = "/mould/deleteByMouldBelongId.json",name = "模板-根据模板所属节点删除模板")
    public CPViewResultInfo deleteByMouldBelongId(CPViewResultInfo info, @RequestBody MouldInfoDto dto){
        try{
            this.mouldInfoAppService.deleteByMouldBelongId(dto.getMouldBelongId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  writeDataToMould
     * @Author: Away
     * @Version: v1.0
     * @See: 将采样数据写入模板中
     * @Param: info
     * @Param: menuCode
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/22 10:24
     */
    @PostMapping(value = "/mould/writeDataToMould.json",name = "模板-将采样数据写入模板中")
    public CPViewResultInfo writeDataToMould(CPViewResultInfo info,SamplingItemDto samplingItemDto){
        try{
            info.newSuccess(mouldInfoAppService.generateData(samplingItemDto));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateMouldTree
     * @Author: Away
     * @Version: v1.0
     * @See: 模板-更新或保存模板树节点
     * @Param: info
     * @Param: treeDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/4 17:21
     */
    @PostMapping(value = "/mould/saveOrUpdateMouldTree.json",name = "模板-更新或保存模板树节点")
    public CPViewResultInfo saveOrUpdateMouldTree(CPViewResultInfo info, @RequestBody MouldTreeDto treeDto){
        try {
            info.newSuccess(this.mouldTreeAppService.saveOrUpdateData(treeDto, MouldTree.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findAllMouldTree
     * @Author: Away
     * @Version: v1.0
     * @See: 模板-查找所有模板树节点
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/4 17:24
     */
    @GetMapping(value = "/mould/findAllMouldTree.json",name = "模板-查找所有模板树节点")
    public CPViewResultInfo findAllMouldTree(CPViewResultInfo info){
        try{
            info.newSuccess(this.mouldTreeAppService.findAllList(MouldTreeDto.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findMouldTreeByName
     * @Author: Away
     * @Version: v1.0
     * @See: 模板-根据名称模糊查找模板树节点
     * @Param: info
     * @Param: name
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/4 17:28
     */
    @GetMapping(value = "/mould/findMouldTreeByName.json",name = "模板-根据名称模糊查找模板树节点")
    public CPViewResultInfo findMouldTreeByName(CPViewResultInfo info,String name){
        try{
            info.newSuccess(this.mouldTreeAppService.findByName(name));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  deleteByMouldTreeId
     * @Author: Away
     * @Version: v1.0
     * @See:  模板-根据模板树节点ID删除节点
     * @Param: info
     * @Param: dto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/4 17:36
     */
    @PostMapping(value = "/mould/deleteByMouldTreeId.json",name = "模板-根据模板树节点ID删除节点")
    public CPViewResultInfo deleteByMouldTreeId(CPViewResultInfo info,@RequestBody MouldTreeDto dto){
        try{
            this.mouldTreeAppService.physicalDelete(dto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  deleteByMouldTreeIdAndDeleteMould
     * @Author: Away
     * @Version: v1.0
     * @See:  模板-根据模板树节点ID删除节点的同时删除模板
     * @Param: info
     * @Param: dto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/4 17:36
     */
    @PostMapping(value = "/mould/deleteByMouldTreeIdAndDeleteMould.json",name = "模板-根据模板树节点ID删除节点的同时删除模板")
    public CPViewResultInfo deleteByMouldTreeIdAndDeleteMould(CPViewResultInfo info,@RequestBody MouldTreeDto dto){
        try{
            this.mouldTreeAppService.physicalDelete(dto.getId());
            this.mouldInfoAppService.deleteByMouldBelongId(dto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findAllAndPackage
     * @Author: Away
     * @Version: v1.0
     * @See: 获根据查询条件获取属性
     * @Param: info
     * @Param: condition
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/11 18:51
     */
    @PostMapping(value = "/mould/findAllAndPackage.json",name = "模板-获根据查询条件获取组装好大类及其子节点属性")
    public CPViewResultInfo findAllAndPackage(CPViewResultInfo info,@RequestBody TablePropertyDto condition){
        try{
            info.newSuccess(this.tablePropertyAppService.findAllAndPackage(condition));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findAllPropertyCategory
     * @Author: Away
     * @Version: v1.0
     * @See: 获得所有以保存的属性大类
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/11 20:38
     */
    @GetMapping(value = "/mould/findAllPropertyCategory.json",name = "模板-获得所有已保存的属性大类")
    public CPViewResultInfo findAllPropertyCategory(CPViewResultInfo info){
        try{
            info.newSuccess(this.tablePropertyAppService.findAllPropertyTreeId());
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }


    /**
     * @Method:  saveOrUpdateProperty
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或删除属性值
     * @Param: info
     * @Param: tablePropertyRequestDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/11 18:54
     */
    @PostMapping(value = "/mould/saveOrUpdateProperty.json",name = "模板-新增或更新属性值")
    public CPViewResultInfo saveOrUpdateProperty(CPViewResultInfo info,@RequestBody TablePropertyRequestDto tablePropertyRequestDto){
        try{
            info.newSuccess(this.tablePropertyAppService.saveOrUpdateProperty(tablePropertyRequestDto));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  deleteProperty
     * @Author: Away
     * @Version: v1.0
     * @See: 删除模板属性
     * @Param: info
     * @Param: id
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/11 18:56
     */
    @GetMapping(value = "/mould/deleteProperty.json",name = "模板-删除模板属性")
    public CPViewResultInfo deleteProperty(CPViewResultInfo info,String ids){
        try{
            this.tablePropertyAppService.batchDelete(ids);
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdatePropertyTree
     * @Author: Away
     * @Version: v1.0
     * @See:  新增或修改表格属性树
     * @Param: info
     * @Param: propertyTreeDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/12 11:50
     */
    @PostMapping(value = "/mould/saveOrUpdatePropertyTree.json",name = "模板-新增或修改表格属性树")
    public CPViewResultInfo saveOrUpdatePropertyTree(CPViewResultInfo info, @RequestBody PropertyTreeDto propertyTreeDto){
        try{
            info.newSuccess(this.propertyTreeAppService.saveOrUpdateData(propertyTreeDto, PropertyTree.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findAllPropertyTree
     * @Author: Away
     * @Version: v1.0
     * @See: 查找所有属性树节点
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/12 11:56
     */
    @GetMapping(value = "/mould/findAllPropertyTree.json",name = "模板-查找所有属性树节点")
    public CPViewResultInfo findAllPropertyTree(CPViewResultInfo info){
        try{
            info.newSuccess(this.propertyTreeAppService.findAllList(PropertyTreeDto.class));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findPropertyByCondition
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查询属性列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/12 11:56
     */
    @PostMapping(value = "/mould/findPropertyByCondition.json",name = "模板-根据条件查询属性列表")
    public CPViewResultInfo findPropertyByCondition(CPViewResultInfo info,@RequestBody TablePropertyDto tablePropertyDto){
        try{
            info.newSuccess(this.tablePropertyAppService.findByCondition(tablePropertyDto));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  deleteTreeNode
     * @Author: Away
     * @Version: v1.0
     * @See: 删除属性树节点
     * @Param: info
     * @Param: id
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/12 12:01
     */
    @GetMapping(value = "/mould/deleteTreeNodeAndDeleteProperty.json",name = "模板-删除属性树节点的同时删除此节点对应的所有属性值")
    public CPViewResultInfo deleteTreeNode(CPViewResultInfo info,Long id){
        try{
            this.tablePropertyAppService.deleteByPropertyTreeId(id);
            this.propertyTreeAppService.physicalDelete(id);
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  generatePropertyCode
     * @Author: Away
     * @Version: v1.0
     * @See: 生成模板属性编号
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/13 10:30
     */
    @GetMapping(value = "/mould/generatePropertyCode.json",name = "模板-生成模板属性编号")
    public CPViewResultInfo generatePropertyCode(CPViewResultInfo info){
        try{
            info.newSuccess(new Date().getTime());
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

}
