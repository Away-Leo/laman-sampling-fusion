package com.laman.web.front.controller.sampling;

import com.laman.biz.task.app.service.SamplingItemAppService;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SamplingController extends AbstractFrontController{

    private final SamplingItemAppService samplingItemAppService;

    @Autowired
    public SamplingController(SamplingItemAppService samplingItemAppService) {
        this.samplingItemAppService = samplingItemAppService;
    }

    /**
     * @Method:  findSamplingListByBusinessCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据检测业务单号查找样品列表
     * @Param: info
     * @Param: businessCode
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/7/7 13:46
     */
    @GetMapping(value = "/sampling/findSamplingListByBusinessCode.json",name = "样品-根据检测业务单号查找样品列表")
    public CPViewResultInfo findSamplingListByBusinessCode(CPViewResultInfo info,String businessCode){
        try{
            info.newSuccess(this.samplingItemAppService.findByBusinessCode(businessCode));
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
}
