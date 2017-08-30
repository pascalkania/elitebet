package de.kania.elitebet.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private static final Log LOGGER = LogFactory.getLog(CacheService.class);

    @Scheduled(cron = "0 0 */6 * * *")
    @CacheEvict(cacheNames = {FootballDataService.TEAMLIST, FootballDataService.AKTUELLE_TABLLENPLATZ_MAP})
    public void resetCaches(){
        LOGGER.info("Cache leeren");
    }
}
