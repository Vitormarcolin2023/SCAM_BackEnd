package org.scam.controller.cadastros;

public enum Curso {

    ADMINISTRADOR("Administração"),
    AGRONOMIA("Agronomia"),
    ANALISE_E_DESENVOLVIMENTO_DE_SISTEMAS("Análise e Desenvolvimento de Sistemas"),
    ARQUITETURA_E_URBANISMO("Arquitetura e Urbanismo"),
    BIOMEDICINA("Biomedicina"),
    CIENCIAS_BIOLOGICAS("Ciências Biológicas"),
    CIENCIAS_CONTABEIS("Ciências Contábeis"),
    DESIGN_GRAFICA_DIGITAL("Design Gráfico Digital"),
    DIREITO("Direito"),
    EDUCACAO_FISICA("Educação Física"),
    ENFERMAGEM("Enfermagem"),
    ENGENHARIA_CIVIL("Engenharia Civil"),
    ENGENHARIA_DE_SOFTWARE("Engenharia de Software"),
    ENGENHARIA_ELETRICA("Engenharia Elétrica"),
    ENGENHARIA_MECANICA("Engenharia Mecânica"),
    FARMACIA("Farmácia"),
    FISIOTERAPIA("Fisioterapia"),
    MEDICINA_VETERINARIA("Medicina Veterinária"),
    NUTRICAO("Nutrição"),
    PEDAGOGIA("Pedagogia"),
    PSICOLOGIA("Psicologia"),
    PUBLICIDADE_E_PROPAGANDA("Publicidade e Propaganda");

    private final String descricao;

    Curso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}