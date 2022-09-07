package com.example.bibliosystem.batch;

import com.example.bibliosystem.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PretProcessor implements ItemProcessor<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public String process(String data) throws Exception {
        System.out.println("MyCustomProcessor : Processing data : "+data);
        data = data.toUpperCase();
        logger.info("MyCustomProcessor : Processing data : "+data);
        return data;
    }

}
