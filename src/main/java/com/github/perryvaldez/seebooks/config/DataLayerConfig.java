package com.github.perryvaldez.seebooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.perryvaldez.seebooks.datalayer.KeyGen;
import com.github.perryvaldez.seebooks.datalayer.impl.DbNumericKeyGen;

@Configuration
public class DataLayerConfig {
	@Bean
	public KeyGen keyGen() {
		return new DbNumericKeyGen();
	}
}
