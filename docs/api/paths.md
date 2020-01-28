
<a name="paths"></a>
## Paths

<a name="getallliensesttypeusingget"></a>
### Récupérer tous les liens entre les Pokémons et les types
```
GET /api/esttype/all
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [EstTypeEntity](#esttypeentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* estType


<a name="getallliensesttypebypokemonusingget"></a>
### Récupérer tous les liens entre un Pokémon et ses types par l'id du Pokémon
```
GET /api/esttype/all/pokemon/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [EstTypeEntity](#esttypeentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* estType


<a name="getallliensesttypebytypeusingget"></a>
### Récupérer tous les liens entre les Pokémons et un type par l'id du type
```
GET /api/esttype/all/type/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [EstTypeEntity](#esttypeentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* estType


<a name="createlienesttypeusingpost"></a>
### Créer un lien entre un Pokémon et un type
```
POST /api/esttype/new
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**estTypeDTO**  <br>*required*|estTypeDTO|[EstTypeDTO](#esttypedto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[EstTypeEntity](#esttypeentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* estType


<a name="getlienesttypebyidusingget"></a>
### Récupérer un lien entre un Pokémon et un type par id
```
GET /api/esttype/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[EstTypeEntity](#esttypeentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* estType


<a name="updatelienesttypeusingput"></a>
### Modifier un lien entre un Pokémon et un type par id
```
PUT /api/esttype/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|
|**Body**|**estTypeDTO**  <br>*required*|estTypeDTO|[EstTypeDTO](#esttypedto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[EstTypeEntity](#esttypeentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* estType


<a name="deletelienesttypeusingdelete"></a>
### Supprimer un lien entre un Pokémon et un type par id
```
DELETE /api/esttype/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, boolean > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `application/json`


#### Tags

* estType


<a name="getallpokemonsusingget"></a>
### Récuperer tous les pokémons
```
GET /api/pokemon/all
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [PokemonEntity](#pokemonentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* pokemon


<a name="exportpokemonsusingget"></a>
### Exporter des Pokémons par nom
```
GET /api/pokemon/export/
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**Extension**  <br>*required*|Format du fichier|enum (xlsx, csv)|
|**Query**|**Name**  <br>*optional*|Name|string|
|**Query**|**Version**  <br>*required*|Version à exporter|enum (full, light)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[InputStreamResource](#inputstreamresource)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* pokemon


<a name="createpokemonusingpost"></a>
### Créer un pokémon
```
POST /api/pokemon/new
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**pokemonDTO**  <br>*required*|pokemonDTO|[PokemonDTO](#pokemondto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[PokemonEntity](#pokemonentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* pokemon


<a name="getallpokemonsbypokedexidusingget"></a>
### Récuperer les pokémons par id Pokédex
```
GET /api/pokemon/pokedex/{IdPokedex}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**IdPokedex**  <br>*required*|IdPokedex|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [PokemonEntity](#pokemonentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* pokemon


<a name="searchpokemonusingget"></a>
### Chercher un Pokémon par nom
```
GET /api/pokemon/search/
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**Name**  <br>*optional*|Name|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [PokemonEntity](#pokemonentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* pokemon


<a name="getpokemonbyidusingget"></a>
### Récupérer un pokémon par id
```
GET /api/pokemon/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[PokemonEntity](#pokemonentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* pokemon


<a name="updatepokemonusingput"></a>
### Modifier un pokémon par id
```
PUT /api/pokemon/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|
|**Body**|**pokemonDTO**  <br>*required*|pokemonDTO|[PokemonDTO](#pokemondto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[PokemonEntity](#pokemonentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* pokemon


<a name="deletepokemonbyidusingdelete"></a>
### Supprimer un pokémon par id
```
DELETE /api/pokemon/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, boolean > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `application/json`


#### Tags

* pokemon


<a name="getalltypesusingget"></a>
### Récuperer tous les type de Pokémons
```
GET /api/type/all
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [TypeEntity](#typeentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* type


<a name="createtypeusingpost"></a>
### Créer un type de Pokémon
```
POST /api/type/new
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**typeDTO**  <br>*required*|typeDTO|[TypeDTO](#typedto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[TypeEntity](#typeentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* type


<a name="searchtypeusingget"></a>
### Chercher un type de Pokémon par nom
```
GET /api/type/search/
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**Name**  <br>*required*|Name|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [TypeEntity](#typeentity) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* type


<a name="gettypebyidusingget"></a>
### Récupérer un type de Pokémon par id
```
GET /api/type/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[TypeEntity](#typeentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `application/json`


#### Tags

* type


<a name="updatetypeusingput"></a>
### Modifier un type de Pokémon par id
```
PUT /api/type/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|
|**Body**|**typeDTO**  <br>*required*|typeDTO|[TypeDTO](#typedto)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[TypeEntity](#typeentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* type


<a name="deletetypebyidusingdelete"></a>
### Supprimer un type de Pokémon par id
```
DELETE /api/type/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, boolean > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


#### Produces

* `application/json`


#### Tags

* type



