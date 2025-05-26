package unoeste.fipp.ativooperante_be.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;
    @Column(name = "usu_cpf")
    private Long cpf;
    @Column(name = "usu_email")
    private String email;
    @Column(name = "usu_senha")
    private int senha;
    @Column(name = "usu_nivel")
    private int nivel;

    public Usuario(Long id, Long cpf) {
        this.id = id;
        this.cpf = cpf;
    }



    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
