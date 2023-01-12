package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Projeto implements Serializable{

    private String nome;
    private String nome_cliente;
    private Float preco_h;
    private ArrayList<Tarefa> tarefas;

    private ArrayList<Utilizador> participantes;

    public Projeto(String nome, String nome_cliente, Float preco_h) {
        this.nome = nome;
        this.nome_cliente = nome_cliente;
        this.preco_h = preco_h;
        this.participantes = new ArrayList();
        this.tarefas = new ArrayList();
    }

    public void criarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public void apagarTarefaporNome(String nome) {

        //procura a tarefa pelo nome e apaga-a
        for (Tarefa tarefa : this.tarefas) {
            if (tarefa.getNome().equals(nome)) {
                this.tarefas.remove(tarefa);
                return;
            }
        }

    }

    public void finalizarTarefaporNome(String nome) {
        for (Tarefa tarefa : this.tarefas) {
            if (tarefa.getNome().equals(nome)) {
                tarefa.finalizar();
                return;
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public Float getPreco_h() {
        return preco_h;
    }

    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    public ArrayList<Utilizador> getParticipantes() {
        return participantes;
    }

}
