package com.szq.cache.core.bs;
import com.szq.cache.api.ICache;
import com.szq.core.bs.CacheBs;
import org.junit.Assert;
import org.junit.Test;
/**
 * 缓存引导类测试
 */
public class CacheBsTest {

    /**
     * 大小指定测试
     */
    @Test
    public void helloTest() {
        ICache<String, String> cache = CacheBs.<String,String>newInstance()
                .size(2)
                .build();

        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");

        Assert.assertEquals(2, cache.size());
        System.out.println(cache.keySet());
    }
}
