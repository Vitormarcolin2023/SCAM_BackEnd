package org.scam.model.entities;

import javax.persistence.*;

@Entity(name = "tb_endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rua", nullable = false, length = 45)
    private String rua;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "bairro", nullable = false, length = 45)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 45)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 45)
    private String estado;

    @Column(name = "cep", nullable = false, length = 45)
    private String cep;

    @Override
    public String toString(){
        return rua + "," + numero + "-" + bairro + "," + cidade + "-" +  estado + ", CEP:" + cep;
    }


    public EnderecoEntity(){} //construtor padrão

    public EnderecoEntity(
        String rua, Integer numero, String bairro,
        String cidade, String estado, String cep){
            this.rua = rua;
            this.numero = numero;
            this.bairro = bairro;
            this.cidade = cidade;
            this.estado = estado;
            this.cep = cep;
    }

    //getters e setters
    public Long getIdEndereco() {return id; }
    public void setIdEndereco(Long id) {this.id = id; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public Integer getNumero(){return numero;}
    public void setNumero(Integer numero) {this.numero = numero;}

    public String getBairro() {return bairro;}
    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public String getCep() {return cep;}
    public void setCep(String cep) {this.cep = cep;}
}
