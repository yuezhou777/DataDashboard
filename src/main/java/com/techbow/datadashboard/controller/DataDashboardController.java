package com.techbow.datadashboard.controller;

import com.techbow.datadashboard.model.dao.DataDao;
import com.techbow.datadashboard.model.dao.impl.DataHashMapDao;
import com.techbow.datadashboard.model.dao.impl.DataJpaDao;
import com.techbow.datadashboard.model.dvo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController //bean == java object managed bu Spring Containers
public class DataDashboardController { // Controller relies on DAO

    @Qualifier("dataJpaDao")
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

    @GetMapping("/data/{id}")
    public Data getDataById(@PathVariable Long id) {
        return dataDao.findById(id); //作为response body, 又把java转化成JSON
    }
}
