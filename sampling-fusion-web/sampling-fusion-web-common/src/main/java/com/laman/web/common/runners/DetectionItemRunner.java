package com.laman.web.common.runners;

import com.laman.biz.original.app.service.DetectionItemAppService;
import com.laman.biz.original.domain.component.QueryComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @Title: HFApplicationRunner.java
 * @Description:  应用初始化扫描所有的接口地址并存入数据库
 * @Author: Away
 * @Date: 2018/4/24 10:39
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Slf4j
@Component
@Order(value = 2)
public class DetectionItemRunner implements ApplicationRunner{

    private final DetectionItemAppService detectionItemAppService;

    @Autowired
    public DetectionItemRunner(DetectionItemAppService detectionItemAppService, QueryComponent queryComponent) {
        this.detectionItemAppService = detectionItemAppService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        detectionItemAppService.deleteAndBatchSaveFromSqlServer();
    }

}
