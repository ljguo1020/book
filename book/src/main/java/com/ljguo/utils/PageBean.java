package com.ljguo.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean implements Serializable {
    private Integer page;
    private Integer size;

    private String title;

    private String category;

}



