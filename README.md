# Guia rápida de usuario para ejecutar el contenedor de docker

## Requerimientos base

- Tener instalado Docker. En caso de que no
sea así acude a este link para seguir las instrucciones 
[guia de instalación en windows](link-url "https://docs.docker.com/desktop/windows/install/").

- Tener instalado Maven. En caso de que no sea así puedes ir al siguiente enlace para descargar Maven [descargar maven](link-url "https://maven.apache.org/download.cgi"). Como recomendación para windows descarga el zip binario, descomprimelo en una carpeta de tu preferencia y por último agrega la ruta de la subcarpeta bin a la variable de entorno PATH en tu sistema.

## Primeros pasos

Para asegurarte de que tienes Docker instalado en tu computadora,
usa el siguiente comando en tu terminal.

```bash
docker --version
```

Si aparece la versión de Docker no hay problema puedes continuar con la guía.

### Construyendo la imagen
Una vez que te hayas asegurado de tener Docker instalado, utiliza el comando **cd**
para dirigirte a la carpeta de este documento.

Una vez allí copia y pega el siguiente comando.

```bash
mvn clean package
```

Este comando lo que hará será empaquetar el proyecto para su lanzamiento en un contenedor.
Después de ejecutar el comando previo, copia y pega el siguiente comando.

```bash
docker build -t threatrest:v1 .
```

Posteriormente si no tienes una red dedicada para los contenedores puedes copiar y pegar
el siguiente comando.
```bash
docker network create threat-network
```

### Ejecutando el contenedor

**Importante:** 
Para poder ejecutar correctamente este contenedor es necesario que primaro hayas creado y ejecutado
el contenedor de eureka server.

Una vez construida la imagen docker ejecuta el siguiente comando para crear un contenedor
con base en la image que acabaste de construir.

```bash
docker run -p 8081:8081 --name threatrest --network threat-network threatrest:v1
```

Y listo ahora solo espera a que se ejecute el contenedor y podrás acceder a el desde la url
http://localhost:8081