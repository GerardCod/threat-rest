# Guia rápida de usuario para ejecutar el contenedor de docker

Primero asegúrate de tener instalado Docker en tu equipo. En caso de que no
se así acude a este link para seguir las instrucciones 
[guia de instalación en windows](link-url "https://docs.docker.com/desktop/windows/install/").

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

Una vez allí copia y pega el siguiente comando
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