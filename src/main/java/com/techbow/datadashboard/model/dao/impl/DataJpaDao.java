package com.techbow.datadashboard.model.dao.impl;

import com.techbow.datadashboard.model.dao.DataDao;
import com.techbow.datadashboard.model.dao.DataJpaRepository;
import com.techbow.datadashboard.model.dvo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataJpaDao implements DataDao {

    @Autowired
    private DataJpaRepository repository;

    @Override
    public Data save(Data data) {
        return repository.save(data);
    }

    @Override
    public Data findById(Long id) {
        return repository.findById(id).get();
    }
}
