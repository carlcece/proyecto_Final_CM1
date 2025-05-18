# proyecto_Final_CM1
Proyecto final del módulo 1: Sistema de cifrado y descifrado usando el cifrado César Este proyecto implementa un sistema modular en Java para cifrar y descifrar textos con el cifrado César, utilizando un alfabeto extendido que incluye letras mayúsculas, signos de puntuación y espacio. Además, incorpora funcionalidades avanzadas para realizar ataques por fuerza bruta y análisis estadístico de frecuencias de caracteres, facilitando así la recuperación del texto original sin conocer la clave.

Para evitar errores, los archivos cifrados incluyen el tag [CIFRADO_CESAR] al inicio para identificar si ya están cifrados y evitar un nuevo cifrado accidental. De igual forma, los archivos descifrados llevan el tag [DECIFRADO_CESAR] para prevenir descifrados repetidos por parte del usuario, mejorando la robustez y usabilidad del sistema.

🔤 Alfabeto Extendido Utilizado El cifrado se basa en el siguiente conjunto de caracteres: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z . , ! ? : ; ' " ( ) Nota: El espacio en blanco se considera un carácter válido dentro del alfabeto para cifrar y descifrar.

📂 Formato de archivo de entrada y salida Para la carga y procesamiento de textos, este sistema solo acepta archivos en formato .txt.

🧪 Archivo de muestra (texto de referencia) Para ejecutar el análisis estadístico y mejorar el descifrado automático, se requiere un archivo de muestra en formato .txt. Este archivo debe contener texto representativo en el mismo idioma del texto cifrado (por ejemplo, inglés). Sirve como base para comparar la frecuencia de letras y símbolos, ayudando a estimar la clave correcta durante el ataque de fuerza bruta.

⚠️ Consideración Como se usa JFileChooser (ventanas gráficas), debe ejecutarse en un entorno que soporte interfaz gráfica (GUI).
