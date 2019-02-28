package modelo;

import java.util.*;

public class Vertice{
    
    private String conteudo;
    private Map<String, Vertice> filhos = new HashMap<>();
    
    public boolean temFilho(){
        return this.getFilhos() != null;
    }
    
    public Vertice(String conteudo){
        this.setConteudo(conteudo);
        this.filhos.clear();
    }
    
    public void adicionarFilho(Vertice filho, String aresta){
        filhos.put(aresta, filho);
        this.setFilhos(filhos);
    }
    
    //Getters & Setters
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public Map<String, Vertice> getFilhos() {
        return filhos;
    }
    public void setFilhos(Map<String, Vertice> filhos) {
        this.filhos = filhos;
    }
}