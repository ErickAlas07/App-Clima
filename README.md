##Api de Datos Meteorologicos.

API construida con tecnología Spring Boot, la cual obtiene y brinda los datos meteorologicos
(de una ciudad proporcionada) desde OpenWeatherMap.

#Requisistos necesarios 

Primeramente, asegúrate que dentro de tu sistemade se encuentren instalados los siguientes requisitos:

- Un entorno de desarrollo integrado (IDE) como Visual Studio Code, Apache Netbeans, Eclipse o IntelliJ IDEA para abrir y tranajar con el proyecto Spring Boot.
- JDK 17: Java versión 17 para poder compilar y configurar el proyecto.
- API KEY de OpenWeatherMap, para acceder a los datos meteorologicos.
- Docker Desktop para lograr contenerizar y facilitar la implementación de la aplicación.
- Instalar Workbench MYSQL, la cual es una aplicación de software que proporciona un entorno visual para trabajar con bases de datos MySQL.
- Instalar Postman, para realizar las peticiones de la API. 
   
  
##Configuración del Entorno

Seguir los siguientes pasos para la ejecución de la aplicacion:

1. Clonar este repositorio en tu maquina local: 
```
git clone https://github.com/ErickAlas07/App-Clima.git
```

2. Ejecutar los siguientes comandos: 

2.1. Ejecutar comandos para limpieza e instalación de paquetes 
```
./mvnw clean package -DskipTests
./mvnw clean install package
```

2.2. Comando para construir los servicios definidos en archivo docker-compose.yml. Nota: Puedes utilizar cualquiera de los dos comandos
```
docker-compose build java-app
docker-compose build
```

2.3. Comando para iniciar los servicios construidos en archivo docker-compose.yml. Nota: Puedes utilizar cualquiera de los dos comandos
```
docker-compose up
docker-compose up 
```

> [!IMPORTANT]
> Asegúrate de estar en el directorio raíz del proyecto cuando ejecutes los anteriores comandos. Esto te garantizará facilidad a la hora de ejecutar la construcción y ejecución de la aplicación junto sus servicios. 


#Endpoints disponibles en la aplicación Spring.

- Registrar un usuario. Método: (POST)
  - Ruta: `/auth/registro`
  - Cuerpo (Body): 

  ```JSON
  {
    "username":"nombre-de-usuario",
    "password":"contraseña"
  }
  ```

- Login. Método: (POST)
  - Ruta: `/auth/login`
  - Cuerpo (Body):

  ```JSON
  {
    "username":"nombre-de-usuario",
    "password":"contraseña"
  }
  ```

- #Obtener usuario actual. Método: (GET)
  - Ruta: `/usuarios/me`

- #Obtener el clima actual dado un nombre de ciudad. Método: (GET)
  - Ruta: `/clima/actual/`

- #Obtener el pronostico del clima de próximos 5 dias, dando un nombre de ciudad. Método: (GET)
  - Ruta: `/clima/forecast/`
 
- #Acceder a datos de contaminacion del aire, dando un nombre de ciudad. Método: (GET)
  - Ruta: `/clima/aire/`

[!IMPORTANT]
> Los endpoints relacionados al clima, necesitan como parámetros el nombre de ciudad y una API KEY válida de OpenWeatherMap.
> Debes "loguearte" con un usuario válido, es decir, autenticado para lograr peticiones exitosas.
> Puedes hacer uso de Postman para realizar las peticiones.


> [!IMPORTANT]
> La aplicación está configurada para realizar 100 consultas por hora.

##Documentacion

Destacar que el programa viene con documentacion generada automáticamente por Swagger.
- Sigue los siguientes pasos para tener el acceso:

1. Abre tu navegador de preferencia.
2. Ir a la ruta `/v3/api-docs`

- Para acceder a los endpoints detallados anteriormente y realizar pruebas:

1. Abre tu navegador de preferencia.
2. Ir a la ruta `/swagger-ui/index.html`


#Ejemplos de peticiones junto a sus respuestas.

1. Solicitar el clima actual de una ciudad ciudad:

  - Solicitud:
      `http://localhost:8080/clima/actual/?ciudad={nombre-de-ciudad}?apiKey={API-key}`

  - Respuesta:

  ```JSON
  {
    "name": "London",
    "main": {
        "temp": 11.15,
        "humidity": 86
    },
    "wind": {
        "speed": 6.69
    },
    "weather": [
        {
            "description": "broken clouds"
        }
    ]
  }
  ```

2. Solicitar un pronostico del tiempo de 5 dias de una ciudad especificada:

  - Solicitud:
      `http://localhost:8080/clima/forecast/?ciudad={nombre-de-ciudad}?apiKey={API-key}`

  - Respuesta:

  ```JSON
  {
    "cod": "200",
    "message": 0,
    "cnt": 40,
    "list": [
        {
            "dt": 1700438400,
            "main": {
                "temp": 10.91,
                "feelsLike": 0.0,
                "pressure": 1008,
                "humidity": 85,
                "temp_min": 10.91,
                "temp_max": 11.75,
                "sea_level": 1008,
                "grnd_level": 1007,
                "temp_kf": -0.84
            },
            "weather": [
                {
                    "id": 803,
                    "main": "Clouds",
                    "description": "broken clouds"
                }
            ],
            "clouds": {
                "all": 75
            },
            "wind": {
                "speed": 6.12,
                "deg": 256,
                "gust": 12.0
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-20 00:00:00"
        },
        {
            "dt": 1700524800,
            "main": {
                "temp": 10.56,
                "feelsLike": 0.0,
                "pressure": 1014,
                "humidity": 91,
                "temp_min": 10.56,
                "temp_max": 10.56,
                "sea_level": 1014,
                "grnd_level": 1011,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 500,
                    "main": "Rain",
                    "description": "light rain"
                }
            ],
            "clouds": {
                "all": 100
            },
            "wind": {
                "speed": 3.84,
                "deg": 337,
                "gust": 9.52
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-21 00:00:00"
        },
        {
            "dt": 1700611200,
            "main": {
                "temp": 7.72,
                "feelsLike": 0.0,
                "pressure": 1030,
                "humidity": 87,
                "temp_min": 7.72,
                "temp_max": 7.72,
                "sea_level": 1030,
                "grnd_level": 1027,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 804,
                    "main": "Clouds",
                    "description": "overcast clouds"
                }
            ],
            "clouds": {
                "all": 96
            },
            "wind": {
                "speed": 2.01,
                "deg": 4,
                "gust": 5.2
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-22 00:00:00"
        },
        {
            "dt": 1700697600,
            "main": {
                "temp": 8.2,
                "feelsLike": 0.0,
                "pressure": 1026,
                "humidity": 96,
                "temp_min": 8.2,
                "temp_max": 8.2,
                "sea_level": 1026,
                "grnd_level": 1023,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 803,
                    "main": "Clouds",
                    "description": "broken clouds"
                }
            ],
            "clouds": {
                "all": 53
            },
            "wind": {
                "speed": 2.65,
                "deg": 259,
                "gust": 9.52
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-23 00:00:00"
        },
        {
            "dt": 1700784000,
            "main": {
                "temp": 10.55,
                "feelsLike": 0.0,
                "pressure": 1017,
                "humidity": 83,
                "temp_min": 10.55,
                "temp_max": 10.55,
                "sea_level": 1017,
                "grnd_level": 1014,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 804,
                    "main": "Clouds",
                    "description": "overcast clouds"
                }
            ],
            "clouds": {
                "all": 100
            },
            "wind": {
                "speed": 5.1,
                "deg": 268,
                "gust": 12.0
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-24 00:00:00"
        }
    ]
  }
  ```

3. Solicitar datos de contaminacion del aire de una ciudad:

   - Ruta:
      `http://localhost:8080/clima/aire/?ciudad={nombre-de-ciudad}?apiKey={API-key}`

   - Respuesta:

  ```JSON
    {
    "list": [
        {
            "main": {
                "aqi": 2
            },
            "components": {
                "co": 226.97,
                "no": 0.0,
                "no2": 6.77,
                "o3": 63.66,
                "so2": 2.83,
                "pm2_5": 1.37,
                "pm10": 3.21,
                "nh3": 0.26
            }
        }
      ]
    }
  ```


##Contacto 
- Correo electrónico: erick777gal@gmail.com
- Github: ErickAlas07

##¡Mucha suerte!

  







