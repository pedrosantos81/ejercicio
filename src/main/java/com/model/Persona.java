package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name="persona")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty
	@Column(name="nombre",nullable=false)
	@Size(min = 4)
	private String nombre;
	
	@NotEmpty
	@Column(name="genero",nullable=false)
	private String genero;
	
	@Column(name="edad",nullable=false)
	private int edad;
	
	@NotEmpty(message="no puede estar vacio")
	@Column(name="identificacion",nullable=false)
	private String identificacion;
	
	@NotEmpty
	@Column(name="direccion",nullable=false)
	private String direccion;
	
	@NotEmpty
	@Column(name="telefono",nullable=false)
	private String telefono;
	
	@OneToOne(mappedBy="persona",cascade = CascadeType.ALL)
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Persona() {
		
	}
	
	public Persona(String nombre, String genero, int edad, String identificacion, String direccion, String telefono) {
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", genero=" + genero + ", edad=" + edad
				+ ", identificacion=" + identificacion + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
	

}
