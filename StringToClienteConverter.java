package com.springboot.automotora.Converter;


import com.springboot.automotora.Model.Entity.Cliente;
import com.springboot.automotora.Model.Entity.Vendedor;
import com.springboot.automotora.Model.Service.IClienteService;
import com.springboot.automotora.Model.Service.IVendedorService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToClienteConverter implements Converter<String,Cliente> {

    private final IClienteService clienteService;

    public StringToClienteConverter(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public Cliente convert(String source) {
        if ((source == null) || source.isEmpty())
        {
            return null;
        }
        Long id = Long.parseLong(source);
        return clienteService.fineOne(id);
    }
}
