package com.globant.internal.oncocureassist.util

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

import javax.annotation.PreDestroy

@Configuration
class TestConfig {

    @Primary
    @Bean
    File wekaDir() {
        File dir = new File(System.getProperty('user.home') + File.separator + 'test' + File.separator + 'weka')
        dir.mkdirs()

        return dir
    }


    @PreDestroy
    void deleteDir() {
        wekaDir().deleteDir()
    }
}
