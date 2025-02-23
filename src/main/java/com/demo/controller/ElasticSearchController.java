package com.demo.controller;


//import io.swagger.annotations.ApiOperation;

import com.demo.es.EsDemo;
import lombok.extern.slf4j.Slf4j;
//import com.demo.es.OrderDoc;
//import com.demo.es.OrderDocService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//@Api(tags = "ES测试模块")
@RestController
@Slf4j
@RequestMapping("/es")
public class ElasticSearchController {

//    @Autowired
//    private OrderDocService orderDocService;
//
//

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    /// /    @ApiOperation(value = "es测试接口", notes = "es测试接口")
//    @GetMapping(value = "/test" )
//    public void saveAll() {
//        log.info("save all start");
//        orderDocService.saveAll(Arrays.asList(
//                new OrderDoc("1",100000l,"苹果电脑"),
//                new OrderDoc("2",100000l,"苹果电脑"),
//                new OrderDoc("3",100000l,"苹果电脑"),
//                new OrderDoc("4",100000l,"苹果电脑"),
//                new OrderDoc("5",100000l,"苹果电脑"),
//                new OrderDoc("6",100000l,"苹果电脑"),
//                new OrderDoc("7",100000l,"苹果电脑")
//        ));
//    }
    @PostMapping
    public void save(@RequestBody EsDemo esDemo) {
        // id字段必填
        esDemo.setCreateTime(LocalDateTime.now());
        elasticsearchOperations.save(esDemo);
    }


    @PutMapping
    public void update(@RequestBody EsDemo esDemo) {
        elasticsearchOperations.update(esDemo);
    }

    @DeleteMapping("/{demoId}")
    public void delete(@PathVariable("demoId") Integer demoId) {
        elasticsearchOperations.delete(demoId.toString(), EsDemo.class);
    }

    @GetMapping("/{demoId}")
    public EsDemo getInfo(@PathVariable("demoId") Integer demoId) {
        Criteria criteria = new Criteria("demoId").is(demoId);
        Query query = new CriteriaQuery(criteria);
        SearchHit<EsDemo> searchHit = elasticsearchOperations.searchOne(query, EsDemo.class);
        return Objects.isNull(searchHit) ? null : searchHit.getContent();
    }

    @GetMapping("/list")
    public List<EsDemo> list() {
        Criteria criteria = new Criteria();
        Query query = new CriteriaQuery(criteria);
        SearchHits<EsDemo> searchHits = elasticsearchOperations.search(query, EsDemo.class);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @GetMapping("/page")
    public Page<EsDemo> page(int pageNum, int pageSize) {
        Criteria criteria = new Criteria();
        PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
        Query query = new CriteriaQuery(criteria).setPageable(pageRequest);
        SearchHits<EsDemo> searchHits = elasticsearchOperations.search(query, EsDemo.class);
        List<EsDemo> esDemos = searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
        return new PageImpl<>(esDemos, pageRequest, searchHits.getTotalHits());
    }


}