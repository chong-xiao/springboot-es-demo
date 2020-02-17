package cn.imooc.dao.es;

import cn.imooc.entity.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogDao extends ElasticsearchRepository<EsBlog,Integer> {
}
