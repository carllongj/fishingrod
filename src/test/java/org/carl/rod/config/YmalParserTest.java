package org.carl.rod.config;

import org.carl.rod.config.base.RodBaseConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author longjie
 * 2021/5/10
 */
public class YmalParserTest {

	@Test
	public void testParse() {
		RodBaseConfiguration rodBaseConfiguration = YamlConfigurationLoader.load("classpath:fishingrod-template.yml", RodBaseConfiguration.class);
		Assert.assertNotNull(rodBaseConfiguration);
	}

	@Test
	public void loadReference() {
		Yaml yaml = new Yaml();
		InputStream stream = ClassLoader.getSystemResourceAsStream("test.yml");
		Object load = yaml.load(stream);
		System.out.println(load);
	}
}
