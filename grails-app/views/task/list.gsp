
<%@ page import="todo.Task" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-task" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="done" title="${message(code: 'task.done.label', default: 'Done')}" />
					
						<g:sortableColumn property="priority" title="${message(code: 'task.priority.label', default: 'Priority')}" />
					
						<g:sortableColumn property="subject" title="${message(code: 'task.subject.label', default: 'Subject')}" />
					
						<th><g:message code="task.tasklist.label" default="Tasklist" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${taskInstanceList}" status="i" var="taskInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "done")}</g:link></td>
					
						<td>${fieldValue(bean: taskInstance, field: "priority")}</td>
					
						<td>${fieldValue(bean: taskInstance, field: "subject")}</td>
					
						<td>${fieldValue(bean: taskInstance, field: "tasklist")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${taskInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
