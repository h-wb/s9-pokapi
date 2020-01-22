
<a name="paths"></a>
## Paths

<a name="getallpokemonsusingget"></a>
### Récuperer tous les pokémons
```
GET /api/pokemon/all
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [PokemonEntity](#pokemonentity) > array|


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
|**Body**|**pokemonEntity**  <br>*required*|pokemonEntity|[PokemonEntity](#pokemonentity)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[PokemonEntity](#pokemonentity)|


#### Consumes

* `application/json`


#### Produces

* `*/*`


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


#### Produces

* `*/*`


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
|**Body**|**pokemonEntityDetails**  <br>*required*|pokemonEntityDetails|[PokemonEntity](#pokemonentity)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|object|


#### Consumes

* `application/json`


#### Produces

* `*/*`


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


#### Produces

* `*/*`


#### Tags

* pokemon


<a name="getalltypesusingget"></a>
### Récuperer tous les type de pokémons
```
GET /api/type/all
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [TypeEntity](#typeentity) > array|


#### Produces

* `*/*`


#### Tags

* type


<a name="createtypeusingpost"></a>
### Créer un type de pokémon
```
POST /api/type/new
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**typeEntity**  <br>*required*|typeEntity|[TypeEntity](#typeentity)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[TypeEntity](#typeentity)|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* type


<a name="gettypebyidusingget"></a>
### Récupérer un type de pokémon par id
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


#### Produces

* `*/*`


#### Tags

* type


<a name="updatetypeusingput"></a>
### Modifier un type de pokémon par id
```
PUT /api/type/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|
|**Body**|**typeEntityDetails**  <br>*required*|typeEntityDetails|[TypeEntity](#typeentity)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|object|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* type


<a name="deletetypebyidusingdelete"></a>
### Supprimer un type de pokémon par id
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


#### Produces

* `*/*`


#### Tags

* type



