package com.ljguo.controller;

import com.ljguo.mapper.BookMapper;
import com.ljguo.pojo.Book;
import com.ljguo.service.BookService;
import com.ljguo.service.FDFSService;
import com.ljguo.utils.PageBean;
import com.ljguo.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;




@RestController
@RequestMapping("/books")
public class BookController {
    @Resource
    private BookService bookService;

    @Resource
    private FDFSService fdfsService;

    @Resource
    private BookMapper bookMapper;


    @PostMapping("/page")
    public R selectAll(@RequestBody PageBean pageBean){
        return bookService.selectAll(pageBean);
    }

    @PostMapping
    public R add(@RequestParam("file") MultipartFile[] files, @RequestPart("book") Book book) throws IOException {
        return bookService.add(files,book);
    }

    @PutMapping
    public R update(@RequestBody Book book){
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Integer id){
        return bookService.delete(id);
    }

    @GetMapping("/{id}")
    public R selectById(@PathVariable("id") Integer id){
        return bookService.selectById(id);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fdfsService.uploadFile(file);
    }


    @GetMapping("/downloadFile")
    public void downloadFile(Integer id, HttpServletResponse response) throws IOException {
        Book book = bookService.getById(id);
        String url = book.getUrl();
        byte[] bytes = fdfsService.downloadFile(url);

        String fileName = URLEncoder.encode(book.getTitle().trim(),"utf-8") + " by " + URLEncoder.encode(book.getAuthor().trim(),"utf-8") + "(ljguo.top)." + url.split("\\.")[1];

        response.reset();
//        response.setContentType("application/octet-stream");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-disposition",
                "attachment; filename=" + fileName);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.close();
    }


    @GetMapping("findCategories")
    public R findCategories(){
        return bookService.findCategories();
    }

}




