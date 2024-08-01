package com.springboot.facturacion.Converter;



import com.springboot.automotora.Model.Service.IVendedorService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.springboot.automotora.Model.Entity.Vendedor;

@Component
public class StringToVendedorConverter implements Converter<String,Vendedor> {

    private final IVendedorService vendedorService;

    public StringToVendedorConverter(IVendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Override
    public Vendedor convert(String source) {
        if ((source == null) || source.isEmpty())
            {
               return null;
            }
        Long id = Long.parseLong(source);
        return vendedorService.fineOne(id);
    }
}
