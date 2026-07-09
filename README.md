Nueva funcionalidad incorporada

Se incorporó un módulo de gestión de red de contactos, inspirado en el funcionamiento de una red profesional como LinkedIn. La mejora reemplaza el agregado directo de contactos por un sistema de solicitudes de contacto, donde un usuario envía una solicitud y el destinatario puede aceptarla o rechazarla antes de establecer la conexión.

Además, se añadió la funcionalidad de seguir usuarios, permitiendo crear relaciones unidireccionales sin necesidad de aceptación.


Esta mejora hace que el comportamiento de la plataforma sea más realista y cercano al de una red profesional.

Los principales beneficios son:

-Permite que cada usuario decida si acepta o rechaza una solicitud de contacto.
-Diferencia dos tipos de relaciones: CONTACTO (bidireccional) y SIGUE (unidireccional).
-Mejora la experiencia del usuario mediante recomendaciones de contactos basadas en conexiones existentes.

Para desarrollar esta funcionalidad se utilizaron los siguientes Tipos de Datos Abstractos:

-Grafo implementado mediante listas de adyacencia: representa la red de usuarios y las relaciones existentes entre ellos.
-Lista enlazada: utilizada para almacenar las relaciones de cada usuario (listas de adyacencia), las solicitudes de contacto pendientes y las listas de recomendaciones.
-Diccionario: almacena todos los perfiles registrados y permite acceder rápidamente a un usuario mediante su correo electrónico.



Cuando un usuario envía una solicitud de contacto, esta se guarda en la lista de solicitudes pendientes del destinatario. Si la solicitud es aceptada, el método conectar() agrega las aristas correspondientes al grafo, creando una relación bidireccional de tipo CONTACTO. En cambio, la funcionalidad seguir usuario agrega únicamente una relación de tipo SIGUE, representando una conexión unidireccional.
