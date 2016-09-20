# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-bookstore)
  - [Recurso Bookasasas](#recurso-book)
    - [GET /books](#get-/books)
    - [GET /books/{id}](#GET-/books/{id})
    - [POST /books](#POST-/books)
    - [PUT /books/{id}](#PUT-/books/{id})
    - [DELETE /books/{id}](#DELETE-/books/{id})
    - [GET books/{booksid}/authors](#GET-books/{booksid}/authors)
    - [GET books/{booksid}/authors/{authorsid}](#GET-books/{booksid}/authors/{authorsid})
    - [POST books/{booksid}/authors/{authorsid}](#POST-books/{booksid}/authors/{authorsid})
    - [PUT books/{booksid}/authors](#PUT-books/{booksid}/authors)
    - [DELETE books/{booksid}/authors/{authorsid}](#DELETE-books/{booksid}/authors/{authorsid}])
  - [Recurso Author](#recurso-author)
    - [GET /authors](#GET-/authors)
    - [GET /authors/{id}](#GET-/authors/{id})
    - [POST /authors](#POST-/authors)
    - [PUT /authors/{id}](#PUT-/authors/{id})
    - [DELETE /authors/{id}](#DELETE-/authors/{id})
    - [GET authors/{authorsid}/books](#GET-authors/{authorsid}/books)
    - [GET authors/{authorsid}/books/{booksid}](#GET-authors/{authorsid}/books/{booksid})
    - [POST authors/{authorsid}/books/{booksid}](#POST-authors/{authorsid}/books/{booksid})
    - [PUT authors/{authorsid}/books](#PUT-authors/{authorsid}/books)
    - [DELETE authors/{authorsid}/books/{booksid}](#DELETE-authors/{authorsid}/books/{booksid}])
  - [Recurso Editorial](#recurso-editorial)
    - [GET /editorials](#GET-/editorials)
    - [GET /editorials/{id}](#GET-/editorials/{id})
    - [POST /editorials](#POST-/editorials)
    - [PUT /editorials/{id}](#PUT-/editorials/{id})
    - [DELETE /editorials/{id}](#DELETE-/editorials/{id})
    - [GET editorials/{editorialsid}/books](#GET-editorials/{editorialsid}/books)
    - [GET editorials/{editorialsid}/books/{booksid}](#GET-editorials/{editorialsid}/books/{booksid})
    - [POST editorials/{editorialsid}/books/{booksid}](#POST-editorials/{editorialsid}/books/{booksid})
    - [PUT editorials/{editorialsid}/books](#PUT-editorials/{editorialsid}/books)
    - [DELETE editorials/{editorialsid}/books/{booksid}](#DELETE-editorials/{editorialsid}/books/{booksid}])

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /BookStore.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación BookStore
### Recurso Book
El objeto Book tiene 3 representaciones JSON:

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    description: '' /*Tipo String*/,
    isbn: '' /*Tipo String*/,
    image: '' /*Tipo String*/,
    publicationDate: '' /*Tipo Date*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    editorial: {
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/    }
}
```

#### Representación Full
```javascript
{
    // todo lo de la representación Detail más la collección de los objetos de relaciones composite.
    reviews: [{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    source: '' /*Tipo String*/,
    description: '' /*Tipo String*/    }]
}
```


#### GET /books

Retorna una colección de objetos Book en representación Detail.
Cada Book en la colección tiene embebidos los siguientes objetos: Editorial.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-book)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /books/{id}

Retorna una colección de objetos Book en representación Full.
Cada Book en la colección tiene los siguientes objetos: Editorial.
Cada Book en la colección tiene embebidos los siguientes objetos con relaciones composite: Review.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Book en [representaciones Full](#recurso-book)
404|No existe un objeto Book con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /books

Es el encargado de crear objetos Book.
Dado que Review es una clase hija de Book a través de una relación composite, este servicio también permite la creación de reviews asociados con un Book.
.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Book que será creado|Sí|[Representación Full](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book ha sido creado|[Representación Full](#recurso-book)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Book|Mensaje de error

#### PUT /books/{id}

Es el encargado de actualizar objetos Book.
Dado que Review es una clase hija de Book a través de una relación composite, este servicio también permite la actualización de reviews asociados con un Book.
.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a actualizar|Sí|Integer
body|body|Objeto Book nuevo|Sí|[Representación Full](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book actualizado|[Representación Full](#recurso-book)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Book|Mensaje de error

#### DELETE /books/{id}

Elimina un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

#### GET books/{booksid}/authors

Retorna una colección de objetos Author asociados a un objeto Book en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Author en [representación Detail](#recurso-author)
500|Error consultando authors |Mensaje de error

#### GET books/{booksid}/authors/{authorsid}

Retorna un objeto Author asociados a un objeto Book en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book a consultar|Sí|Integer
authorsid|Path|ID del objeto Author a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Author en [representación Detail](#recurso-author)
404|No existe un objeto Author con el ID solicitado asociado al objeto Book indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST books/{booksid}/authors/{authorsid}

Asocia un objeto Author a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|PathParam|ID del objeto Book al cual se asociará el objeto Author|Sí|Integer
authorsid|PathParam|ID del objeto Author a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Author asociado|[Representación Detail de Author](#recurso-author)
500|No se pudo asociar el objeto Author|Mensaje de error

#### PUT books/{booksid}/authors

Es el encargado de remplazar la colección de objetos Author asociada a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos Author|Sí|[Representación Detail](#recurso-author)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se remplazó la colección|Colección de objetos Author en [Representación Detail](#recurso-author)
500|No se pudo remplazar la colección|Mensaje de error

#### DELETE books/{booksid}/authors/{authorsid}

Remueve un objeto Author de la colección en un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book asociado al objeto Author|Sí|Integer
authorsid|Path|ID del objeto Author a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)
### Recurso Author

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```




#### GET /authors

Retorna una colección de objetos Author en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /authors/{id}

Retorna una colección de objetos Author en representación Full.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Author en [representaciones Full](#recurso-author)
404|No existe un objeto Author con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /authors

Es el encargado de crear objetos Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Author que será creado|Sí|[Representación Full](#recurso-author)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Author ha sido creado|[Representación Full](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Author|Mensaje de error

#### PUT /authors/{id}

Es el encargado de actualizar objetos Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a actualizar|Sí|Integer
body|body|Objeto Author nuevo|Sí|[Representación Full](#recurso-author)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Author actualizado|[Representación Full](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Author|Mensaje de error

#### DELETE /authors/{id}

Elimina un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

#### GET authors/{authorsid}/books

Retorna una colección de objetos Book asociados a un objeto Author en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Book en [representación Detail](#recurso-book)
500|Error consultando books |Mensaje de error

#### GET authors/{authorsid}/books/{booksid}

Retorna un objeto Book asociados a un objeto Author en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|Path|ID del objeto Author a consultar|Sí|Integer
booksid|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Book en [representación Detail](#recurso-book)
404|No existe un objeto Book con el ID solicitado asociado al objeto Author indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST authors/{authorsid}/books/{booksid}

Asocia un objeto Book a un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|PathParam|ID del objeto Author al cual se asociará el objeto Book|Sí|Integer
booksid|PathParam|ID del objeto Book a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Book asociado|[Representación Detail de Book](#recurso-book)
500|No se pudo asociar el objeto Book|Mensaje de error

#### PUT authors/{authorsid}/books

Es el encargado de remplazar la colección de objetos Book asociada a un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|Path|ID del objeto Author cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos Book|Sí|[Representación Detail](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se remplazó la colección|Colección de objetos Book en [Representación Detail](#recurso-book)
500|No se pudo remplazar la colección|Mensaje de error

#### DELETE authors/{authorsid}/books/{booksid}

Remueve un objeto Book de la colección en un objeto Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
authorsid|Path|ID del objeto Author asociado al objeto Book|Sí|Integer
booksid|Path|ID del objeto Book a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)
### Recurso Editorial

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```




#### GET /editorials

Retorna una colección de objetos Editorial en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-editorial)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /editorials/{id}

Retorna una colección de objetos Editorial en representación Full.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Editorial a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Editorial en [representaciones Full](#recurso-editorial)
404|No existe un objeto Editorial con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /editorials

Es el encargado de crear objetos Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Editorial que será creado|Sí|[Representación Full](#recurso-editorial)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Editorial ha sido creado|[Representación Full](#recurso-editorial)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Editorial|Mensaje de error

#### PUT /editorials/{id}

Es el encargado de actualizar objetos Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Editorial a actualizar|Sí|Integer
body|body|Objeto Editorial nuevo|Sí|[Representación Full](#recurso-editorial)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Editorial actualizado|[Representación Full](#recurso-editorial)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Editorial|Mensaje de error

#### DELETE /editorials/{id}

Elimina un objeto Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Editorial a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

#### GET editorials/{editorialsid}/books

Retorna una colección de objetos Book asociados a un objeto Editorial en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Editorial a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Book en [representación Detail](#recurso-book)
500|Error consultando books |Mensaje de error

#### GET editorials/{editorialsid}/books/{booksid}

Retorna un objeto Book asociados a un objeto Editorial en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
editorialsid|Path|ID del objeto Editorial a consultar|Sí|Integer
booksid|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Book en [representación Detail](#recurso-book)
404|No existe un objeto Book con el ID solicitado asociado al objeto Editorial indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST editorials/{editorialsid}/books/{booksid}

Asocia un objeto Book a un objeto Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
editorialsid|PathParam|ID del objeto Editorial al cual se asociará el objeto Book|Sí|Integer
booksid|PathParam|ID del objeto Book a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Book asociado|[Representación Detail de Book](#recurso-book)
500|No se pudo asociar el objeto Book|Mensaje de error

#### PUT editorials/{editorialsid}/books

Es el encargado de remplazar la colección de objetos Book asociada a un objeto Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
editorialsid|Path|ID del objeto Editorial cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos Book|Sí|[Representación Detail](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se remplazó la colección|Colección de objetos Book en [Representación Detail](#recurso-book)
500|No se pudo remplazar la colección|Mensaje de error

#### DELETE editorials/{editorialsid}/books/{booksid}

Remueve un objeto Book de la colección en un objeto Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
editorialsid|Path|ID del objeto Editorial asociado al objeto Book|Sí|Integer
booksid|Path|ID del objeto Book a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)
