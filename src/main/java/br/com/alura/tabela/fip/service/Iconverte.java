package br.com.alura.tabela.fip.service;

public interface Iconverte {
    <T> T OberterDados (String json, Class<T> classe);
}
