package com.springboot.automotora.Controller;


import com.springboot.automotora.Model.Entity.Cliente;
import com.springboot.automotora.Model.Entity.Vendedor;
import com.springboot.automotora.Model.Service.IClienteService;
import com.springboot.automotora.Model.Service.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cliente")
public class clienteController {
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IVendedorService vendedorService;


    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo","Listado de Clientes");
        model.addAttribute("cliente",clienteService.findAll());
        return "cliente/listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String ,Object> model){
        Cliente cliente = new Cliente();
        List<Vendedor> vendedorList=vendedorService.findAll();
        model.put("titulo","Formulario de Cliente");
        model.put("cliente",cliente);
        model.put("listaVendedores",vendedorList);
        return "cliente/form";
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public  String  guardar(Cliente cliente){
        clienteService.save(cliente);
        return "redirect:listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value="id") Long id , Map<String , Object> model){
        Cliente cliente=null;
        if (id > 0)
            cliente = clienteService.fineOne(id);
        else
            return "redirect:listar";
        List<Vendedor> vendedorList=vendedorService.findAll();
        model.put("cliente",cliente);
        model.put("titulo","Editar Datos del  Cliente");
        model.put("listaVendedores",vendedorList);
        return "cliente/form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable (value = "id") Long id ){
        if (id > 0)
            clienteService.eliminar(id);
        return "redirect:/cliente/listar";

    }
}
