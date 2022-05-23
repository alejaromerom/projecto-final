package Modelo;

public class Estudiante extends Persona {
	private Notas notas;
	private String materia;

	public Estudiante(String nombre, String apellido, int codigo, Notas notas) {
		super(nombre, apellido, codigo);
		this.notas = notas;
	}

	public Estudiante() {
		super(null, null, 0);
	}
	
	public Notas getNotas() {
		return notas;
	}

	public void setNotas(Notas notas) {
		this.notas = notas;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "Estudiante [notas=" + notas + ", materia=" + materia + ", nombre=" + getNombre() + ", apellido=" + getApellido()
				+ ", codigo=" + getCodigo() + "]";
	}
}
