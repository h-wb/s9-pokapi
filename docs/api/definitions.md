
<a name="definitions"></a>
## Definitions

<a name="esttypedto"></a>
### EstTypeDTO
Classe représentant le lien entre un Pokémon et un type


|Name|Description|Schema|
|---|---|---|
|**idPokemon**  <br>*optional*|Identifiant du Pokémon  <br>**Example** : `1`|integer (int64)|
|**idType**  <br>*optional*|Identifiant du type  <br>**Example** : `1`|integer (int64)|


<a name="esttypeentity"></a>
### EstTypeEntity
Classe représentant le lien entre un Pokémon et un type


|Name|Description|Schema|
|---|---|---|
|**idPokemon**  <br>*optional*|Identifiant du Pokémon  <br>**Example** : `1`|integer (int64)|
|**idType**  <br>*optional*|Identifiant du type  <br>**Example** : `1`|integer (int64)|


<a name="file"></a>
### File

|Name|Schema|
|---|---|
|**absolute**  <br>*optional*|boolean|
|**absoluteFile**  <br>*optional*|[File](#file)|
|**absolutePath**  <br>*optional*|string|
|**canonicalFile**  <br>*optional*|[File](#file)|
|**canonicalPath**  <br>*optional*|string|
|**directory**  <br>*optional*|boolean|
|**file**  <br>*optional*|boolean|
|**freeSpace**  <br>*optional*|integer (int64)|
|**hidden**  <br>*optional*|boolean|
|**name**  <br>*optional*|string|
|**parent**  <br>*optional*|string|
|**parentFile**  <br>*optional*|[File](#file)|
|**path**  <br>*optional*|string|
|**totalSpace**  <br>*optional*|integer (int64)|
|**usableSpace**  <br>*optional*|integer (int64)|


<a name="inputstream"></a>
### InputStream
*Type* : object


<a name="inputstreamresource"></a>
### InputStreamResource

|Name|Schema|
|---|---|
|**description**  <br>*optional*|string|
|**file**  <br>*optional*|[File](#file)|
|**filename**  <br>*optional*|string|
|**inputStream**  <br>*optional*|[InputStream](#inputstream)|
|**open**  <br>*optional*|boolean|
|**readable**  <br>*optional*|boolean|
|**uri**  <br>*optional*|[URI](#uri)|
|**url**  <br>*optional*|[URL](#url)|


<a name="pokemondto"></a>
### PokemonDTO
Classe représentant un Pokémon


|Name|Description|Schema|
|---|---|---|
|**idPokedex**  <br>*optional*|Numéro de Pokédex du pokémon  <br>**Example** : `1`|integer (int64)|
|**name**  <br>*optional*|Nom du pokémon  <br>**Example** : `"Bulbizarre"`|string|


<a name="pokemonentity"></a>
### PokemonEntity
Classe représentant un Pokémon


|Name|Description|Schema|
|---|---|---|
|**idPokedex**  <br>*optional*|Numéro de Pokédex du pokémon  <br>**Example** : `1`|integer (int64)|
|**name**  <br>*optional*|Nom du pokémon  <br>**Example** : `"Bulbizarre"`|string|


<a name="typedto"></a>
### TypeDTO
Classe représentant un type de pokémon


|Name|Description|Schema|
|---|---|---|
|**name**  <br>*optional*|Nom du type  <br>**Example** : `"Feu"`|string|


<a name="typeentity"></a>
### TypeEntity
Classe représentant un type de pokémon


|Name|Description|Schema|
|---|---|---|
|**name**  <br>*optional*|Nom du type  <br>**Example** : `"Feu"`|string|


<a name="uri"></a>
### URI

|Name|Schema|
|---|---|
|**absolute**  <br>*optional*|boolean|
|**authority**  <br>*optional*|string|
|**fragment**  <br>*optional*|string|
|**host**  <br>*optional*|string|
|**opaque**  <br>*optional*|boolean|
|**path**  <br>*optional*|string|
|**port**  <br>*optional*|integer (int32)|
|**query**  <br>*optional*|string|
|**rawAuthority**  <br>*optional*|string|
|**rawFragment**  <br>*optional*|string|
|**rawPath**  <br>*optional*|string|
|**rawQuery**  <br>*optional*|string|
|**rawSchemeSpecificPart**  <br>*optional*|string|
|**rawUserInfo**  <br>*optional*|string|
|**scheme**  <br>*optional*|string|
|**schemeSpecificPart**  <br>*optional*|string|
|**userInfo**  <br>*optional*|string|


<a name="url"></a>
### URL

|Name|Schema|
|---|---|
|**authority**  <br>*optional*|string|
|**content**  <br>*optional*|object|
|**defaultPort**  <br>*optional*|integer (int32)|
|**file**  <br>*optional*|string|
|**host**  <br>*optional*|string|
|**path**  <br>*optional*|string|
|**port**  <br>*optional*|integer (int32)|
|**protocol**  <br>*optional*|string|
|**query**  <br>*optional*|string|
|**ref**  <br>*optional*|string|
|**userInfo**  <br>*optional*|string|



