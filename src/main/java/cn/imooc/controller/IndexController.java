package cn.imooc.controller;

import cn.imooc.dao.mysql.MysqlBlogDao;
import cn.imooc.entity.mysql.MysqlBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MysqlBlogDao mysqlBlogDao;

    @RequestMapping("/")
    public String index(){
        List<MysqlBlog> list = mysqlBlogDao.findAll();
        System.out.println(list.size());

        return "index.html";
    }
}
