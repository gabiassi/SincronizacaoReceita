package br.com.sincronizacao_receita.config.cs;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

public class ContasConversionService implements ConversionService {

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return sourceType == String.class && targetType == double.class;
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.equals(TypeDescriptor.valueOf(String.class)) &&
                targetType.equals(TypeDescriptor.valueOf(double.class));
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return (T) Double.valueOf(source.toString().replace(',', '.'));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Double.valueOf(source.toString().replace(',', '.'));
    }
}