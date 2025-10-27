# GymNess

## Descripcion
 - Profe te dejo los endpoints con los q podes usar el programa.

### USUARIO
 - http://localhost:8080/api/auth/login

### CLIENTE 
 - http://localhost:8080/api/clientes

 - {
  "nombre": "Juan",
  "apellido": "Pérez",
  "documento": "12345678",
  "direccion": "Calle Falsa",
  "nroDireccion": 123,
  "obraSocial": "OSDE",
  "fechaNacimiento": "1995-05-20",
  "planId": 1
   }

 - Tiene q estar un plan previamente creado un plan 

### TIPO EJERCICIO
 - http://localhost:8080/api/tipos-ejercicio

 - {
  "nombre": "Cardio"
   } 

### SESION ENTRENAMIENTO
 - http://localhost:8080/api/sesiones

 - {
  "nombre": "Nueva sesión de fuerza",
  "descripcion": "Sesión enfocada en fuerza de piernas",
  "rutinaId": 1,
  "estadoId": 1,
  "ejercicioIds": [] 
   }

 - Tiene q haber creado previamente un estado.
 - Tiene q haber creado previamente una rutina.

### RUTINA
 - http://localhost:8080/api/rutinas

 - {
  "nombre": "Rutina de movilidad",
  "descripcion": "Ejercicios de movilidad articular",
  "objetivoId": 1,
  "sesionEntrenamientoIds": []
   }

 - Tiene q haber previamente creado un odjetivo rutina.

### PROFESIONAL
 - http://localhost:8080/api/profesionales

 - {
  "id": 1,
  "nombre": "Juan Carlos",
  "apellido": "Pérez",
  "documento": 12345678,
  "email": "juan.c.perez@example.com",
  "telefono": "1134567890",
  "matriculaProfesional": "MAT-12345",
  "activo": true,
  "especialidadId": 1,
  "planIds": [1]
   }

 - Puede tener o no un plan/es asociados.
 - Tiene q tener creado previamente una especialidad.

### PLAN
 - http://localhost:8080/api/planes

 - {
    "nombre": "Plan Elite",
    "descripcion": "Acceso total + entrenador personal",
    "precio": 5000
   }

### ODJETIVO RUTINA
 - http://localhost:8080/api/odjetivo-rutina

 - {
    "nombre": "Pérdida de peso"
   }


### MUSCULO ODJETIVO 
 - http://localhost:8080/api/musculos-objetivo

 - {
  "nombre": "Tríceps"
   } 

### ESTADO
 - http://localhost:8080/api/estados

 - {
  "nombre": "Activo"
   }

### ESPECIALIDAD
 - http://localhost:8080/api/especialidad

 - {
    "nombre": "Nutri"
   }

### EJERCICIO SESION
 - http://localhost:8080/api/ejercicio-sesion

 - {
  "sesionId": 1,
  "ejercicioId": 3,
  "repeticiones": 15,
  "series": 4,
  "descanso": 60
   }

### EJERCICIO
 - http://localhost:8080/api/ejercicios

 - {
  "nombre": "Sentadilla frontal",
  "descripcion": "Ejercicio de piernas",
  "instrucciones": "Mantener la espalda recta...",
  "videoUrl": "https://youtube.com/...",
  "imagenUrl": "https://imagenes.com/...",
  "activo": true,
   "musculosObjetivoIds": [1],   
  "tipoEjercicioIds": [1] 
   }

 - Tiene q haber creado previamente un tipo de ejercicio

 - Tiene q haber creado previamente un musculo odjetivo


