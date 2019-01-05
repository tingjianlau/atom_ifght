package me.ifight.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.ifight.model.BookBean;
import me.ifight.model.common.RestResponse;
import me.ifight.service.impl.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "书籍查询")
@RequestMapping(value = "/api/book")
public class BookController {
    private final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping(value = "/{id}")
    @ApiOperation("根据ID查询书籍信息")
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "int")
    public RestResponse queryBookById(@PathVariable int id){
        BookBean bookBean = bookService.queryBookById(id);

        if(bookBean != null){
            return RestResponse.succuess(bookBean);
        }else{
            return RestResponse.fail("id is invalid");
        }

    }
}
