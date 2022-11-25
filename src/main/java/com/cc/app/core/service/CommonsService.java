package com.cc.app.core.service;

import com.cc.app.core.dao.OrganizationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonsService {

    private Logger logger = LoggerFactory.getLogger(CommonsService.class);

    @Autowired
    private OrganizationMapper organizationMapper;

    public List<Map<String,Object>> getDeptTreeByPId(Map<String,Object> params) {
        List<Map<String,Object>> list = organizationMapper.getDeptTreeByPId(params);
        return list;
    }
}
