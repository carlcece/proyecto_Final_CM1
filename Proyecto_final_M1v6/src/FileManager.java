import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * Clase para manejar la selección, lectura y escritura de archivos de texto.
 */
public class FileManager {

    /**
     * Permite al usuario seleccionar un archivo para abrir.
     */
    public File seleccionarArchivo(String titulo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(titulo);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        int resultado = fileChooser.showOpenDialog(null);
        return (resultado == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile() : null;
    }

    /**
     * Permite al usuario seleccionar una ruta para guardar un archivo.
     */
    public File seleccionarGuardar(String nombreSugerido) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("💾 Especifica dónde deseas guardar el archivo");
        fileChooser.setSelectedFile(new File(nombreSugerido));

        while (true) {
            int resultado = fileChooser.showSaveDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                if (esNombreValido(archivoSeleccionado.getName())) {
                    return archivoSeleccionado;
                } else {
                    System.out.println("⚠️ Advertencia: Solo se permite archivos con la extensión \".txt\".");
                    System.out.println("➡ Asegúrate de escribir correctamente la extensión: por ejemplo, \"archivo.txt\".");
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Lee el contenido completo de un archivo de texto.
     */
    public String leerArchivo(File archivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
        return contenido.toString();
    }

    /**
     * Guarda contenido de texto en el archivo especificado.
     */
    public void guardarArchivo(File archivo, String contenido) {
        if (!esNombreValido(archivo.getName())) {
            System.out.println("❌ Nombre de archivo no permitido, Intenta con otro nombre que termine en .txt y no comience con punto.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(contenido);
           // System.out.println("✅ Archivo guardado correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar archivo: " + e.getMessage());
        }
    }

    /**
     * Valida que el nombre del archivo sea aceptable.
     */
    private boolean esNombreValido(String nombre) {
        return !nombre.startsWith(".") &&
                !nombre.contains("..") &&
                nombre.toLowerCase().endsWith(".txt") &&
                nombre.trim().length() >= 5;
    }
}