package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Modelo.Estudiante;
import Modelo.Notas;
import Vista.Consola;

public class Controlador implements Serializable {
    private Consola consola;
    private Scanner entrada;
    File f = new File("notas.txt");

    public Controlador() {
        consola = new Consola();
    }

    public void menuPrincipal() {
        boolean salir = false;
        int opcion;
        while (!salir) {
            try {
                consola.printMsg("Sistema de notas de estudiante");
                consola.printMsg("1. Lea el archivo con las notas");
                consola.printMsg("2. Ingrese al modulo de notas");
                consola.printMsg("3. Salir");
                opcion = consola.readInt("Ingrese una opcion: ");

                switch (opcion) {
                    case 1:
                        leerRegistros();
                        break;
                    case 2:
                        agregarRegistros();
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        consola.printMsg("Solo puedes escoger entre las opciones 1, 2 o 3");
                }

            } catch (InputMismatchException e) {
                consola.printMsg("Valor invalido!");
                e.printStackTrace();
            }
        }
    }

    public void crearArchivo(List<Estudiante> lista) {
        try {
            FileWriter fw = new FileWriter(f);
            for (Estudiante estudiante : lista) {
                fw.write(estudiante.getCodigo() + " " + estudiante.getNombre() + " " + estudiante.getApellido() + " " + estudiante.getMateria() + " " + estudiante.getNotas().getNota1() + " " + estudiante.getNotas().getNota2() + " " + estudiante.getNotas().getNota3() + " " + estudiante.getNotas().getNota4() + " " +  estudiante.getNotas().getNota5() + " " + estudiante.getNotas().getNotadef() + "\n");
            }
            fw.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void leerRegistros() {
        try {
            Scanner sc = new Scanner(f);
            consola.printMsg("Fecha y hora:" + LocalDateTime.now());
            consola.printMsg("Codigo Materia Nombre Apellido Nota 1 Nota 2 Nota 3 Nota 4 Nota 5 Nota Definitiva");
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                consola.printMsg(linea);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        
        }
    }

    public void agregarRegistros() {
        boolean continuar = true;
        consola.printMsg("Ingrese los datos de los estudiantes a ingresar por orden\n");
        List<Estudiante> estudiantes = new ArrayList<>();
        double n1;
        double n2;
        double n3;
        double n4;
        double n5;

        while (continuar) {
            try {
                Estudiante registro = new Estudiante();
                Notas notas = new Notas();
                registro.setCodigo(consola.readInt("Ingrese el código del estudiante: "));
                registro.setMateria(consola.readString("Ingrese la materia: "));
                registro.setNombre(consola.readString("Ingrese el mombre del estudiante: "));
                registro.setApellido(consola.readString("Ingrese el apellido del estudiante: "));

                n1 = consola.readDouble("Ingrese la nota del primer corte: ");
                while (n1 < 0 || n1 > 5) {
                    n1 = consola.readDouble("Ingrese la nota del primer corte: ");
                }
                notas.setNota1(n1);

                n2 = consola.readDouble("Ingrese la nota del segundo corte: ");
                while (n2 < 0 || n2 > 5) {
                n2 = consola.readDouble("Ingrese la nota del segundo corte: ");
                }
                notas.setNota2(n2);

                n3 = consola.readDouble("Ingrese la nota del tercer corte: ");
                while (n3 < 0 || n3 > 5) {
                n3 = consola.readDouble("Ingrese la nota del tercer corte: ");
                }
                notas.setNota3(n3);

                n4 = consola.readDouble("Ingrese la nota del cuarto corte: ");
                while (n4 < 0 || n4 > 5) {
                n4 = consola.readDouble("Ingrese la nota del cuarto corte: ");
                }
                notas.setNota4(n4);

                n5 = consola.readDouble("Ingrese la nota del quinto corte: ");
                while (n5 < 0 || n5 > 5) {
                n5 = consola.readDouble("Ingrese la nota del quinto corte: ");
                }
                notas.setNota5(n5);
                
                notas.setNotadef(Math.round(calcNotadef(notas) * 100d) / 100d);
                registro.setNotas(notas);
                estudiantes.add(registro);

                String continuarS = consola.readString("¿Quiere añadir otra nota? s/n");
                if (continuarS.equals("n")) {
                    System.out.println(estudiantes);
                    crearArchivo(estudiantes);
                    continuar = false;
                } else {
                    while (!continuarS.equals("n") && !continuarS.equals("s")) {
                        consola.readString("Has colacado una opcion invalida. Ingresa s/n: ");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            consola.printMsg("Hecho!\n");
        }
    }

    public double calcNotadef(Notas notas) {
        return (notas.getNota1() * 0.2) + (notas.getNota2()* 0.2) + (notas.getNota3()* 0.2)+ (notas.getNota4()* 0.2)+ (notas.getNota5()* 0.2);
    }

    public void cerrarArchivo() {
        if (entrada != null) {
            entrada.close();
        }
    }
}