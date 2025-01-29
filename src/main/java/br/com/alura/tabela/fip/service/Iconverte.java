package br.com.alura.tabela.fip.service;

import java.util.List;

public interface Iconverte {
    <T> T OberterDados (String json, Class<T> classe);

    <T> List<T> obeterlista (String json, Class<T> classe);
}
