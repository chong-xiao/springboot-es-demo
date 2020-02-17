package cn.imooc.dao.mysql;

import cn.imooc.entity.mysql.MysqlBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 */
//JpaRepository<MysqlBlog,Integer> 这里2个泛型的说明，第一个不用说，第二个是指主键的类型
public interface MysqlBlogDao extends JpaRepository<MysqlBlog, Integer> {
    @Query("select e from MysqlBlog  e order by e.createTime desc ")
    List<MysqlBlog> queryAll();

    @Query("select e from MysqlBlog e where e.title " +
            "like concat('%',:keyword,'%') or e.content like concat('%',:keyword,'%') ")
    List<MysqlBlog> queryBlogs(String keyword);
}
