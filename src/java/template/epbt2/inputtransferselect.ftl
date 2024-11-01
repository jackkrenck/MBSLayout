<#--
/*
 * $Id: inputtransferselect.ftl,v 1.1.1.1 2014/03/04 03:40:09 cvsepbt Exp $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
-->
<#if !stack.findValue("#inputtransferselect_js_included")??><#t/>
	<script type="text/javascript" src="<@s.url value="/struts/inputtransferselect.js" encode='false' includeParams='none'/>"></script>
	<#assign temporaryVariable = stack.setValue("#inputtransferselect_js_included", "true") /><#t/>
</#if><#t/>
<table border="0">
<tr>
<td>
<#if parameters.leftTitle??><#t/>
	<label for="leftTitle">${parameters.leftTitle}</label><br />
</#if><#t/>


<input type="text"<#rt/>
 name="${parameters.name?default("")?html}_input"<#rt/>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
<#if parameters.readonly?default(false)>
 readonly="readonly"<#rt/>
</#if>
<#if parameters.tabindex??>
 tabindex="${parameters.tabindex?html}"<#rt/>
</#if>
<#if parameters.id??>
 id="${parameters.id?html}_input"<#rt/>
</#if>
<#if parameters.cssClass??>
 class="${parameters.cssClass?html}"<#rt/>
</#if>
<#if parameters.cssStyle??>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/scripting-events.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/common-attributes.ftl" />
/>


</td>
<td valign="middle" align="center">
	<#assign addLabel=parameters.addLabel?default("->")?html /><#t/>
	<input type="button"
		<#if parameters.buttonCssClass??><#t/>
		 class="${parameters.buttonCssClass?html}"
		</#if><#t/>
		<#if parameters.buttonCssStyle??>
		 style="${parameters.buttonCssStyle?html}"
		</#if><#t/>
		 value="${addLabel}" onclick="addOption(document.getElementById('${parameters.id?html}_input'), document.getElementById('${parameters.id?html}'))" /><br /><br />
	<#t/>
	<#assign removeLabel=parameters.removeLabel?default("<-")?html /><#t/>
	<input type="button"
  		<#if parameters.buttonCssClass??><#t/>
		 class="${parameters.buttonCssClass?html}"
		</#if><#t/>
		<#if parameters.buttonCssStyle??>
		 style="${parameters.buttonCssStyle?html}"
		</#if><#t/>
		 value="${removeLabel}" onclick="removeOptions(document.getElementById('${parameters.id?html}'))" /><br /><br />
	<#t/>
	<#assign removeAllLabel=parameters.removeAllLabel?default("<<--")?html /><#t/>
	<input type="button"
	    		<#if parameters.buttonCssClass??><#t/>
		 class="${parameters.buttonCssClass?html}"
		</#if><#t/>
		<#if parameters.buttonCssStyle??>
		 style="${parameters.buttonCssStyle?html}"
		</#if><#t/>
		 value="${removeAllLabel}" onclick="removeAllOptions(document.getElementById('${parameters.id?html}'))" /><br /><br />
</td>
<td>
<#if parameters.rightTitle??><#t/>
	<label for="rightTitle">${parameters.rightTitle}</label><br />
</#if><#t/>
<#include "/${parameters.templateDir}/simple/select.ftl" />
<#if parameters.allowUpDown?default(true)>
<input type="button" 
<#if parameters.headerKey??>
	onclick="moveOptionDown(document.getElementById('${parameters.id}'), 'key', '${parameters.headerKey}');"
<#else>
	onclick="moveOptionDown(document.getElementById('${parameters.id}'), 'key', '');"
</#if>
<#if parameters.downLabel??>
	value="${parameters.downLabel?html}"
</#if>
/>
<input type="button" 
<#if parameters.headerKey??>
	onclick="moveOptionUp(document.getElementById('${parameters.id}'), 'key', '${parameters.headerKey}');"
<#else>
	onclick="moveOptionUp(document.getElementById('${parameters.id}'), 'key', '');"
</#if>
<#if parameters.upLabel??>
	value="${parameters.upLabel?html}"
</#if>
/>
</#if>
</td>
</tr>
</table>
