package com.funcionario.client.endereco.feignclient;

import com.funcionario.client.endereco.dto.LocalidadesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${endereco.servico.url}")
public interface LocalidadesClient {
    @GetMapping("/{cep}/json")
    LocalidadesDto getEnderecoDto(@PathVariable String cep);
}
