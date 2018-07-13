package com.laman.biz.mould.app.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laman.biz.mould.app.dto.MouldInfoDto;
import com.laman.biz.mould.domain.service.MouldInfoDomainService;
import com.laman.biz.task.app.dto.DetectiveItemDto;
import com.laman.biz.task.app.dto.SamplingItemDto;
import com.laman.biz.task.app.service.DetectiveItemAppService;
import com.laman.biz.task.app.service.SampleDetectionResultAppService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.beans.BeanToMap;
import com.zds.common.lang.exception.BusinessException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Title: MouldInfoAppService
 * @Description: 模板信息应用层服务
 * @Author: Away
 * @Date: 2018/6/22 10:07
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class MouldInfoAppService extends BaseAppService<MouldInfoDomainService> {

    private final SampleDetectionResultAppService sampleDetectionResultAppService;

    private final DetectiveItemAppService detectiveItemAppService;

    @Autowired
    public MouldInfoAppService(SampleDetectionResultAppService sampleDetectionResultAppService, DetectiveItemAppService detectiveItemAppService) {
        this.sampleDetectionResultAppService = sampleDetectionResultAppService;
        this.detectiveItemAppService = detectiveItemAppService;
    }

    /**
     * @Method: findByMouldCode
     * @Author: Away
     * @Version: v1.0
     * @See: 按照模编号查找
     * @Param: mouldCode
     * @Return: com.laman.biz.mould.app.dto.MouldInfoDto
     * @Date: 2018/6/22 10:07
     */
    public MouldInfoDto findByMouldCode(String mouldCode) throws BusinessException {
        return this.BDS.findByMouldCode(mouldCode);
    }

    /**
     * @Method: findByMouldBelongId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照模板所属节点ID查找模板
     * @Param: infoDto
     * @Return: com.laman.biz.mould.app.dto.MouldInfoDto
     * @Date: 2018/7/4 17:01
     */
    public MouldInfoDto findByMouldBelongId(Long mouldTreeId) throws BusinessException {
        return this.BDS.findByMouldBelongId(mouldTreeId);
    }

    /**
     * @Method: deleteByMouldBelongId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据所属节点ID删除模板
     * @Param: mouldBelongId
     * @Return: void
     * @Date: 2018/7/5 14:12
     */
    public void deleteByMouldBelongId(Long mouldBelongId) throws BusinessException {
        this.BDS.deleteByMouldBelongId(mouldBelongId);
    }

    /**
     * @Method: findByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 按照条件查找分页数据
     * @Param: pageRequest
     * @Param: mouldInfoDto
     * @Return: org.springframework.data.domain.Page<com.laman.biz.mould.app.dto.MouldInfoDto>
     * @Date: 2018/7/5 19:10
     */
    public Page<MouldInfoDto> findByConditions(PageRequest pageRequest, MouldInfoDto mouldInfoDto) throws BusinessException {
        return this.BDS.findByConditions(pageRequest, mouldInfoDto);
    }

    /**
     * @Method:  generateData
     * @Author: Away
     * @Version: v1.0
     * @See: 生成数据表格数据
     * @Param: samplingItemDto
     * @Return: com.laman.biz.mould.app.dto.MouldInfoDto
     * @Date: 2018/7/10 11:13
     */
    public MouldInfoDto generateData(SamplingItemDto samplingItemDto) throws Exception {
        if (ObjectHelper.isNotEmpty(samplingItemDto)) {
            MouldInfoDto mouldData = this.findByMouldBelongId(samplingItemDto.getMouldBelongId());
            if (ObjectHelper.isNotEmpty(mouldData) && ObjectHelper.isNotEmpty(mouldData.getId())) {
                String resourceData = mouldData.getMouldData();
                if (ObjectHelper.isNotEmpty(resourceData)) {
                    mouldData = replaceVariable(samplingItemDto, mouldData);
                }
            } else {
                throw new BusinessException(ENUM_EXCEPTION.E10030.code, ENUM_EXCEPTION.E10030.msg);
            }
            return mouldData;
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  replaceVariable
     * @Author: Away
     * @Version: v1.0
     * @See: 替换变量
     * @Param: samplingItemDto
     * @Param: mouldInfoDto
     * @Return: com.laman.biz.mould.app.dto.MouldInfoDto
     * @Date: 2018/7/10 11:14
     */
    private MouldInfoDto replaceVariable(SamplingItemDto samplingItemDto, MouldInfoDto mouldInfoDto) throws Exception {
        //获取模板里面需要合并单元格的行数
        JSONArray mergeCellJsonArray = null;
        if (ObjectHelper.isNotEmpty(mouldInfoDto.getMouldMergeCell())) {
            mergeCellJsonArray = JSONArray.parseArray(mouldInfoDto.getMouldMergeCell());
        }
        //合并单元格数据
        JSONArray jsonArray = new JSONArray();
        //单元格样式
        JSONArray sourceCss = JSONArray.parseArray(mouldInfoDto.getMouldClasses());
        JSONArray cssArray = new JSONArray();
        Map<String, Object> hasSavedData = this.getHasSavedData(samplingItemDto);
        //获得表格中的公共部分
        Map<String, Object> tempData = (Map<String, Object>) hasSavedData.get("common");

        List<Map<String, Object>> tempTestData = (List<Map<String, Object>>) hasSavedData.get("list");
        //定义返回数据
        JSONArray circleReturnJsonArray = new JSONArray();
        if (ObjectHelper.isNotEmpty(mouldInfoDto.getMouldData())) {
            //将模板数据转换为json数组
            JSONArray mouldDataJsonArray = JSONArray.parseArray(mouldInfoDto.getMouldData());
            if (ObjectHelper.isNotEmpty(mouldDataJsonArray)) {
                for (int i = 0; i < mouldDataJsonArray.size(); i++) {
                    if (mouldDataJsonArray.get(i).toString().contains("aliasCode=\"{circle}\"")) {
                        //移除下一个含有{circle}的数据
                        mouldDataJsonArray.remove(i + 1);
                        //移除下一个含有{circle}的数据的同时移除被移除行的样式
                        sourceCss.remove(i + 1);
                        //先组装行数不固定的列表数据
                        JSONObject mouldLineData = (JSONObject) mouldDataJsonArray.get(i);
                        for (Map<String, Object> tempSource : tempTestData) {
                            List<String> newMouldLineDataArray = new ArrayList<>();
                            for (int n = 0; n < mouldLineData.size(); n++) {
                                Document document = Jsoup.parse(mouldLineData.get(n).toString());
                                Elements elements = document.getElementsByAttribute("aliasCode");
                                for (int t = 0; t < elements.size(); t++) {
                                    Element cellElement = elements.get(t);
                                    String attrValue = cellElement.attr("aliasCode");
                                    cellElement.attr("samplingCode",tempSource.get("samplingCode") + "");
                                    cellElement.attr("style","");
                                    cellElement.text(tempSource.get(attrValue) + "");
                                    //查找检测结果并更新结果或存入结果
                                    this.sampleDetectionResultAppService.saveDataBeforeCheckExsit(tempSource.get("samplingCode") + "",
                                            attrValue, samplingItemDto.getBusinessCode(), samplingItemDto.getDetectiveCode(), tempSource.get(attrValue) + "");
                                }
                                newMouldLineDataArray.add(document.getElementsByTag("body").html());
                            }
                            circleReturnJsonArray.add(JSON.toJSONString(newMouldLineDataArray));
                            cssArray.add(sourceCss.get(i));
                        }
                        //再延伸单元格合并数据
                        if (ObjectHelper.isNotEmpty(mergeCellJsonArray)) {
                            for (int j = 0; j < mergeCellJsonArray.size(); j++) {
                                JSONObject jsonObject = mergeCellJsonArray.getJSONObject(j);
                                if (jsonObject.getInteger("row") == i && jsonObject.getInteger("rowspan") == 2) {
                                    //{"row":0,"col":2,"rowspan":3,"colspan":1,"removed":false}
                                    String newMergeCell = "{\"row\":" + jsonObject.getInteger("row") + ",\"col\":" + jsonObject.getInteger("col") + ",\"rowspan\":" + tempTestData.size() + ",\"colspan\":" + jsonObject.getInteger("colspan") + ",\"removed\":" + jsonObject.getBoolean("removed") + "}";
                                    jsonArray.add(JSONObject.parseObject(newMergeCell));
                                } else if (jsonObject.getInteger("row") != i + 1) {
                                    if (jsonObject.getInteger("row") > i + 1) {
                                        String newMergeCellDown = "{\"row\":" + (jsonObject.getInteger("row") + (tempTestData.size() - 2)) + ",\"col\":" + jsonObject.getInteger("col") + ",\"rowspan\":" + jsonObject.getInteger("rowspan") + ",\"colspan\":" + jsonObject.getInteger("colspan") + ",\"removed\":" + jsonObject.getBoolean("removed") + "}";
                                        jsonArray.add(JSONObject.parseObject(newMergeCellDown));
                                    } else {
                                        jsonArray.add(jsonObject);
                                    }
                                } else if (jsonObject.getInteger("row") == i + 1) {
                                    if (tempTestData.size() > 1) {
                                        for (int k = 0; k < tempTestData.size() - 1; k++) {
                                            String newMergeCellRest = "{\"row\":" + (jsonObject.getInteger("row") + k) + ",\"col\":" + jsonObject.getInteger("col") + ",\"rowspan\":" + jsonObject.getInteger("rowspan") + ",\"colspan\":" + jsonObject.getInteger("colspan") + ",\"removed\":" + jsonObject.getBoolean("removed") + "}";
                                            jsonArray.add(JSONObject.parseObject(newMergeCellRest));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        JSONObject mouldLineData = (JSONObject) mouldDataJsonArray.get(i);
                        List<String> newMouldLineDataArray = new ArrayList<>();
                        for (int n = 0; n < mouldLineData.size(); n++) {
                            Document document = Jsoup.parse(mouldLineData.get(n).toString());
                            Elements elements = document.getElementsByAttribute("aliasCode");
                            for (int t = 0; t < elements.size(); t++) {
                                Element cellElement = elements.get(t);
                                String attrValue = cellElement.attr("aliasCode");
                                cellElement.attr("style","");
                                //特殊标签处理，<img>标签、<input>标签等
                                if(cellElement.toString().contains("<img")){
                                    cellElement.attr("src",tempData.get(attrValue)+"");
                                }else{
                                    cellElement.text(tempData.get(attrValue) + "");
                                }
                                //查找检测结果并更新结果或存入结果
                                this.sampleDetectionResultAppService.saveDataBeforeCheckExsit(null,
                                        attrValue, samplingItemDto.getBusinessCode(), samplingItemDto.getDetectiveCode(), tempData.get(attrValue) + "");
                            }
                            newMouldLineDataArray.add(document.getElementsByTag("body").html());
                        }
                        circleReturnJsonArray.add(JSON.toJSONString(newMouldLineDataArray));
                        cssArray.add(sourceCss.get(i));
                    }
                }
            }
        }
        mouldInfoDto.setMouldData(circleReturnJsonArray.toJSONString());
        mouldInfoDto.setMouldMergeCell(ObjectHelper.isNotEmpty(jsonArray) ? jsonArray.toJSONString() : "");
        mouldInfoDto.setMouldClasses(JSON.toJSONString(cssArray, SerializerFeature.DisableCircularReferenceDetect));//消除对同一对象循环引用的问题
        return mouldInfoDto;
    }

    /**
     * @Method: getHasSavedData
     * @Author: Away
     * @Version: v1.0
     * @See: 将已经保存的检测结果值组装为map形式，分为公共部分和样品列表部分
     * @Param: sampleDetectionResultDto
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date: 2018/7/9 16:53
     */
    private Map<String, Object> getHasSavedData(SamplingItemDto samplingItemDto) throws BusinessException {
        if (ObjectHelper.isNotEmpty(samplingItemDto)) {
            //参数验证
            if (ObjectHelper.isEmpty(samplingItemDto.getBusinessCode()))
                throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
            if (ObjectHelper.isEmpty(samplingItemDto.getDetectiveCode()))
                throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
            //获得检测项数据
            DetectiveItemDto detectionItemDto = this.detectiveItemAppService.findByBusinessCodeAndDetectiveItemCode(samplingItemDto.getBusinessCode(), samplingItemDto.getDetectiveCode());
            //获得并组装检测结果值 公共部分key为common,样品列key为list
            Map<String, Object> sourceData = this.sampleDetectionResultAppService.getResultAndPackageData(samplingItemDto);
            Map<String, Object> commonData = (Map<String, Object>) sourceData.get("common");
            Map<String, Object> detectionMap = BeanToMap.convert(detectionItemDto);
            //所有数据以检测结果为准，当检测结果里面没有值时才以检测项公共数据为准
            for (String commonKeys : commonData.keySet()) {
                detectionMap.remove(commonKeys);
            }
            commonData.putAll(detectionMap);
            return sourceData;
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

//    private List<String> variableMatch(String s) {
//        List<String> results = new ArrayList<String>();
//        Pattern p = Pattern.compile("\\{([\\w]*)\\}");
//        Matcher m = p.matcher(s);
//        while (m.find()) {
//            results.add(m.group(1));
//        }
//        return results;
//    }

}
