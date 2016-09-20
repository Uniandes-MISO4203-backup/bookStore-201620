# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-model)
  - [Recurso Book](#recurso-book)
    - [GET /books](#GET-/books)
    - [GET /books/{id}](#GET-/books/{id})
    - [POST /books](#POST-/books)
    - [PUT /books/{id}](#PUT-/books/{id})
    - [DELETE /books/{id}](#DELETE-/books/{id})
    - [GET books/{booksid}/authors](#GET-books/{booksid}/authors)
    - [GET books/{booksid}/authors/{authorsid}](#GET-books/{booksid}/authors/{authorsid})
    - [POST books/{booksid}/authors/{authorsid}](#POST-books/{booksid}/authors/{authorsid})
    - [PUT books/{booksid}/authors](#PUT-books/{booksid}/authors)
    - [DELETE books/{booksid}/authors/{authorsid}](#DELETE-books/{booksid}/authors/{authorsid}])
    - [GET books/{booksid}/reviews](#GET-books/{booksid}/reviews)
    - [GET books/{booksid}/reviews/{reviewsid}](#GET-books/{booksid}/reviews/{reviewsid})
    - [POST books/{booksid}/reviews/{reviewsid}](#POST-books/{booksid}/reviews/{reviewsid})
    - [PUT books/{booksid}/reviews](#PUT-books/{booksid}/reviews)
    - [DELETE books/{booksid}/reviews/{reviewsid}](#DELETE-books/{booksid}/reviews/{reviewsid}])
  - [Recurso Author](#recurso-author)
    - [GET /authors](#GET-/authors)
    - [GET /authors/{id}](#GET-/authors/{id})
    - [POST /authors](#POST-/authors)
    - [PUT /authors/{id}](#PUT-/authors/{id})
    - [DELETE /authors/{id}](#DELETE-/authors/{id})
  - [Recurso Editorial](#recurso-editorial)
    - [GET /editorials](#GET-/editorials)
    - [GET /editorials/{id}](#GET-/editorials/{id})
    - [POST /editorials](#POST-/editorials)
    - [PUT /editorials/{id}](#PUT-/editorials/{id})
    - [DELETE /editorials/{id}](#DELETE-/editorials/{id})
    - [GET editorials/{editorialsid}/editedbooks](#GET-editorials/{editorialsid}/editedbooks)
    - [GET editorials/{editorialsid}/editedbooks/{editedbooksid}](#GET-editorials/{editorialsid}/editedbooks/{editedbooksid})
    - [POST editorials/{editorialsid}/editedbooks/{editedbooksid}](#POST-editorials/{editorialsid}/editedbooks/{editedbooksid})
    - [PUT editorials/{editorialsid}/editedbooks](#PUT-editorials/{editorialsid}/editedbooks)
    - [DELETE editorials/{editorialsid}/editedbooks/{editedbooksid}](#DELETE-editorials/{editorialsid}/editedbooks/{editedbooksid}])

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /model.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

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

## API de la aplicación model
### Recurso Book
El objeto Book tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    isbn: '' /*Tipo String*/,
    description: '' /*Tipo String*/,
    image: '' /*Tipo String*/,
    publishingdate: '' /*Tipo Date*/
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

Retorna una colección de objetos Book en representación Detail.
Cada Book en la colección tiene los siguientes objetos: Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Book en [representaciones Detail](#recurso-book)
404|No existe un objeto Book con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /books

Es el encargado de crear objetos Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Book que será creado|Sí|[Representación Detail](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book ha sido creado|[Representación Detail](#recurso-book)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Book|Mensaje de error

#### PUT /books/{id}

Es el encargado de actualizar objetos Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a actualizar|Sí|Integer
body|body|Objeto Book nuevo|Sí|[Representación Detail](#recurso-book)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book actualizado|[Representación Detail](#recurso-book)
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


#### GET books/{booksid}/reviews

Retorna una colección de objetos Review asociados a un objeto Book en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Review en [representación Detail](#recurso-review)
500|Error consultando reviews |Mensaje de error

#### GET books/{booksid}/reviews/{reviewsid}

Retorna un objeto Review asociados a un objeto Book en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book a consultar|Sí|Integer
reviewsid|Path|ID del objeto Review a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Review en [representación Detail](#recurso-review)
404|No existe un objeto Review con el ID solicitado asociado al objeto Book indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST books/{booksid}/reviews/{reviewsid}

Asocia un objeto Review a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|PathParam|ID del objeto Book al cual se asociará el objeto Review|Sí|Integer
reviewsid|PathParam|ID del objeto Review a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Review asociado|[Representación Detail de Review](#recurso-review)
500|No se pudo asociar el objeto Review|Mensaje de error

#### PUT books/{booksid}/reviews

Es el encargado de actualizar un objeto Review asociada a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos Review|Sí|[Representación Detail](#recurso-review)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se actualizo el objeto|Objeto Review en [Representación Detail](#recurso-review)
500|No se pudo actualizar|Mensaje de error

#### DELETE books/{booksid}/reviews/{reviewsid}

Remueve un objeto Review asociado a un objeto Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
booksid|Path|ID del objeto Book asociado al objeto Review|Sí|Integer
reviewsid|Path|ID del objeto Review a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)
### Recurso Author
El objeto Author tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    birthDate: '' /*Tipo Date*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    books: {
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    isbn: '' /*Tipo String*/,
    description: '' /*Tipo String*/,
    image: '' /*Tipo String*/,
    publishingdate: '' /*Tipo Date*/    }
}
```



#### GET /authors

Retorna una colección de objetos Author en representación Detail.
Cada Author en la colección tiene embebidos los siguientes objetos: Book.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /authors/{id}

Retorna una colección de objetos Author en representación Detail.
Cada Author en la colección tiene los siguientes objetos: Book.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Author en [representaciones Detail](#recurso-author)
404|No existe un objeto Author con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /authors

Es el encargado de crear objetos Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Author que será creado|Sí|[Representación Detail](#recurso-author)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Author ha sido creado|[Representación Detail](#recurso-author)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Author|Mensaje de error

#### PUT /authors/{id}

Es el encargado de actualizar objetos Author.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Author a actualizar|Sí|Integer
body|body|Objeto Author nuevo|Sí|[Representación Detail](#recurso-author)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Author actualizado|[Representación Detail](#recurso-author)
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



[Volver arriba](#tabla-de-contenidos)
### Recurso Editorial
El objeto Editorial tiene 2 representaciones JSON:	

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

Retorna una colección de objetos Editorial en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Editorial a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Editorial en [representaciones Detail](#recurso-editorial)
404|No existe un objeto Editorial con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /editorials

Es el encargado de crear objetos Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Editorial que será creado|Sí|[Representación Detail](#recurso-editorial)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Editorial ha sido creado|[Representación Detail](#recurso-editorial)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Editorial|Mensaje de error

#### PUT /editorials/{id}

Es el encargado de actualizar objetos Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Editorial a actualizar|Sí|Integer
body|body|Objeto Editorial nuevo|Sí|[Representación Detail](#recurso-editorial)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Editorial actualizado|[Representación Detail](#recurso-editorial)
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

#### GET editorials/{editorialsid}/editedbooks

Retorna una colección de objetos Book asociados a un objeto Editorial en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Editorial a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Book en [representación Detail](#recurso-book)
500|Error consultando editedbooks |Mensaje de error

#### GET editorials/{editorialsid}/editedbooks/{editedbooksid}

Retorna un objeto Book asociados a un objeto Editorial en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
editorialsid|Path|ID del objeto Editorial a consultar|Sí|Integer
editedbooksid|Path|ID del objeto Book a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Book en [representación Detail](#recurso-book)
404|No existe un objeto Book con el ID solicitado asociado al objeto Editorial indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST editorials/{editorialsid}/editedbooks/{editedbooksid}

Asocia un objeto Book a un objeto Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
editorialsid|PathParam|ID del objeto Editorial al cual se asociará el objeto Book|Sí|Integer
editedbooksid|PathParam|ID del objeto Book a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Book asociado|[Representación Detail de Book](#recurso-book)
500|No se pudo asociar el objeto Book|Mensaje de error

#### PUT editorials/{editorialsid}/editedbooks

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

#### DELETE editorials/{editorialsid}/editedbooks/{editedbooksid}

Remueve un objeto Book de la colección en un objeto Editorial.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
editorialsid|Path|ID del objeto Editorial asociado al objeto Book|Sí|Integer
editedbooksid|Path|ID del objeto Book a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)
