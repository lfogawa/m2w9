package com.devinhouse.m2w9.exercises.controller;

import com.devinhouse.m2w9.exercises.dtos.MultaRequest;
import com.devinhouse.m2w9.exercises.dtos.MultaResponse;
import com.devinhouse.m2w9.exercises.dtos.VeiculoResponse;
import com.devinhouse.m2w9.exercises.model.Multa;
import com.devinhouse.m2w9.exercises.model.Usuario;
import com.devinhouse.m2w9.exercises.model.Veiculo;
import com.devinhouse.m2w9.exercises.dtos.VeiculoRequest;
import com.devinhouse.m2w9.exercises.model.enums.Role;
import com.devinhouse.m2w9.exercises.model.enums.TipoVeiculo;
import com.devinhouse.m2w9.exercises.repository.VeiculoRepository;
import com.devinhouse.m2w9.exercises.service.UsuarioService;
import com.devinhouse.m2w9.exercises.service.VeiculoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> consultar() {
        var veiculos = veiculoService.consultar();
        var resp = new ArrayList<VeiculoResponse>();
        for(Veiculo veiculo:veiculos){
            var veicDTO = mapper.map(veiculo, VeiculoResponse.class);
            if(veiculo.temMultas()){
                var multasDTO = veiculo.getMultas().stream().
                        map(m -> mapper.map(m, MultaResponse.class)).toList();
                veicDTO.setMultas(multasDTO);
            }
            resp.add(veicDTO);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<VeiculoResponse> consultar(@PathVariable("placa") String placa) {
        Veiculo veiculo = veiculoService.consultar(placa);
        var resp = mapper.map(veiculo, VeiculoResponse.class);
        if(veiculo.temMultas()){
            var multasDTO = veiculo.getMultas().stream().
                    map(m -> mapper.map(m, MultaResponse.class)).toList();
            resp.setMultas(multasDTO);
        }
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrarVeiculo(@RequestBody @Valid VeiculoRequest request){
        var veiculo = mapper.map(request, Veiculo.class);
        veiculo = veiculoService.salvar(veiculo);
        var resp = mapper.map(veiculo, VeiculoResponse.class);
        return ResponseEntity.created(URI.create(veiculo.getPlaca())).body(resp);
    }

    @PostMapping("{placa}/multas")
    public ResponseEntity<MultaResponse> cadastrarMulta(@PathVariable("placa") String placa, @RequestBody @Valid MultaRequest request){
        var multa = mapper.map(request, Multa.class);
        multa = veiculoService.cadastrarMulta(placa, multa);
        var resp = mapper.map(multa, MultaResponse.class);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/dados")
    public ResponseEntity<?> carregarDados() {
        var veiculos = veiculoService.consultar();
        if (veiculos.isEmpty()) {
            Veiculo veiculo1 = new Veiculo("ABC-1234", TipoVeiculo.AUTOMOVEL, "Bat-Movel", 2022, "preto");
            Veiculo veiculo2 = new Veiculo("BCA-4321", TipoVeiculo.ONIBUS, "Enterprise", 1960, "prata");
            veiculoService.salvar(veiculo1);
            veiculoService.salvar(veiculo2);
            Multa multa1Veic1 = new Multa("Gothan City", "Farol apagado", 250F, veiculo1);
            Multa multa2Veic1 = new Multa("Gothan City", "Insulfilm", 100F, veiculo1);
            Multa multa1Veic2 = new Multa("Hiper-espaço", "Excesso velocidade", 400F, veiculo2);
            veiculoService.cadastrarMulta(veiculo1.getPlaca(),multa1Veic1);
            veiculoService.cadastrarMulta(veiculo1.getPlaca(),multa2Veic1);
            veiculoService.cadastrarMulta(veiculo2.getPlaca(),multa1Veic2);
        }
        var usuarios = usuarioService.consultar();
        if (usuarios.isEmpty()) {
            usuarioService.inserir(new Usuario("James Kirk", "james@enterprise.com", "123456", Role.ADMIN));
            usuarioService.inserir(new Usuario("Spock", "spock@enterprise.com", "123456", Role.ADMIN));
            usuarioService.inserir(new Usuario("Leonard McCoy", "mccoy@enterprise.com", "123456", Role.USUARIO));
            usuarioService.inserir(new Usuario("Montgomery Scott", "scott@enterprise.com", "123456", Role.USUARIO));
        }
        return ResponseEntity.ok().build();
    }

}
