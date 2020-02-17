package cn.imooc.controller;

import cn.imooc.dao.es.EsBlogDao;
import cn.imooc.dao.mysql.MysqlBlogDao;
import cn.imooc.entity.es.EsBlog;
import cn.imooc.entity.mysql.MysqlBlog;
import cn.imooc.vo.Params;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@RestController
public class DataController {

    @Autowired
    MysqlBlogDao mysqlBlogDao;
    @Autowired
    EsBlogDao esBlogDao;

    //首页初加载展示所有
    @GetMapping("/blogs")
    public Object blogs(){
            List<MysqlBlog> mysqlBlogs = mysqlBlogDao.queryAll();
            return mysqlBlogs;
    }

    /**
     * 
     * @param id
     *
     * @return
     */
    @GetMapping("/blog/{id}")
    public MysqlBlog blog(@PathVariable Integer id){
        Optional<MysqlBlog> byId = mysqlBlogDao.findById(id);
        //Optional 对象 有可能为空，jdk8引入,获取使用get
        MysqlBlog mysqlBlog = byId.get();
        return mysqlBlog;


    }

    @PostMapping("search")
    public Object search(@RequestBody Params params){
        String type = params.getType();
        HashMap<String,Object> map = new HashMap<>();
        StopWatch watch = new StopWatch();
        watch.start();
        if("mysql".equals(type)) {
            //mysql
            List<MysqlBlog> mysqlBlogs = mysqlBlogDao.queryBlogs(params.getKeyword());
            map.put("list",mysqlBlogs);
        } else if("es".equals(type)){
            /**ES 的 DSL查询语句
             * POST /blog/_search
             * {
             *   "query": {
             *     "bool": {
             *       "should": [
             *         {
             *           "match": {
             *             "title": "springboot"
             *           }
             *         },
             *         {
             *           "match": {
             *             "content": "springboot"
             *           }
             *         }
             *       ]
             *     }
             *   }
             * }
             */

            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title",params.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("content",params.getKeyword()));
            String s = builder.toString();
            System.out.println(s);
            Page<EsBlog> search = (Page<EsBlog>) esBlogDao.search(builder);
            //强转为Page对象后，获取page中的内容
            List<EsBlog> content = search.getContent();
            map.put("list",content);

            //es
        } else {
            return "I don't understand!";
        }
        watch.stop();
        long duration = watch.getTotalTimeMillis();
        map.put("duration",duration);
        return map;
    }



}
