package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name="cliente")
@PrimaryKeyJoinColumn(name = "IdPersona",referencedColumnName = "id")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "idcliente")
//@JsonIdentityReference(alwaysAsId = true)
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Cliente extends Persona implements Serializable{
	
	@Column(name="IdCliente")
	private int idcliente;
	
	@Column(name="contrasena",nullable=false)
	@NotEmpty(message="no puede estar vacio")
	private String pass;
	
	@Column(name="estado",columnDefinition = "boolean default true")
	private boolean estado;
	
	@Column(name="IdPersona",insertable = false,updatable = false)
	private int idpersona;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="IdPersona",insertable = false,updatable = false)
	@JsonIgnore
	Persona persona;
	
	@OneToMany(mappedBy="clientes",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
	//@JsonManagedReference
	//@Fetch(FetchMode.SELECT)
	private List<Cuenta> cuentas= new ArrayList<Cuenta>();
	

	public Cliente() {
	}

//	public Cliente(String nombre,String genero,int edad,TipoIdentificacion identificacion,
//			String direccion,String telefono, String pass, boolean estado) {
//		
//		super(nombre,genero,edad,identificacion,direccion,telefono);
//		this.pass = pass;
//		this.estado = estado;
//	}
	
//	public Cliente(int idpersona,String nombre,String telefono,String pass,String direccion, boolean estado) {
//		super(nombre,"",0,TipoIdentificacion,direccion,telefono);
//		this.pass = pass;
//		this.estado = estado;
//		//this.idpersona=idpersona;
//	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

//	@Override
//	public String toString() {
//		return "Cliente [idcliente=" + idcliente + ", pass=" + pass + 
//				", estado=" + estado + ", persona=" + persona.getNombre() +
//				", direccion=" + persona.getDireccion() +
//				", identificacion=" + persona.getIdentificacion() +
//				", id: "+persona.getId()
//				+ "]";
//	}

	public void addCuenta(Cuenta cuenta) {
		if(cuentas==null) {
			cuentas = new ArrayList<>();
			}
		cuenta.setCliente(this);
        cuentas.add(cuenta);
        
    }
 
    public void removeComment(Cuenta cuenta) {
        cuentas.remove(cuenta);
        
    }

}
