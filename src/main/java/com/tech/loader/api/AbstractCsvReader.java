package com.tech.loader.api;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public abstract class AbstractCsvReader<CSV> {

    private final Class<CSV> csvClass;

    protected AbstractCsvReader() {
        final Class<?>[] classes = GenericTypeResolver
                .resolveTypeArguments(getClass(), AbstractCsvReader.class);
        csvClass = (Class<CSV>) classes[0];
    }

    public List<CSV> readCSV(String absoluteFilePath) {
        BeanListProcessor<CSV> rowProcessor = new BeanListProcessor<>(csvClass);
        CsvParserSettings parserSettings = getCsvParserSettings();
        parserSettings.setProcessor(rowProcessor);

        CsvParser parser = new CsvParser(parserSettings);
        try {
            parser.parse(new InputStreamReader(new FileInputStream(absoluteFilePath), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            log.error("Error reading csv files {}", e.getMessage());
        }

        return rowProcessor.getBeans();
    }


    private CsvParserSettings getCsvParserSettings(){
        CsvParserSettings parserSettings = new CsvParserSettings();

        parserSettings.getFormat().setLineSeparator("\n");
        parserSettings.setNullValue("");
        parserSettings.setSkipEmptyLines(true);
        parserSettings.getFormat().setDelimiter('\t');
        parserSettings.trimValues(true);
        parserSettings.setHeaderExtractionEnabled(true);

        return parserSettings;
    }
}
