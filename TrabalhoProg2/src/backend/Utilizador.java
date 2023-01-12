
package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilizador implements Serializable{
    
    private String nome;
    private String username;
    private String password;
    private Float horas;
    
    private ArrayList<Projeto> projetos;

    public Utilizador(String nome, String username, String password, Float horas) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.horas = horas;
        this.projetos = new ArrayList();
    }
    
    public void adicionarProjeto(Projeto projeto){
        this.projetos.add(projeto);
    }
    
    
    public Projeto getProjetoPorNome(String nome) {
        
        for (Projeto proj : projetos){
            
            if (proj.getNome().equals(nome)) {
                return proj;
            }
        }
        
        return null;
    }
    
      
    
    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Float getHoras() {
        return horas;
    }

    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHoras(Float horas) {
        this.horas = horas;
    }

    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }
    
    
    
}
