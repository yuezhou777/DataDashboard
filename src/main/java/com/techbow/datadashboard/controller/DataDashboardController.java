package com.techbow.datadashboard.controller;

import com.techbow.datadashboard.model.dao.DataDao;
import com.techbow.datadashboard.model.dao.impl.DataJpaDao;
import com.techbow.datadashboard.model.dvo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //bean == java object managed bu Spring Containers
public class DataDashboardController { // Controller relies on DAO

    //@Qualifier("dataJpaDao")
    @Autowired
    //private DataDao dataDao = new DataJpaDao(); //coupling
    private DataDao dataDao;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping("/data")
    public Data createData(@RequestBody Data data) { //JSON会自动转化成java的Data object
        return dataDao.save(data);
    }

    @Cacheable(value = "DataCacheNamespace", key = "#id")
    @GetMapping("/data/{id}")
    public Data getDataById(@PathVariable Long id) {
        System.out.println("\nCalling dataById from DAO for data:\n" + id);
        return dataDao.findById(id); //作为response body, 又把java转化成JSON
    }

    @GetMapping("/data")
    public List<Data> getAllData(@RequestParam(name = "limit", required = false) String limit) { //localhost:8080/data?limit=2
        return dataDao.findAll(limit);
    }

    @GetMapping("/data/client/{clientId}")
    public List<Data> findDataByClientId(
            @PathVariable Long clientId,
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end
    ) {
        return dataDao.findByClientId(clientId, field, sort, start, end);
    }

    @CachePut(value = "dataCache", key = "#id")
    @PutMapping("/data/{id}")
    public Data updateData(@PathVariable Long id, @RequestBody Data data) {
        return dataDao.update(id, data);
    }

}

