package fr.legrain.prestashop.ws.entities;

/**
 * The product manufacturers
 * @author nicolas
 *
 */
public class Manufacturers {
/*
 <?xml version="1.0" encoding="UTF-8"?>
<prestashop xmlns:xlink="http://www.w3.org/1999/xlink">
<manufacturer>
<id_address></id_address>
<link_rewrite></link_rewrite>
<name></name>
<short_description>
<language id="1" ></language>
<language id="2" ></language>
<language id="3" ></language>
<language id="4" ></language>
<language id="5" ></language>
</short_description>
<meta_title>
<language id="1" ></language>
<language id="2" ></language>
<language id="3" ></language>

<language id="4" ></language>
<language id="5" ></language>
</meta_title>
<meta_description>
<language id="1" ></language>
<language id="2" ></language>
<language id="3" ></language>
<language id="4" ></language>
<language id="5" ></language>
</meta_description>
<description>
<language id="1" ></language>
<language id="2" ></language>
<language id="3" ></language>
<language id="4" ></language>
<language id="5" ></language>
</description>

<meta_keywords>
<language id="1" ></language>
<language id="2" ></language>
<language id="3" ></language>
<language id="4" ></language>
<language id="5" ></language>
</meta_keywords>
</manufacturer>
</prestashop>

 */
	
/*
 <?xml version="1.0" encoding="UTF-8"?>
<prestashop xmlns:xlink="http://www.w3.org/1999/xlink">
<manufacturer>
<id_address xlink:href="http://promethee.biz/prestaEb/api/addresses/"></id_address>
<link_rewrite></link_rewrite>
<name required="true" maxSize="64" format="isCatalogName"></name>
<short_description maxSize="254" format="isString" >
<language id="1" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/1"></language>
<language id="2" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/2"></language>
<language id="3" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/3"></language>
<language id="4" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/4"></language>
<language id="5" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/5"></language>
</short_description>
<meta_title maxSize="128" format="isGenericName" >
<language id="1" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/1"></language>
<language id="2" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/2"></language>
<language id="3" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/3"></language>

<language id="4" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/4"></language>
<language id="5" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/5"></language>
</meta_title>
<meta_description maxSize="255" format="isGenericName" >
<language id="1" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/1"></language>
<language id="2" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/2"></language>
<language id="3" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/3"></language>
<language id="4" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/4"></language>
<language id="5" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/5"></language>
</meta_description>
<description format="isString" >
<language id="1" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/1"></language>
<language id="2" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/2"></language>
<language id="3" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/3"></language>
<language id="4" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/4"></language>
<language id="5" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/5"></language>
</description>

<meta_keywords format="isGenericName" >
<language id="1" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/1"></language>
<language id="2" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/2"></language>
<language id="3" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/3"></language>
<language id="4" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/4"></language>
<language id="5" format="isUnsignedId" xlink:href="http://promethee.biz/prestaEb/api/languages/5"></language>
</meta_keywords>
</manufacturer>
</prestashop>

 */
	
	private int idAdress;
	private String linkRewrite;
	private String name;
	private String shortDescription; // ???
	private String metaTitle; // ???
	private String metaDescription; // ???
	private String description; // ???
	private String metaKeywords; // ???
}
