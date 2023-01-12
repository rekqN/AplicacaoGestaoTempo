package backend;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class Sistema implements Serializable {

    public ArrayList<Utilizador> utilizadores;

    public Utilizador corrente;

    public Sistema() {
        this.utilizadores = new ArrayList();
        this.lerFicheiro();
    }

    public Boolean registarUtilizador(String nome, String username, String password, Float horas) {

        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getUsername().equals(username)) {
                return false;
            }
        }
        
        password = hashPassword(password);

        this.utilizadores.add(new Utilizador(nome, username, password, horas));
        this.escreverFicheiro();
        return true;
    }

    public Boolean loginUtilizador(String username, String password) {
        
        password = hashPassword(password);

        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getUsername().equals(username) && utilizador.getPassword().equals(password)) {
                this.corrente = utilizador;
                return true;
            }
        }
        return false;
    }
    
    private static String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            password = bytesParaHex(hash);
        } catch(Exception e){
            e.printStackTrace();
        } 
        return password;
    }
    
    private static String bytesParaHex(byte[] bytes){
        StringBuilder hashedString = new StringBuilder();
        for(byte b : bytes){
            hashedString.append(Integer.toString((b & 0xff) + 0x100,16).substring(1));
        }
        return hashedString.toString();
    }
    
    public Boolean loginAdministrador(String username, String password) {
        if(username.equals("SUPERADMIN") && password.equals("ADMIN123SUPER")){
            return true;
        }
        return false;
    }

    public void Sair() {

        for (Utilizador u : this.utilizadores) {

            if (u.getNome().equals(corrente.getNome())) {

                this.utilizadores.add(corrente);
                this.utilizadores.remove(u);
                this.corrente = null;
            }

        }

        this.escreverFicheiro();

    }

    public void gerarRelatorio() {

        String relatorio = "RELATORIO DE TAREFAS E PROJETOS\n";

        relatorio += corrente.getNome() + "\n\n";

        relatorio += "------+--------+--------+------ \n";

        for (Projeto p : this.corrente.getProjetos()) {

            relatorio += "---- " + p.getNome() + "\n";

            for (Tarefa t : p.getTarefas()) {

                if (t.getEstado().equals("Finalizado")) {

                    Long tempo_gasto = t.getFim().getTime() / 3600000 - t.getInicio().getTime() / 3600000;
                    Float ganhos = tempo_gasto * t.getPreco_h();

                    relatorio += "\t+" + t.getNome() + " | " + Long.toString(tempo_gasto) + " horas | " + Float.toString(ganhos) + "$\n";
                }

            }

        }

        System.out.println(relatorio);

        try {
            File relatorio_ficheiro = new File("./relatorios/" + this.corrente.getNome() + ".txt");
            FileWriter myWriter = new FileWriter("./relatorios/" + this.corrente.getNome() + ".txt");
            myWriter.write(relatorio);
            myWriter.close();
        } catch (Exception e) {

        }

        try {
            File directory = new File("./relatorios/");
            Desktop.getDesktop().open(directory);
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void lerFicheiro() {

        try {
            FileInputStream fileIn = new FileInputStream("data.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            this.utilizadores = (ArrayList) objectIn.readObject();

            System.out.println("Os utilizadores foram carregados com sucesso !!");

        } catch (Exception ex) {

            System.out.println("ERRO: a ler os utilizadore");
        }
    }

    public void escreverFicheiro() {
        try {

            FileOutputStream fileOut = new FileOutputStream("data.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.utilizadores);
            objectOut.close();
            System.out.println("Os utilizadores foram guardados com sucesso !!");
        } catch (Exception ex) {

            System.out.println("ERRO: a guardar os utilizadores");
        }

    }

}
