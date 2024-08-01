package com.springboot.automotora.Model.Dao;

import com.springboot.automotora.Model.Entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente,Long> {
}
