package com.alfa.web.util.Cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/24.
 */
@Service
public class CacheController {
    private static final Logger log = Logger
                  .getLogger(MethodCacheInterceptor.class);

    private Cache cache;

    private CacheManager cacheManager;

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 从缓存中获取对象
     * @param cacheName
     * @param key
     * @param isDefault
     * @return
     */
    public Object getObjectCached(Boolean isDefault,String cacheName, Serializable key){
        Cache cache = isDefault?this.getCache():getCache(cacheName);
        if(cache!=null){
            try {
                Element elem = cache.get(key);
                if(elem!=null && !cache.isExpired(elem))
                    return elem.getObjectValue();
            } catch (Exception e) {
                log.error("Get cache("+cacheName+") of "+key+" failed.", e);
            }
        }
        return null;
    }

    /**
     * 获取默认cache key value
     * @param key
     * @return
     */
    public Object getObjectCachedByDefault(Serializable key) {
        Cache cache = this.getCache();
        if (cache != null) {
            Element elem = cache.get(key);
            if (elem != null && !cache.isExpired(elem))
                return elem.getObjectValue();
        }
        return null;
    }


    /**
     * 把对象放入默认的cache缓存中
     * @param key
     * @param value
     */
    public synchronized void putByDefault(Object key, Object value) {
        Cache cache = this.getCache();
        cache.remove(key);
        Element elem = new Element(key, value);
        cache.put(elem);
    }

    /**
     * 把对象放入缓存中
     * @param cacheName
     * @param key
     * @param value
     */
    public synchronized void put(Boolean isDefault,String cacheName, Object key, Object value){
        Cache cache = isDefault?this.getCache():getCache(cacheName);
        if(cache==null){
            cache= new Cache(cacheName, 1000000, true, true, 0, 0);
            cacheManager.addCache(cache);
        }else{
            try {
                cache.remove(key);
                Element elem = new Element(key, value);
                cache.put(elem);
            } catch (Exception e) {
                log.error("put cache("+cacheName+") of "+key+" failed.", e);
            }
        }
    }

    /**
     * 获取指定名称的缓存
     * @param cacheName
     * @return
     * @throws IllegalStateException
     */
    public Cache getCache(String cacheName) throws IllegalStateException {
        return cacheManager.getCache(cacheName);
    }

    /**
     * 获取缓冲中的信息
     * @param isDefault
     * @param cacheName
     * @param key
     * @return
     */
    public Element getElement(Boolean isDefault,String cacheName, Object key){
        Cache cCache = isDefault?this.getCache():getCache(cacheName);
        return cCache.get(key);
    }

    public void removeElementByDefault(Object key){
        cache.remove(key);
    }

    public void removeElement(Boolean isDefault,String cacheName, Object key) {
        Cache cache = isDefault?this.getCache():getCache(cacheName);
        if (cache != null) {
            cache.remove(key);
        }
    }

    public void removeCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cacheManager.removeCache(cacheName);
        }
    }


    /**
     * 停止缓存管理器
     */
    public void shutdown(){
        if(cacheManager!=null)
            cacheManager.shutdown();
    }


}
