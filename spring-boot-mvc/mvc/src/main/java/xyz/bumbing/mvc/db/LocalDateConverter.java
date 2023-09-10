package xyz.bumbing.mvc.db;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//DB가 DATE 타입이고 서버랑 타임존이 다를때 Entity 수정할때마다 날짜가 변경됨
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convertToDatabaseColumn(LocalDate plainText) {
        if (plainText != null) {
            return plainText.format(dateTimeFormatter);
        }
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(String date) {
        if (date != null) {
            return LocalDate.parse(date, dateTimeFormatter);
        }
        return null;
    }
}
