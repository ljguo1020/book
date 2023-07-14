package com.ljguo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljguo.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    String[] findCategories();
}
