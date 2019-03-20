package com.swmu.bszn.guide.controller;

import com.swmu.bszn.neo4j.service.Neo4jOperationService;
import com.swmu.common.response.MapResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author seven.mu
 * @Description: 办事指南neo4j操作控制层
 * @date 2019/3/12-10:02
 */
@Controller
@RequestMapping(value = "build")
public class GuideController {
    @Autowired
    private Neo4jOperationService neo4jOperationService;

    @RequestMapping("graph")
    @ResponseBody
    private MapResult buildGraph(){
        MapResult mapResult = new MapResult().ok();
        try{
            boolean b = neo4jOperationService.buildGuideGraph();
            mapResult.put("data", b);
        }catch (Exception e){
            e.printStackTrace();
            mapResult.put("data", false);
        }
        return mapResult;
    }

    @RequestMapping("intention")
    @ResponseBody
    private MapResult buildIntention(){
        MapResult mapResult = new MapResult().ok();
        try{
            boolean b = neo4jOperationService.buildIntentionSynonymGraph();
            mapResult.put("data", b);
        }catch (Exception e){
            e.printStackTrace();
            mapResult.put("data", false);
        }
        return mapResult;
    }

    @RequestMapping("keyword")
    @ResponseBody
    private MapResult buildKeyword(){
        MapResult mapResult = new MapResult().ok();
        try{
            boolean b = neo4jOperationService.buildKeywordSynonymGraph();
            mapResult.put("data", b);
        }catch (Exception e){
            e.printStackTrace();
            mapResult.put("data", false);
        }
        return mapResult;
    }


    @RequestMapping("area")
    @ResponseBody
    private MapResult buildArea(){
        MapResult mapResult = new MapResult().ok();
        try{
            boolean b = neo4jOperationService.buildAreaGraph();
            mapResult.put("data", b);
        }catch (Exception e){
            e.printStackTrace();
            mapResult.put("data", false);
        }
        return mapResult;
    }
}
