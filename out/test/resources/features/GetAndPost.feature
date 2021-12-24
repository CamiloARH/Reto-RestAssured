Feature:Obtener un recurso y actualizar datos
  como un usuario registrado del sistema
  quiere obtener un recurso del sistema y luego hacer un cambio
  para poder saber si el color que busco se encuentra y luego cambiar mi informacion

  Scenario Outline:Obtener un recurso
    Given el usuario esta en la pagina de busqueda y desea buscar en la lista de recursos
    When el usuario busca el recurso que desea y obtiene una respuesta <respuesta>
    Then el usuario debera ver la data del recurso como su nombre <name> o no ver nada si el recurso no existe
    Examples:
    |respuesta|name|
    |HttpStatus.SC_OK|fuchsia rose|
    |HttpStatus.SC_NOT_FOUND|null|


  Scenario: Actualizar datos
    Given el usuario esta en la pagina de update de usuarios
    When el usuario escribe el nombre "morpheus" y cambia su cargo "zion resident"
    Then el usuario obtiene una respuesta y ve sus nuevos datos