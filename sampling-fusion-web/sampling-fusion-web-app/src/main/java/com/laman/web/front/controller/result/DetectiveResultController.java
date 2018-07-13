package com.laman.web.front.controller.result;

import com.laman.biz.mould.app.service.MouldDetectionItemRelationAppService;
import com.laman.biz.mould.app.service.MouldInfoAppService;
import com.laman.biz.task.app.dto.SampleDetectionResultDto;
import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.biz.task.app.service.DetectiveItemAppService;
import com.laman.biz.task.app.service.SampleDetectionResultAppService;
import com.laman.biz.task.app.service.SamplingItemAppService;
import com.laman.biz.task.app.service.TaskAppService;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
* @Title: DetectiveResultController
* @Description:  检测结果控制器
* @Author: Away
* @Date: 2018/7/10 14:24
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@RestController
public class DetectiveResultController extends AbstractFrontController{

    private final TaskAppService taskAppService;

    private final SamplingItemAppService samplingItemAppService;

    private final SampleDetectionResultAppService sampleDetectionResultAppService;

    private final DetectiveItemAppService detectiveItemAppService;

    private final MouldDetectionItemRelationAppService mouldDetectionItemRelationAppService;

    private final MouldInfoAppService mouldInfoAppService;

    @Autowired
    public DetectiveResultController(TaskAppService taskAppService,
                                     SamplingItemAppService samplingItemAppService,
                                     SampleDetectionResultAppService sampleDetectionResultAppService,
                                     DetectiveItemAppService detectiveItemAppService,
                                     MouldDetectionItemRelationAppService mouldDetectionItemRelationAppService,
                                     MouldInfoAppService mouldInfoAppService) {
        this.taskAppService = taskAppService;
        this.samplingItemAppService = samplingItemAppService;
        this.sampleDetectionResultAppService = sampleDetectionResultAppService;
        this.detectiveItemAppService = detectiveItemAppService;
        this.mouldDetectionItemRelationAppService=mouldDetectionItemRelationAppService;
        this.mouldInfoAppService=mouldInfoAppService;
    }

    /**
     * @Method:  findTaskByBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据业务编号获取任务
     * @Param: info
     * @Param: businessCode
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/10 14:36
     */
    @GetMapping(value = "/result/findTaskByBusinessCode.json",name = "检测结果-根据业务编号获取任务")
    public CPViewResultInfo findTaskByBusinessCode(CPViewResultInfo info,String businessCode){
        try{
            info.newSuccess(this.taskAppService.findByBusinessCode(businessCode));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  findMouldsByDetectionCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据检测项编号查找模板
     * @Param: info
     * @Param: detectionCode
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/10 14:53
     */
    @GetMapping(value = "/result/findMouldsByDetectionCode.json",name = "检测结果-根据检测项编号查找模板")
    public CPViewResultInfo findMouldsByDetectionCode(CPViewResultInfo info,String detectionCode){
        try{
            info.newSuccess(this.mouldDetectionItemRelationAppService.findByDetectiveItemCode(detectionCode));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  generateTable
     * @Author: Away
     * @Version: v1.0
     * @See: 生成表格
     * @Param: info
     * @Param: samplingItemDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/10 15:10
     */
    @PostMapping(value = "/result/generateTable.json",name = "检测结果-生成表格")
    public CPViewResultInfo generateTable(CPViewResultInfo info,@RequestBody SamplingItemDto samplingItemDto){
        try{
            info.newSuccess(mouldInfoAppService.generateData(samplingItemDto));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

    /**
     * @Method:  writeTableData
     * @Author: Away
     * @Version: v1.0
     * @See: 保存表格中填充的值（前提是在模板配置中已经放置了变量）
     * @Param: info
     * @Param: sampleDetectionResultDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/10 15:53
     */
    @PostMapping(value = "/result/writeTableData.json",name = "检测结果-保存表格中填充的值（前提是在模板配置中已经放置了变量）")
    public CPViewResultInfo writeTableData(CPViewResultInfo info, @RequestBody SampleDetectionResultDto sampleDetectionResultDto){
        try{
            info.newSuccess(this.sampleDetectionResultAppService.saveDataBeforeCheckExist(sampleDetectionResultDto));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }

}
