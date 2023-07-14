package com.ljguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljguo.mapper.BookMapper;
import com.ljguo.pojo.Book;
import com.ljguo.service.BookService;
import com.ljguo.service.FDFSService;
import com.ljguo.utils.PageBean;
import com.ljguo.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    BookMapper bookMapper;

    @Resource
    FDFSService fdfsService;


    @Override
    public R selectAll(PageBean pageBean) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();

        // 条件查询, 根据书名模糊查询
        queryWrapper.like("title", pageBean.getTitle());

        queryWrapper.like("category",pageBean.getCategory());

        // 开启分页
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());

        List<Book> books = bookMapper.selectList(queryWrapper);
        PageInfo<Book> pageInfo = new PageInfo<>(books);

        return R.ok(pageInfo);
    }

    @Override
    public R add(MultipartFile[] files, Book book) throws IOException {

        // 上传文件, 获取 url
        String url = fdfsService.uploadFile(files[0]);
        String image = fdfsService.uploadFile(files[1]);

        // 设置 url
        book.setImages(image);
        book.setUrl(url);
        int insert = bookMapper.insert(book);
        if (insert > 0) return R.ok();
        return R.error("添加失败，请稍后重试");
    }

    @Override
    public R update(Book book) {
        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", book.getId());
        int update = bookMapper.update(book, updateWrapper);
        if (update > 0) return R.ok();
        return R.error("修改失败，请稍后重试");
    }

    @Override
    public R delete(Integer id) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        int delete = bookMapper.delete(queryWrapper);
        if (delete > 0) return R.ok();
        return R.error("删除失败，请稍后重试");
    }

    @Override
    public R selectById(Integer id) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Book book = bookMapper.selectOne(queryWrapper);
        if (book != null) {
            return R.ok(book);
        }
        return R.error("查询失败，请稍后重试");
    }

    @Override
    public R findCategories() {
        String[] categories = bookMapper.findCategories();
        return R.ok(categories);
    }
}
