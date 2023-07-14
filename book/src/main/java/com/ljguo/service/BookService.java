package com.ljguo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljguo.pojo.Book;
import com.ljguo.utils.PageBean;
import com.ljguo.utils.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService extends IService<Book> {
    R selectAll(PageBean pageBean);

    R add(MultipartFile[] files, Book book) throws IOException;

    R update(Book book);

    R delete(Integer id);

    R selectById(Integer id);

    R findCategories();
}
