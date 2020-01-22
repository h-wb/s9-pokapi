
<a name="paths"></a>
## Paths

<a name="indexusingpost"></a>
### index
```
POST /
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* main-controller


<a name="indexusingget"></a>
### index
```
GET /
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Produces

* `*/*`


#### Tags

* main-controller


<a name="indexusingput"></a>
### index
```
PUT /
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* main-controller


<a name="indexusingdelete"></a>
### index
```
DELETE /
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Produces

* `*/*`


#### Tags

* main-controller


<a name="indexusingpatch"></a>
### index
```
PATCH /
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* main-controller


<a name="indexusinghead"></a>
### index
```
HEAD /
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* main-controller


<a name="indexusingoptions"></a>
### index
```
OPTIONS /
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* main-controller


<a name="errorhtmlusingpost"></a>
### errorHtml
```
POST /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|


#### Consumes

* `application/json`


#### Produces

* `text/html`


#### Tags

* basic-error-controller


<a name="errorhtmlusingget"></a>
### errorHtml
```
GET /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|


#### Produces

* `text/html`


#### Tags

* basic-error-controller


<a name="errorhtmlusingput"></a>
### errorHtml
```
PUT /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|


#### Consumes

* `application/json`


#### Produces

* `text/html`


#### Tags

* basic-error-controller


<a name="errorhtmlusingdelete"></a>
### errorHtml
```
DELETE /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|


#### Produces

* `text/html`


#### Tags

* basic-error-controller


<a name="errorhtmlusingpatch"></a>
### errorHtml
```
PATCH /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|


#### Consumes

* `application/json`


#### Produces

* `text/html`


#### Tags

* basic-error-controller


<a name="errorhtmlusinghead"></a>
### errorHtml
```
HEAD /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|


#### Consumes

* `application/json`


#### Produces

* `text/html`


#### Tags

* basic-error-controller


<a name="errorhtmlusingoptions"></a>
### errorHtml
```
OPTIONS /error
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|


#### Consumes

* `application/json`


#### Produces

* `text/html`


#### Tags

* basic-error-controller


<a name="createpokemonusingpost"></a>
### createPokemon
```
POST /pokemons
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**pokemon**  <br>*required*|pokemon|[Pokemon](#pokemon)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Pokemon](#pokemon)|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* pokemon-controller


<a name="getallpokemonsusingget"></a>
### getAllPokemons
```
GET /pokemons
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [Pokemon](#pokemon) > array|


#### Produces

* `*/*`


#### Tags

* pokemon-controller


<a name="getpokemonbyidusingget"></a>
### getPokemonById
```
GET /pokemons/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Pokemon](#pokemon)|


#### Produces

* `*/*`


#### Tags

* pokemon-controller


<a name="updatepokemonusingput"></a>
### updatePokemon
```
PUT /pokemons/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|
|**Body**|**pokemonDetails**  <br>*required*|pokemonDetails|[Pokemon](#pokemon)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|object|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* pokemon-controller


<a name="deletepokemonbyidusingdelete"></a>
### deletePokemonById
```
DELETE /pokemons/{Id}
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

* pokemon-controller


<a name="createtypeusingpost"></a>
### createType
```
POST /types
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**type**  <br>*required*|type|[Type](#type)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Type](#type)|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* type-controller


<a name="getalltypesusingget"></a>
### getAllTypes
```
GET /types
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Iterable«Type»](#9c3627461f0d3a843a0e61623d3d6b8d)|


#### Produces

* `*/*`


#### Tags

* type-controller


<a name="gettypebyidusingget"></a>
### getTypeById
```
GET /types/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Type](#type)|


#### Produces

* `*/*`


#### Tags

* type-controller


<a name="updatetypeusingput"></a>
### updateType
```
PUT /types/{Id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**Id**  <br>*required*|Id|integer (int64)|
|**Body**|**typeDetails**  <br>*required*|typeDetails|[Type](#type)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|object|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* type-controller


<a name="deletetypebyidusingdelete"></a>
### deleteTypeById
```
DELETE /types/{Id}
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

* type-controller



