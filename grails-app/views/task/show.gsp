
<%@ page import="todo.Task" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-task" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list task">
			
				<g:if test="${taskInstance?.done}">
				<li class="fieldcontain">
					<span id="done-label" class="property-label"><g:message code="task.done.label" default="Done" /></span>
					
						<span class="property-value" aria-labelledby="done-label"><g:formatBoolean boolean="${taskInstance?.done}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.priority}">
				<li class="fieldcontain">
					<span id="priority-label" class="property-label"><g:message code="task.priority.label" default="Priority" /></span>
					
						<span class="property-value" aria-labelledby="priority-label"><g:fieldValue bean="${taskInstance}" field="priority"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.subject}">
				<li class="fieldcontain">
					<span id="subject-label" class="property-label"><g:message code="task.subject.label" default="Subject" /></span>
					
						<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${taskInstance}" field="subject"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.tasklist}">
				<li class="fieldcontain">
					<span id="tasklist-label" class="property-label"><g:message code="task.tasklist.label" default="Tasklist" /></span>
					
						<span class="property-value" aria-labelledby="tasklist-label"><g:link controller="taskList" action="show" id="${taskInstance?.tasklist?.id}">${taskInstance?.tasklist?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${taskInstance?.id}" />
					<g:link class="edit" action="edit" id="${taskInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
