package project.plants.controller;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableCaching
public class ImageFinishService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CacheManager cacheManager;

    public void saveCache() {

        Cache cache = cacheManager.getCache("page-texts");
        Ehcache ehcache = (Ehcache) cache.getNativeCache();
        System.out.println("@@@@@");
        System.out.println(ehcache.getKeys());

        for (Object key : ehcache.getKeys()) {
            Element element = ehcache.get(key);
            String page = key.toString().split("-")[2];
            System.out.println(page);
            String text = (String) element.getObjectValue();
            System.out.println(text);

            // 데이터베이스에 저장
            String sql = "INSERT INTO write_test (page, text) VALUES (?, ?)";
            jdbcTemplate.update(sql, page, text);
        }
    }
}
