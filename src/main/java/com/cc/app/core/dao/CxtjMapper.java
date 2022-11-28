package com.cc.app.core.dao;

import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CxtjMapper {

    Page ckxxcx(Map<String, Object> params);

    Page sjlhtj(Map<String, Object> params);

    Page zxgzhtj(Map<String, Object> params);

    Page jcgstj(Map<String, Object> params);

    Page tcfytj(Map<String, Object> params);

    Page zxqd(Map<String, Object> params);

    List ndjhtj(Map<String, Object> params);
}