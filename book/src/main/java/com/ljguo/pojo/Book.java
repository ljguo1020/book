package com.ljguo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("book")
public class Book implements Serializable {
    private Integer id;
    private String title;
    private String author;
    private String category;
    private String description;
    private String images;

    private String url;


}
