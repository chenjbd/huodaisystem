package com.cc.app.base.dao;

import com.cc.app.base.model.SeqObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeqObjectMapper {
    int getSeq(SeqObject record);
}