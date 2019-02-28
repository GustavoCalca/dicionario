package modelo;

import java.util.*;

public class Dicionario {
    
    private Vertice raiz;
    private Scanner scanner = new Scanner(System.in);
    private int contador;

    public Dicionario(){
        raiz = new Vertice(null);
        //teste();
        menu();
    }

    public Vertice adicionarVertice(String conteudo){
        Vertice vertice = new Vertice(conteudo);
        vertice.setFilhos(null);
        return vertice;
    }
    
    //Método de teste com valores pré-definidos
    public void teste() {
        Vertice v1 = new Vertice("Primeira letra do alfabeto");
        Vertice v2 = new Vertice("Segunda letra do alfabeto");
        Vertice v1_1 = new Vertice(null);
        Vertice v1_1_1 = new Vertice("Aba");
        Vertice v1_1_2 = new Vertice("Nemesis do Samurai Jack");
        raiz.adicionarFilho(v1, "a");
        raiz.adicionarFilho(v2, "b");
        v1.adicionarFilho(v1_1, "b");
        v1_1.adicionarFilho(v1_1_1, "a");
        v1_1.adicionarFilho(v1_1_2, "u");
        System.out.println(raiz.getFilhos().get("a").getConteudo());
        System.out.println(raiz.getFilhos().get("b").getConteudo());
        System.out.println(v1_1.getFilhos().get("a").getConteudo());
        System.out.println(v1_1.getFilhos().get("u").getConteudo());
    }
        
    public void menu(){
        System.out.println("\n--------------------\nDigite uma palavra(Apenas letras minúsculas)");
        String palavra = scanner.next();
        scanner.nextLine();
        if(!buscarPalavra(palavra)){
            System.out.println("Palavra não consta no dicionário\nDeseja adicioná-la?[S-SIM][N-NÃO]");
            if(scanner.next().equals("S")){
                scanner.nextLine();
                System.out.println("Digite a definição:");
                String definicao = scanner.nextLine();
                scanner.nextLine();
                adicionarPalavra(palavra, definicao);
            }
        }
        else{
            System.out.println("Deseja alterar sua definição?[S-SIM][N-NÃO]");
            if(scanner.next().equals("S")){
                scanner.nextLine();
                System.out.println("Insira a nova definição");
                String novaDefinicao = scanner.nextLine();
                scanner.nextLine();
                alterarPalavra(palavra, novaDefinicao);
            }
        }
        System.out.println("Deseja continuar?[S-SIM][N-NÃO]");
        if(scanner.next().equals("S"))
            menu();
    }
    
    public boolean buscarPalavra(String palavra){
        String[] palavraSeparada = palavra.split("(?!^)");
        contador = 0;
        if(this.raiz.temFilho()){
            Vertice raiz1 = this.raiz;//Variavel de suporte
            for(int i = 0; i < palavraSeparada.length; i++){//Looping de busca
                if(raiz1.getFilhos().containsKey(palavraSeparada[i]))//Possui a letra
                    if(i != palavraSeparada.length - 1){//Não é a última letra
                        if(raiz1.temFilho())
                           raiz1 = raiz1.getFilhos().get(palavraSeparada[i]);//"auto-incremento" do pai
                        else{
                            contador = i - 1;
                            return false;
                        }
                    }
                    else{
                        contador = i - 1;
                        System.out.println("\nDefinição:\n" + raiz1.getFilhos().get(palavraSeparada[i]).getConteudo());
                        return true;
                    }
                else{
                    contador = i-1;
                    break;
                }
            }
        }
        return false;
    }
    
    public void adicionarPalavra(String palavra, String definicao){
        String[] palavraSeparada = palavra.split("(?!^)");
        Vertice raiz1 = this.raiz;//Variavel de suporte
        for(int j = 0; j <= contador; j++){
            raiz1 = raiz1.getFilhos().get(palavraSeparada[j]);//"auto-incremento" do pai
        }
        for(int i = contador + 1; i < palavraSeparada.length; i++){
            if(i != palavraSeparada.length - 1){//Não é a última letra
                Vertice v = new Vertice(null);//Cria um novo vértice a cada nova letra
                raiz1.adicionarFilho(v, palavraSeparada[i]);//Associa vértice como filho do pai atual
                raiz1 = raiz1.getFilhos().get(palavraSeparada[i]);//"auto-incremento" do pai
            }
            else{
                Vertice v = new Vertice(null);
                v.setConteudo(definicao);
                raiz1.adicionarFilho(v, palavraSeparada[i]);
                System.out.println("Palavra adicionada com sucesso");
            }
        }
    }
    
    //BÔNUS
    public void alterarPalavra(String palavra, String novaDefinicao){
        String[] palavraSeparada = palavra.split("(?!^)");
        Vertice raiz1 = this.raiz;//Variavel de suporte
        for(int i = 0; i < palavraSeparada.length; i++)
            raiz1 = raiz1.getFilhos().get(palavraSeparada[i]);//"auto-incremento" do pai
        raiz1.setConteudo(novaDefinicao);
        System.out.println("Definição alterada");
    }
}
