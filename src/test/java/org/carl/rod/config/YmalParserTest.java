package org.carl.rod.config;

import org.carl.rod.config.base.RodBaseConfiguration;
import org.testng.annotations.Test;

/**
 * @author longjie
 * 2021/5/10
 */
public class YmalParserTest {

    @Test
    public void testParse() {
        RodBaseConfiguration rodBaseConfiguration = YamlConfigurationLoader.load("classpath:fishingrod-template.yml", RodBaseConfiguration.class);
        System.out.println(rodBaseConfiguration);
    }
}
