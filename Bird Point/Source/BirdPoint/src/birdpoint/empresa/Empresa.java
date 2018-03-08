/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Karlos
 */
@Entity
public class Empresa {
    
    @Id
    @GeneratedValue
    private int idEmpresa;

    @Column(length = 100, nullable = false)
    private String nomeEmpresa;
    
    @Column(length = 100, nullable = false)
    private String nomeFantasiaEmpresa;
    
    @Column(length = 100, nullable = false)
    private String cnpjEmpresa;

    @Column(length = 100)
    private String ruaEmpresa;

    @Column(length = 100)
    private String bairroEnderecoEmpresa;

    @Column(length = 100)
    private String telefoneEmpresa;

    @Column(length = 100)
    private String emailEmpresa;

    @Column(length = 100)
    private int numeroEnderecoEmpresa;

    @Column(length = 100)
    private String cidadeEnderecoEmpresa;
    
    @Column
    @Lob
    private byte[] fotoEmpresa;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeFantasiaEmpresa() {
        return nomeFantasiaEmpresa;
    }

    public void setNomeFantasiaEmpresa(String nomeFantasiaEmpresa) {
        this.nomeFantasiaEmpresa = nomeFantasiaEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getRuaEmpresa() {
        return ruaEmpresa;
    }

    public void setRuaEmpresa(String ruaEmpresa) {
        this.ruaEmpresa = ruaEmpresa;
    }

    public String getBairroEnderecoEmpresa() {
        return bairroEnderecoEmpresa;
    }

    public void setBairroEnderecoEmpresa(String bairroEnderecoEmpresa) {
        this.bairroEnderecoEmpresa = bairroEnderecoEmpresa;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public int getNumeroEnderecoEmpresa() {
        return numeroEnderecoEmpresa;
    }

    public void setNumeroEnderecoEmpresa(int numeroEnderecoEmpresa) {
        this.numeroEnderecoEmpresa = numeroEnderecoEmpresa;
    }

    public String getCidadeEnderecoEmpresa() {
        return cidadeEnderecoEmpresa;
    }

    public void setCidadeEnderecoEmpresa(String cidadeEnderecoEmpresa) {
        this.cidadeEnderecoEmpresa = cidadeEnderecoEmpresa;
    }

    public byte[] getFotoEmpresa() {
        return fotoEmpresa;
    }

    public void setFotoEmpresa(byte[] fotoEmpresa) {
        this.fotoEmpresa = fotoEmpresa;
    }
    
}
