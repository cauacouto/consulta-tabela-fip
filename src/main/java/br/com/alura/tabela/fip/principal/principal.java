package br.com.alura.tabela.fip.principal;

import br.com.alura.tabela.fip.model.Modelos;
import br.com.alura.tabela.fip.model.Veiculo;
import br.com.alura.tabela.fip.model.dados;
import br.com.alura.tabela.fip.service.ConverteDados;
import br.com.alura.tabela.fip.service.consumiApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class principal {
    private final String UrlBase = "https://parallelum.com.br/fipe/api/v1/";
    private consumiApi consumo = new consumiApi();
    private ConverteDados converso = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);

    public void exiberMenu(){

        var menu= """
             **** opçãos ****
             carro
             moto
             caminhão
              
             digite uma das opções para coonsulta:
               """;
        System.out.println(menu);
        var opcao = leitura.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("carr")){
            endereco= UrlBase + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco= UrlBase + "motos/marcas";
        }else endereco= UrlBase + "caminhoes/marcas";

        var Json= consumo.ObeterDados(endereco);



        var marcas= converso.obeterlista(Json, dados.class);

        marcas.stream()
                .sorted(Comparator.comparing(dados::codigo))
                .forEach(System.out::println);

        System.out.println("insira o codigo da marca para consulta:");

        var codigoMarca = leitura.nextLine();


        endereco = endereco + "/" + codigoMarca + "/modelos";
        Json = consumo.ObeterDados(endereco);

        var modeloLista = converso.OberterDados(Json,Modelos.class);

        System.out.println("\nModelos dessa marca:");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(dados::codigo))
                .forEach(System.out::println);


        System.out.println("\n digite um trecho do nome do carro  a ser buscado");
        var nomeDoVeiculo = leitura.nextLine();

        List<dados>  modelosFiltrados = modeloLista.modelos().stream()
                .filter(m-> m.nome().toLowerCase().contains(nomeDoVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\n Modelos filtrados");
        modelosFiltrados.forEach(System.out::println);


        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        Json = consumo.ObeterDados(endereco);
        List<dados> anos = converso.obeterlista(Json,dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            Json = consumo.ObeterDados(enderecoAnos);

            Veiculo veiculo = converso.OberterDados(Json,Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("todos os veiculos filtrados com avalição pro ano ");
        veiculos.forEach(System.out::println);
    }
}
