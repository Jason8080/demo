package com.gm.demo.boot.es.controller;

import com.gm.demo.boot.es.dao.entity.BookBean;
import com.gm.demo.boot.es.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Jas°
 */
@RestController
@RequestMapping("book")
public class ElasticController {
    @Resource
    private BookService bookService;

    @RequestMapping("{id}")
    public BookBean getBookById(@PathVariable String id) {
        Optional<BookBean> opt = bookService.findById(id);
        BookBean book = opt.get();
        System.out.println(book);
        return book;
    }

    @RequestMapping("save")
    public void save() {
        BookBean book = new BookBean("1", "ES入门教程", "程裕强", "2018-10-01");
        System.out.println(book);
        bookService.save(book);
    }

}