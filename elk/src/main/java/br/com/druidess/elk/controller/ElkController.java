/**
 * 
 */
package br.com.druidess.elk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/elk")
public class ElkController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ElkController.class);

	@GetMapping
	public String helloWorld() {
		String message = "Hello World!";
		LOGGER.info(message);
		return message;
	}

}
