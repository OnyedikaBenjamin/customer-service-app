package com.example.customerservice.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class PriceConfig extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(bigDecimal.setScale(2, RoundingMode.HALF_UP).toString());
    }
}
