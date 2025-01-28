package br.com.alura.tabela.fip.principal;

import br.com.alura.tabela.fip.service.consumiApi;

import java.util.Scanner;

public class principal {
    private final String UrlBase = "https://parallelum.com.br/fipe/api/v1/";
    private consumiApi consumo = new consumiApi();
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

        System.out.println(Json);

    }


}
