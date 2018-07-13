package com.laman.web.common.controller;

import com.laman.biz.original.app.service.OriginalAppService;
import com.laman.web.common.dto.CPViewResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends AbstractCommonController{

    private final OriginalAppService originalAppService;

    @Autowired
    public TestController(OriginalAppService originalAppService) {
        this.originalAppService = originalAppService;
    }

    @GetMapping(value = "/findAllOriginal.json",name = "测试-获取原始数据")
    public CPViewResultInfo findAllOriginal(CPViewResultInfo info){
        try {
            info.newSuccess(this.originalAppService.findALLOriginal());
        }catch (Exception e){
            info.newFalse(e);
        }
        return info;
    }
}
