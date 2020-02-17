package cn.imooc.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name = "t_blog")
@Entity
public class MysqlBlog {

    /**
     * CREATE TABLE `t_blog` (
     *    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
     *    `titile` varchar(60) DEFAULT NULL COMMENT '博客标题',
     *    `author` varchar(60) DEFAULT NULL COMMENT '博客作者',
     *    `content` mediumtext COMMENT '博客内容',
     *    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     *    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
     *    PRIMARY KEY (`id`)
     */

    /**
     * 以下的注解由hibernat jpa---javax.persistence.*提供
     * 本是javaEE标准备
     */

    @Id //自增主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //根据数据实现，自增主键随源数据库
    private Integer id;
    private String title;
    private String author;
    //这里的类型比较特殊，需声明
    @Column(columnDefinition = "mediumtext")
    private String content;
    //jpa自带驼峰自动转换
    private Date createTime;
    private Date updateTime;

}
