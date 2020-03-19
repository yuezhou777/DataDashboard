package com.techbow.datadashboard.model.dao;

import com.techbow.datadashboard.model.dvo.Data;

public interface DataDao {
    Data save(Data data);
    Data findById(Long id);
}
