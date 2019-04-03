package com.github.perryvaldez.seebooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.perryvaldez.seebooks.datalayer.KeyUtilities;
import com.github.perryvaldez.seebooks.datalayer.impl.DbNumericKeyUtilities;

@Configuration
public class DataLayerConfig {
	@Bean
	public KeyUtilities keyUtilities() {
		return new DbNumericKeyUtilities();
	}
}
