package br.senac.pi.cadastro30.Domain;

/**
 * Created by Aluno on 23/11/2015.
 */
public class Cachorro {
    private long id;
    private String Nome;
    private String Raça;

public Cachorro () {}

    public Cachorro(long id,String Nome,String Raça ){

        this.id = id;
        this.Nome = Nome;
        this.Raça = Raça;
    }

    public  String getNome(){ return Nome;}
    public  void setNome (String nome){this.Nome = nome;}
    public  String getRaça(){return  Raça;}
    public  void  setRaça(String raça  ){this.Raça = raça;}
    public long getId(){return id;}
    public void setId(long id){this.id = id;}


}
