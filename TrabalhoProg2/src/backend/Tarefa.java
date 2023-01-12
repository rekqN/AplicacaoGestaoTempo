
package backend;

import java.io.Serializable;
import java.util.Date;

public class Tarefa implements Serializable{
    
    private String nome;
    private Date inicio;
    private Float preco_h;
    private String estado;
    private Date fim;

    public Tarefa(String nome, Date inicio, Float preco_h) {
        this.nome = nome;
        this.inicio = inicio;
        this.preco_h = preco_h;
        this.estado = "Em curso";
    }
    
    public void finalizar(){
        this.estado = "Finalizado";
        this.fim = new Date();
    }
    

    
    
    
    public String getNome() {
        return nome;
    }

    public Date getInicio() {
        return inicio;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFim() {
        return fim;
    }

    public Float getPreco_h() {
        return preco_h;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public void setPreco_h(Float preco_h) {
        this.preco_h = preco_h;
    }
       
}
