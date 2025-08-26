# EJERCICIO 2 

Es importante poder hacer la correcta instalación de MySQL, en este caso se recomienda tener ciertas apps instaladas que antes no se necesitaban:
* MySQL Workbench
* Docker

Se debe crear un archivo yml, en mi caso lo dejé en mi carpeta User de C

mysql.yml
version: "3"
services:
    db:
        image: mysql
        command: --default-authentication-plugin=mysql_native_password
        restart: always
        environment:
            - "MYSQL_ROOT_PASSWORD=password"
        ports:
            - "3306:3306"
        volumes:
            - "/Users/adiazpace/Documents/mysql:/var/lib/mysql"

**Importante la identación de este documento**

Desde cmd correr 

### docker-compose -f mysql.yml up

Debería crearnos un container para nuestra base de datos