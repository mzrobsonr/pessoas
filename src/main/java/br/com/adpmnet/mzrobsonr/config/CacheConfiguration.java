package br.com.adpmnet.mzrobsonr.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(br.com.adpmnet.mzrobsonr.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTPai.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTPai.class.getName() + ".idpais", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTUfe.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTUfe.class.getName() + ".idufes", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTCid.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTCid.class.getName() + ".idufes", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTBai.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTBai.class.getName() + ".idbais", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTBac.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTBac.class.getName() + ".idbacs", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTTlg.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTTlg.class.getName() + ".idtlgs", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTLog.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTLog.class.getName() + ".idlogs", jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTLgb.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.adpmnet.mzrobsonr.domain.SconTCon.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
