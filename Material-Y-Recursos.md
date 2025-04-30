# 🏍️NAKEDMOTORSES🏍️

## Material y Recursos para poder arrancar la aplicación

Aquí se dispondrán los recursos necesarios para poder arrancar la aplicación de manera local.

### Docker

- Windonws 

Para Windows necesitamos el software de DockerDesktop (la versión es indiferente)

- Linux
  
  Para Linux necesitamos tener instalado docker 
  ```cmd
  sudo apt-get install docker-ce**
  ```


Una vez hecho esto arrancamos nuestro archivo.yml con: 

```cmd
docker-compose docker-compose.yml up --build
```

### Frontend

Para poder arrancar el frontend vamos a necesitar Angular v16.20 
Para conseguir este debemos descargarnos la versión de node.js que nos pida y ya ejecutar el comando
dentro de la carpeta "frontend":

```cmd 
ng serve
```

En el caso de dar error probablemente es por no tener el node_modules de node:

```cmd 
npm install
```

### Backend

Para poder arrancar el Backend vamos a necesitar una versión de java instalada en nuestro equipo... En mi caso java21

Ademas del extensión pack de Spring para tener accesible todo