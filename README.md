Pasos a seguir para levantar el proyecto:

- Version de Java 22 o superior.
- Maven 3 o superior.
- Plugin de lombok configurado en su IDE.
- MySQL como Base de Datos.
- Crear una Base de Datos con la siguiente instruccion create schema grupo24_oo2;
- Abrir el proyecto y revisar que se descarguen las dependencias, en caso de que no abrir una terminal en la raiz del proyecto y ejecutar esta instruccion: mvn clean install
- Configurar las variables de entorno para que el archivo application.properties las reconozca antes de iniciar la aplicacion:
    DB_URL -> colocar la url de la base de datos.
    USERNAME -> colocar tu usuario de la base de datos.
    PASSWORD -> colocar tu password de la base de datos.
    EMAIL_USER -> oo2.grupo.024@gmail.com (Solo es utilizado por la aplicación para enviar mails).
    EMAIL_PASSWORD -> bwlb pmvk bnum nbmu
- Ejecutar el proyecto para crear la base de datos.
- Ejecutar el sql script 'hardcodear servicios'. Les va a permitir crear tickets sin haber creado servicos anteriormente.
- Abrir el navegador e ir a la siguiente url: http://localhost:8080
